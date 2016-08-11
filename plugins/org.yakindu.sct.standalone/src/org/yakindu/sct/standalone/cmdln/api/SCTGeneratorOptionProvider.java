package org.yakindu.sct.standalone.cmdln.api;

import java.util.List;

public interface SCTGeneratorOptionProvider  {
	SCTGeneratorOptionProvider withArgs(String[] args);
	SCTGeneratorOptionProvider init();
	SCTGeneratorOptionProvider parse();
	SCTStandaloneOptions getStandaloneOptions();
	SCTGeneratorOptionProvider initLoggingForPackages(List<String> loggerIDs);

}
