package nz.co.ecngroup.rule;

import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import nz.co.ecngroup.droolstest.SessionBuilder;
import nz.co.ecngroup.service.DbService;

public abstract class AppBaseRule implements AppRule {
	
	
	@Override
	public void runRule() throws Exception {
		final StatefulKnowledgeSession session = sessionBuilder.createSession();
		//KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(session, "test");
		try {
			doRunRule(session);
		}finally {
			if (session != null) {
				session.dispose();
			}
		}
		
	}
	
	protected abstract void doRunRule(StatefulKnowledgeSession session) throws Exception;
	
	protected SessionBuilder sessionBuilder;
	public void setSessionBuilder(SessionBuilder sessionBuilder) {
		this.sessionBuilder = sessionBuilder;
	}
	
	protected DbService dbService;
	public void setDbService(DbService dbService) {
		this.dbService = dbService;
	}
}
