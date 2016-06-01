package org.yakindu.sct.standalone.api;

import org.yakindu.sct.standalone.cmdln.api.SCTStandaloneOptions;

public interface ISCTStandalone {

	void init(SCTStandaloneOptions standaloneOptions);
	void generate();


}