package org.yakindu.sct.standalone.cmdln.api;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public interface SCTStandaloneOptions {

	String OPT_WS = "workspace";
	String OPT_WS_LONG = "sctWorkspace";
	String OPT_WS_DESC = "Base generation directory. Relative from execution directory or an absolute path.";

	String OPT_SGEN = "sgen";
	String OPT_SGEN_LONG = "generatorModel";
	String OPT_SGEN_DESC = "Relative path to the generator model which will be used for execution.";

	String OPT_SCT = "statecharts";
	String OPT_SCT_LONG = "statechartFolder";
	String OPT_SCT_DESC = "The directory where SCT models could be found.";


	String OPT_LIBRARIES = "lib";

	public static final String FILE_EXTENSION_SCT = "sct";
	public static final String FILE_EXTENSION_SGEN = "sgen";
	
	public static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;
	
	
	String getAbsoluteWorkspaceDir();
	String getSGenPath();
	String getSCTDir();
	String getAbsoluteGenTargetDir();
	
}
