package org.yakindu.sct.standalone.cmdln.api;



public interface SCTGeneratorOptionProvider  {
	SCTGeneratorOptionProvider withArgs(String[] args);
	SCTGeneratorOptionProvider init();
	SCTGeneratorOptionProvider parse();
	SCTStandaloneOptions getStandaloneOptions();

}
