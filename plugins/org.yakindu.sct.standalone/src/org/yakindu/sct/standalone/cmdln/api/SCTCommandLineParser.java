package org.yakindu.sct.standalone.cmdln.api;



public interface SCTCommandLineParser  {
	SCTCommandLineParser withArgs(String[] args);
	SCTCommandLineParser init();
	SCTCommandLineParser parse();
	SCTStandaloneOptions getStandaloneOptions();

}
