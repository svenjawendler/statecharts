package org.yakindu.sct.standalone.cmdln.impl;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.log4j.Logger;
import org.yakindu.sct.standalone.cmdln.api.SCTGeneratorOptionProvider;
import org.yakindu.sct.standalone.cmdln.api.SCTStandaloneOptions;

public class BaseSCTGeneratorOptionProvider implements SCTGeneratorOptionProvider {

	private String[] args;
	private Options cmdOptions;
	Logger LOG = Logger.getLogger(BaseSCTGeneratorOptionProvider.class);
	private CommandLine cmd;
	private CmdLineUtil cmdLineUtil;

	public BaseSCTGeneratorOptionProvider() {
		cmdLineUtil = new CmdLineUtil();
	}

	@Override
	public SCTGeneratorOptionProvider withArgs(String[] args) {
		this.args = args;
		return this;
	}

	@Override
	public SCTGeneratorOptionProvider init() {
		createOptions();
		return this;
	}

	@Override
	public SCTGeneratorOptionProvider parse() {

		if (args == null || args.length < 1 || cmdLineUtil.containsHelp(args)) {
			cmdLineUtil.help(cmdOptions, "SCT Headless");
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
		cmdOptions.addOption(OptionBuilder.isRequired().hasArg().withArgName("path").withDescription(SCTStandaloneOptions.OPT_WS_DESC)
				.withLongOpt(SCTStandaloneOptions.OPT_WS_LONG).create(SCTStandaloneOptions.OPT_WS));
		cmdOptions.addOption(OptionBuilder.isRequired().hasArg().withArgName("path").withDescription(SCTStandaloneOptions.OPT_SGEN_DESC)
				.withLongOpt(SCTStandaloneOptions.OPT_SGEN_LONG).create(SCTStandaloneOptions.OPT_SGEN));
		cmdOptions.addOption(OptionBuilder.hasArg().withArgName("path").withDescription(SCTStandaloneOptions.OPT_SCT_DESC)
				.withLongOpt(SCTStandaloneOptions.OPT_SCT_LONG).create(SCTStandaloneOptions.OPT_SCT));
	}



	@Override
	public SCTStandaloneOptions getStandaloneOptions() {
		return new SCTStandaloneOptions() {
			@Override
			public String getAbsoluteWorkspaceDir() {
				//WORKSPACE
				if (cmd.hasOption(OPT_WS))
					return  cmd.getOptionValue(OPT_WS);
				else
					return System.getProperty("user.dir") + "/workspace";
				
			}

			@Override
			public String getSGenPath() {
				if (cmd.hasOption(OPT_SGEN))
					return cmd.getOptionValue(OPT_SGEN);
				else
					throw new RuntimeException("-sgen is mendatory");
			}

			@Override
			public String getSCTDir() {
				if (cmd.hasOption(OPT_SCT))
					return cmd.getOptionValue(OPT_SCT);
				else
					return "/";
			}

			@Override
			public String getAbsoluteGenTargetDir() {
				return getAbsoluteWorkspaceDir();
			}

			@Override
			public String getAbsoluteLibrariesDir() {
				return System.getProperty("user.dir") + "/libraries";
			}
		};
	}
}
