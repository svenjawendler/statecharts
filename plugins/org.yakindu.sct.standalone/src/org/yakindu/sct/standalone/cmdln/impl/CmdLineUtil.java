package org.yakindu.sct.standalone.cmdln.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
/**
 * 
 * @author Johannes Dicks - Initial contribution and API
 *
 */
public class CmdLineUtil {
	private static final String PARAM_HELP = "h";
	/**
	 * root package so we will configure logging for all subpackages
	 */
	public static final String LOGGER_ID = "com.yakindu.sct.generator.headless";
	public static final String LOGGER_ID_CORE = LOGGER_ID+".core";
	private static final Logger logger = Logger.getLogger(LOGGER_ID);
	private static final Logger logger_core = Logger.getLogger(LOGGER_ID_CORE);
	private static final String DEFAULT_LOG_LEVEL = "INFO";
	private static final String LOG_LEVEL_DEVELOPMENT = "DEV";

	public static final String PARAM_LOG = "logLvl";
	public static final String PARAM_LOG_FILE = "logFile";

	// execution parameters
	private static final String PARAM_CONFIRM_EXIT = "ce";

	public  void initDefaultLogging() {
		Logger root = Logger.getRootLogger();
		root.setLevel(Level.INFO);
		root.removeAllAppenders();
		PatternLayout layout = new PatternLayout("%5p | %m%n");
		// add appenders
		root.addAppender(new ConsoleAppender(layout));
	}

	public  void configureCustomLogging(CommandLine line) {
		// get log lvl
		String logLevel = null;
		if (line != null && line.hasOption(PARAM_LOG))
			logLevel = line.getOptionValue(PARAM_LOG);
		else
			logLevel = DEFAULT_LOG_LEVEL;

		Logger root = Logger.getRootLogger();
		Level level = null;
		PatternLayout layout = new PatternLayout("%5p | %d | %F | %L | %m%n");
		if (logLevel.equals(LOG_LEVEL_DEVELOPMENT)) {
			level = Level.ALL;
			root.removeAllAppenders();
			root.addAppender(new ConsoleAppender(layout));
			logger_core.setLevel(level);
		} else {
			level = Level.toLevel(logLevel);
		}
		initFileAppenderIfConfigured(line, root, layout);
		logger.setLevel(level);
		logger.info("Logging mode: " + level);
	}

	private  void initFileAppenderIfConfigured(CommandLine line, Logger root, PatternLayout layout) {
		if (line != null && line.hasOption(PARAM_LOG_FILE)) {
			try {
				root.addAppender(new RollingFileAppender(layout, line.getOptionValue(PARAM_LOG_FILE), true));
			} catch (IOException e) {
				logger_core.error("Error creating log4j.FileAppender", e);
			}
		}
	}

	@SuppressWarnings("static-access")
	public  void initOptions(Options options) {
		// output / loglvl
		options.addOption(OptionBuilder.withArgName("logLevel").hasArg()
				.withDescription("Log level:  OFF, FATAL, ERROR, WARN, INFO, DEBUG, TRACE and ALL").create(PARAM_LOG));
		options.addOption(OptionBuilder.withArgName("logFile").hasArg()
				.withDescription("An absolute path to the log file").create(PARAM_LOG_FILE));
		// user experience
		options.addOption(OptionBuilder.withArgName("confirm").hasArg(false).isRequired(false)
				.withDescription("Confirm exit, avoids cmd line window to be closed.").create(PARAM_CONFIRM_EXIT));
		options.addOption(OptionBuilder.withArgName("help").hasArg(false).isRequired(false)
				.withDescription("Shows this help content.").create(PARAM_HELP));
	}

	public  String readInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			return br.readLine();
		} catch (IOException e) {
			logger_core.error("Error while reading input.", e);
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// silently...
			}
		}
		return null;
	}

	public  void printHelp(Options options, String app) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printUsage(new PrintWriter(System.out), 80, app, options);
		formatter.printHelp(app, options);
	}

	public  void exit(int exitCode, CommandLine cmdLine) {
		if (getConfirmExit(cmdLine)) {
			logger.info("please press enter to exit...");
			// blocks
			readInput();
		}
		logger.info("exit. ( " + exitCode + " )");
		System.exit(exitCode);
	}

	private  boolean getConfirmExit(CommandLine cmdLine) {
		if (cmdLine != null && cmdLine.hasOption(PARAM_CONFIRM_EXIT)) {
			return true;
		}
		return false;
	}

	public  CommandLine parseCmdLine(String[] args, String app, Options options) {
		CommandLine cmd = null;
		try {
			CommandLineParser parser = new BasicParser();
			cmd = parser.parse(options, args);
			configureCustomLogging(cmd);
		} catch (MissingOptionException e) {
			System.err.println(e.getMessage());
			helpAndExit(options, app);
		} catch (UnrecognizedOptionException e) {
			System.err.println(e.getMessage());
			helpAndExit(options, app);
		} catch (ParseException e) {
			e.printStackTrace();
			helpAndExit(options, app);
		}
		return cmd;
	}

	public  void helpAndExit(Options cmdOptions, String app) {
		printHelp(cmdOptions, app);
		exit(0, null);
	}

	public  boolean containsHelp(String[] args) {
		for (String string : args) {
			if(string.equals("-h"))
				return true;
		}
		return false;
	}
}
