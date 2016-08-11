package org.yakindu.sct.standalone.io;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import com.google.common.collect.Lists;

public class ResourceUtil {
	public URLClassLoader resourceLoader;

	public ResourceUtil(List<String> paths) {
		paths.add(getExecutionPath());
		initResourceLoader(toURLs(paths));
	}

	protected ClassLoader initResourceLoader(Collection<URL> paths) {
		resourceLoader = URLClassLoader.newInstance(paths.toArray(new URL[paths.size()]),
				Thread.currentThread().getContextClassLoader());

		return resourceLoader;
	}

	public String getAbsolutePath(String resourcePath) {
		java.nio.file.Path ioPath = Paths.get(resourcePath);
		if (ioPath.isAbsolute()) {
			return resourcePath;
		} else {
			URL resourceUrl = resourceLoader.getResource(resourcePath);
			if (resourceUrl == null)
				throw new IllegalStateException("Could not load file '" + resourcePath + "'.\n searchRoots:\n"+getSearchRoots());
			return resourceUrl.getPath();
		}

	}
	
	public static String getExecutionPath() {
		String path = System.getProperty("user.dir");
		// normally this isn't used during production
		if (path.endsWith("bin/")) {
			return path.replace("bin/", "");
		}
		return path;
	}
	
	private String getSearchRoots() {
		String searchRoots = ""; 
		URL[] urLs = resourceLoader.getURLs();
		for (URL url : urLs) {
			if(searchRoots.isEmpty()){
				searchRoots += url.toString();
			}else{
				searchRoots += "\n"+url.toString();
				
			}
		}
		return searchRoots;
	}

	protected Collection<URL> toURLs(List<String> paths) {
		Collection<URL> values = Lists.newArrayList();
		// init URLs
		for (String path : paths) {
			try {
				IPath fromOSString = Path.fromOSString(path);
				values.add(fromOSString.toFile().toURI().toURL());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return values;
	}
}
