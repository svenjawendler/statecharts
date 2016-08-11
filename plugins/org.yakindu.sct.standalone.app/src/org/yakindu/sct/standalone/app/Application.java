package org.yakindu.sct.standalone.app;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.yakindu.sct.standalone.api.ISCTStandalone;
import org.yakindu.sct.standalone.cmdln.api.SCTGeneratorOptionProvider;
import org.yakindu.sct.standalone.cmdln.api.SCTStandaloneOptions;
import org.yakindu.sct.standalone.cmdln.impl.BaseSCTGeneratorOptionProvider;
import org.yakindu.sct.standalone.impl.SCTStandalone;

import com.google.common.collect.Lists;

public class Application implements IApplication {

	private static final String APPLICATION_ARGS = "application.args";
	private static final Logger LOGGER = Logger.getLogger(Application.class);

	@Override
	public Object start(IApplicationContext context) throws Exception {
		doRun(context);
		return IApplication.EXIT_OK;
	}

	private void doRun(IApplicationContext context) throws CoreException {
		String[] sctExecutionArguments = getArguments(context);

		List<String> packages = Lists.newArrayList();
		
		packages.add("org.yakindu.sct");
		packages.add("com.yakindu.sct");
		
		SCTGeneratorOptionProvider parameter = new BaseSCTGeneratorOptionProvider().init()
				.withArgs(sctExecutionArguments).parse().initLoggingForPackages(packages);

		LOGGER.debug("SCT Standalone START");
		
		SCTStandaloneOptions standaloneOptions = parameter.getStandaloneOptions();

		LOGGER.debug("workspace DIR : " + standaloneOptions.getAbsoluteWorkspaceDir());
		LOGGER.debug("genTarget DIR : " + standaloneOptions.getAbsoluteGenTargetDir());
		LOGGER.debug("sct DIR : " + standaloneOptions.getSCTDir());
		LOGGER.debug("sgen path : " + standaloneOptions.getSGenPath());

		ISCTStandalone sctStandalone = new SCTStandalone();
		sctStandalone.init(standaloneOptions);
		sctStandalone.generate();

	}

	private String[] getArguments(IApplicationContext context) {
		Map<?, ?> applicationArguments = context.getArguments();
		String[] sctExecutionArguments = (String[]) applicationArguments.get(APPLICATION_ARGS);
		return sctExecutionArguments;
	}

	@Override
	public void stop() {
		LOGGER.debug("SCT Standalone END");
	}

}
