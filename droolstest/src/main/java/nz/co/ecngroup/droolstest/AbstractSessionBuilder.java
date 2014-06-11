package nz.co.ecngroup.droolstest;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;

public abstract class AbstractSessionBuilder implements SessionBuilder {

	// Whether drools output is logged to console
	protected boolean logToConsole = false;
	// The file to log drools output to - by default null value will not log
	protected String logFile;

	/**
	 * Sets whether Drools output is logged to console.
	 * @param logToConsole whether we should log to console.
	 */
	public void setLogToConsole(boolean logToConsole) {
		this.logToConsole = logToConsole;
	}

	/**
	 * Sets the log file for Drools output.
	 * @param logFile the log file. Can be <code>null</code> in which case 
	 * output is not logged.
	 */
	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}
}
