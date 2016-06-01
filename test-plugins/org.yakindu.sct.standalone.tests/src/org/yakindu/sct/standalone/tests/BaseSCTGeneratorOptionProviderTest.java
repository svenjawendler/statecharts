package org.yakindu.sct.standalone.tests;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.yakindu.sct.standalone.cmdln.api.SCTGeneratorOptionProvider;
import org.yakindu.sct.standalone.cmdln.impl.BaseSCTGeneratorOptionProvider;

public class BaseSCTGeneratorOptionProviderTest {

	private SCTGeneratorOptionProvider provider;

	@Before
	public void setUp() throws Exception {
		provider = new BaseSCTGeneratorOptionProvider();
	}

	@Test
	public void testParse() {
		//TODO init proper cmd line args, parse and check
		String[] args = {"-sgen","/a/genmodel.sgen","-sct","/a.test.project","-logLvl","TRACE","-ws","file:/A:/WS_FOLDER"};
		provider.init().withArgs(args).parse();

		assertEquals(provider.getStandaloneOptions().getAbsoluteWorkspaceDir(),"file:/A:/WS_FOLDER");
		assertEquals(provider.getStandaloneOptions().getAbsoluteGenTargetDir(),"file:/A:/WS_FOLDER");
		assertEquals(provider.getStandaloneOptions().getSCTDir(),"/a.test.project");
		assertEquals(provider.getStandaloneOptions().getSGenPath(),"/a/genmodel.sgen");
		//etc...
		
	}
}
