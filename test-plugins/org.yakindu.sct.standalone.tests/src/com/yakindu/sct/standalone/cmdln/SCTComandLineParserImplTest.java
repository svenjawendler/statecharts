package com.yakindu.sct.standalone.cmdln;


import org.junit.Before;
import org.junit.Test;
import org.yakindu.sct.standalone.cmdln.api.SCTCommandLineParser;
import org.yakindu.sct.standalone.cmdln.impl.SCTComandLineParserImpl;

public class SCTComandLineParserImplTest {

	private SCTCommandLineParser parser;

	@Before
	public void setUp() throws Exception {
		parser = new SCTComandLineParserImpl();
	}

	@Test
	public void testParse() {
		//TODO init proper cmd line args, parse and check
		String[] args = null;
		parser.init().withArgs(args).parse();

		parser.getStandaloneOptions().getAbsoluteGenTargetDir();
		//etc...
		
	}
}
