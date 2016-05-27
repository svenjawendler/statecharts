package org.yakindu.sct.standalone;

import org.yakindu.sct.standalone.api.ISCTStandalone;
import org.yakindu.sct.standalone.api.SCTStandaloneParameter;
import org.yakindu.sct.standalone.cmdln.api.SCTCommandLineParser;
import org.yakindu.sct.standalone.cmdln.api.SCTStandaloneOptions;
import org.yakindu.sct.standalone.cmdln.impl.SCTComandLineParserImpl;
import org.yakindu.sct.standalone.impl.SCTStandalone;

public class Main {
	public static void main(String[] args) {

		SCTCommandLineParser parameter = new SCTComandLineParserImpl().init().withArgs(args).parse();
		SCTStandaloneParameter execParams = getExecParams(parameter.getStandaloneOptions());
		
		ISCTStandalone sctStandaloneGeneration = new SCTStandalone();
		
		sctStandaloneGeneration.init(execParams);
		sctStandaloneGeneration.generate();
		
	}
	
	public static SCTStandaloneParameter getExecParams(SCTStandaloneOptions options) {
		SCTStandaloneParameter params = new SCTStandaloneParameter();
		params.absoluteWorkspaceDir = options.getAbsoluteWorkspaceDir();
		params.absoluteSgenPath = options.getAbsoluteSGenPath();
		params.absoluteSCTDir = options.getAbsoluteSCTDir();
		params.absoluteGenTargetDir = options.getAbsoluteGenTargetDir();
		params.absoluteLibrariesDir = options.getAbsoluteLibrariesDir();
		return params;
	}

	
	
	
}

