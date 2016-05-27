package org.yakindu.sct.standalone.cmdln.impl;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.log4j.Logger;
import org.yakindu.sct.standalone.cmdln.api.SCTCommandLineParser;
import org.yakindu.sct.standalone.cmdln.api.SCTStandaloneOptions;

public class SCTComandLineParserImpl implements SCTCommandLineParser, SCTStandaloneOptions {

	private String[] args;
	private Options cmdOptions;
	Logger LOG = Logger.getLogger(SCTComandLineParserImpl.class);
	private CommandLine cmd;
	private CmdLineUtil cmdLineUtil;

	public SCTComandLineParserImpl() {
		cmdLineUtil = new CmdLineUtil();
	}

	@Override
	public SCTCommandLineParser withArgs(String[] args) {
		this.args = args;
		return this;
	}

	@Override
	public SCTCommandLineParser init() {
		cmdLineUtil.initDefaultLogging();
		createOptions();
		return this;
	}

	@Override
	public SCTCommandLineParser parse() {

		if (args == null || args.length < 1 || cmdLineUtil.containsHelp(args)) {
			cmdLineUtil.helpAndExit(cmdOptions, "SCT Headless");
		}

		cmd = cmdLineUtil.parseCmdLine(args, "ExtendedSCTStandaloneExecutor", cmdOptions);

		return this;
	}

	@SuppressWarnings("static-access")
	protected void createOptions() {
		cmdOptions = new Options();
		// options added first will be shown on bottom, so general parameters
		// first...
		cmdLineUtil.initOptions(cmdOptions);
		// than SCT specific parameters
		cmdOptions.addOption(OptionBuilder.isRequired().hasArg().withArgName("path").withDescription(OPT_WS_DESC)
				.withLongOpt(OPT_WS_LONG).create(OPT_WS));
		cmdOptions.addOption(OptionBuilder.isRequired().hasArg().withArgName("path").withDescription(OPT_SGEN_DESC)
				.withLongOpt(OPT_SGEN_LONG).create(OPT_SGEN));
		cmdOptions.addOption(OptionBuilder.hasArg().withArgName("path").withDescription(OPT_SCT_DESC)
				.withLongOpt(OPT_SCT_LONG).create(OPT_SCT));
	}

	@Override
	public String getAbsoluteWorkspaceDir() {
		//WORKSPACE
		if (cmd.hasOption(OPT_WS))
			return  cmd.getOptionValue(OPT_WS);
		else
			return System.getProperty("user.dir") + "/workspace";
		
	}

	@Override
	public String getAbsoluteSGenPath() {
		if (cmd.hasOption(OPT_SGEN))
			return getAbsoluteWorkspaceDir()
					+ cmd.getOptionValue(OPT_SGEN);
		else
			throw new RuntimeException("-sgen is mendatory");
	}

	@Override
	public String getAbsoluteSCTDir() {
		if (cmd.hasOption(OPT_SCT))
			return getAbsoluteWorkspaceDir()
					+ cmd.getOptionValue(OPT_SCT);
		else
			return getAbsoluteWorkspaceDir();
	}

	@Override
	public String getAbsoluteGenTargetDir() {
		return getAbsoluteWorkspaceDir();
	}

	@Override
	public String getAbsoluteLibrariesDir() {
		return System.getProperty("user.dir") + "/libraries";
	}

	@Override
	public SCTStandaloneOptions getStandaloneOptions() {
		return this;
	}
}
