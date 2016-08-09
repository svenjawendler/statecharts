package org.yakindu.sct.standalone.app;

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.yakindu.sct.standalone.api.ISCTStandalone;
import org.yakindu.sct.standalone.cmdln.api.SCTGeneratorOptionProvider;
import org.yakindu.sct.standalone.cmdln.api.SCTStandaloneOptions;
import org.yakindu.sct.standalone.cmdln.impl.BaseSCTGeneratorOptionProvider;
import org.yakindu.sct.standalone.impl.SCTStandalone;

public class Application implements IApplication {

	private static final String APPLICATION_ARGS = "application.args";

	@Override
	public Object start(IApplicationContext context) throws Exception {
		// org.eclipse.emf.ecore.plugin.EcorePlugin.getPlugin().
		doRun(context);
		return IApplication.EXIT_OK;
	}

	private void doRun(IApplicationContext context) throws CoreException {
		System.out.println("SCT Standalone START");
		String[] sctExecutionArguments = getArguments(context);

		SCTGeneratorOptionProvider parameter = new BaseSCTGeneratorOptionProvider().init()
				.withArgs(sctExecutionArguments).parse();

		SCTStandaloneOptions standaloneOptions = parameter.getStandaloneOptions();

		System.out.println("workspace DIR : " + standaloneOptions.getAbsoluteWorkspaceDir());
		System.out.println("genTarget DIR : " + standaloneOptions.getAbsoluteGenTargetDir());
		System.out.println("libraries DIR : " + standaloneOptions.getAbsoluteLibrariesDir());
		System.out.println("sct DIR : " + standaloneOptions.getSCTDir());
		System.out.println("sgen path : " + standaloneOptions.getSGenPath());

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
		System.out.println("SCT Standalone END");
	}

}
