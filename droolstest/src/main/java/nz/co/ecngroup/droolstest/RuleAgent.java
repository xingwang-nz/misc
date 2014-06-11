package nz.co.ecngroup.droolstest;

import java.net.URL;

import org.drools.KnowledgeBase;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class RuleAgent {
	
	private final String name;
	private final URL changeSetURL;
	
	private final KnowledgeAgent kagent;
	
	public RuleAgent(String name, URL changeSetURL) {
		super();
		this.name = name;
		this.changeSetURL = changeSetURL;
		kagent = KnowledgeAgentFactory.newKnowledgeAgent(this.name);
		kagent.applyChangeSet(ResourceFactory.newUrlResource(this.changeSetURL));
	}
	
	public void ruleRules(Object ... facts) {
		KnowledgeBase kbase = kagent.getKnowledgeBase();
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		for (int i = 0; i < facts.length; i++) {
			Object fact = facts[i];
			System.out.println("Inserting fact: " + fact);
			ksession.insert(fact);
		}
		ksession.fireAllRules();
		ksession.dispose();
	}
	
	public void dispose() {
		if(kagent != null) {
			kagent.dispose();
		}
	}
	
	public static void startPooling() {
		ResourceFactory.getResourceChangeNotifierService().start();
		ResourceFactory.getResourceChangeScannerService().start();
	}

	public static void stopPooling() {
		ResourceFactory.getResourceChangeNotifierService().stop();
		ResourceFactory.getResourceChangeScannerService().stop();
	}
	
	
}
