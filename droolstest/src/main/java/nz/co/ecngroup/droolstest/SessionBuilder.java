package nz.co.ecngroup.droolstest;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;

public interface SessionBuilder {
	public StatefulKnowledgeSession createSession() throws Exception;
	
	public KnowledgeBase getKnowledgeBase();
}
