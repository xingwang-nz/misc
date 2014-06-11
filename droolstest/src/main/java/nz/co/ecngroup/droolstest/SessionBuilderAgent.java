package nz.co.ecngroup.droolstest;

import java.net.URL;

import org.drools.KnowledgeBase;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.io.ResourceChangeScannerConfiguration;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class SessionBuilderAgent extends AbstractSessionBuilder {

	static {
		startPooling();
		// Set the interval on the ResourceChangeScannerService if the default is 60s.
		final ResourceChangeScannerConfiguration sconf = ResourceFactory.getResourceChangeScannerService().newResourceChangeScannerConfiguration();
		sconf.setProperty("drools.resource.scanner.interval", "30"); 
		ResourceFactory.getResourceChangeScannerService().configure(sconf);
	}
	
	private final KnowledgeAgent kagent;
	
	public SessionBuilderAgent(String name, String changeSetClassPath) {
		final URL changeSetURL = getClass().getClassLoader().getResource(changeSetClassPath);
		kagent = KnowledgeAgentFactory.newKnowledgeAgent(name);
		kagent.applyChangeSet(ResourceFactory.newUrlResource(changeSetURL));
	}
	
	
	@Override
	public StatefulKnowledgeSession createSession() throws Exception {
		return kagent.getKnowledgeBase().newStatefulKnowledgeSession();
	}

	@Override
	public KnowledgeBase getKnowledgeBase() {
		return kagent.getKnowledgeBase();
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
