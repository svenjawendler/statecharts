package org.yakindu.sct.standalone;

import org.yakindu.sct.standalone.api.ISCTStandalone;
import org.yakindu.sct.standalone.cmdln.api.SCTGeneratorOptionProvider;
import org.yakindu.sct.standalone.cmdln.impl.BaseSCTGeneratorOptionProvider;
import org.yakindu.sct.standalone.impl.SCTStandalone;

public class Main {
	public static void main(String[] args) {

		SCTGeneratorOptionProvider parameter = new BaseSCTGeneratorOptionProvider().init().withArgs(args).parse();
		
		ISCTStandalone sctStandaloneGeneration = new SCTStandalone();
		
		sctStandaloneGeneration.init(parameter.getStandaloneOptions());
		sctStandaloneGeneration.generate();
	}
}

