package nz.co.ecngroup.droolstest;

import java.util.List;

import org.apache.log4j.Logger;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.RuntimeDroolsException;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * Class for all services that require a Drools session.
 */
public class SessionBuilderImpl extends AbstractSessionBuilder {
	private static final Logger log = Logger.getLogger(SessionBuilderImpl.class);
	// All the rule files to load
	private final List<String> ruleFiles;
	// All the process flow files to load
	private final List<String> flowFiles;
	
	private final KnowledgeBase kbase;
	
	
	/**
	 * Create the session builder.
	 * @param flowFiles all the drools flow files to add to the session knowledge.
	 * @param ruleFiles all the drools rule files to add to the session knowledge.
	 * @throws Exception 
	 */
	public SessionBuilderImpl(List<String> flowFiles, 
			  			  List<String> ruleFiles) throws Exception {
		this.flowFiles = flowFiles;
		this.ruleFiles = ruleFiles;
		
		kbase = loadRuleBase();
	}
	
	/**
	 * Create the drools session with all the flow and rule files.
	 * @return a new session.
	 * @throws Exception
	 */
	@Override
	public StatefulKnowledgeSession createSession() throws Exception {
		
		final StatefulKnowledgeSession session = kbase.newStatefulKnowledgeSession();
		if (logToConsole) {
			KnowledgeRuntimeLoggerFactory.newConsoleLogger(session);
		}
		if (logFile != null) {
			// Default log to file in separate thread every 10 seconds
			KnowledgeRuntimeLoggerFactory.newThreadedFileLogger(session, logFile, 500);
			//KnowledgeRuntimeLoggerFactory.newFileLogger(session, logFile);
		}
		return session;
	}
	
	@Override
	public KnowledgeBase getKnowledgeBase() {
		return kbase;
	}

	/**
	 * Adds all the flow and rule files specified. Subclasses can override for
	 * more control.
	 * @param builder the builder to add resources to.
	 */
	protected void addRuleResources(KnowledgeBuilder builder) {
		for (String flowFile : flowFiles) {
			builder.add(ResourceFactory.newClassPathResource(flowFile), 
						ResourceType.DRF);
		}
		for (String ruleFile : ruleFiles) {
			builder.add(ResourceFactory.newClassPathResource(ruleFile), 
						ResourceType.DRL);
		}
	}
	
	/**
	 * Builds a knowledge base by adding to resources to a builder.
	 * @return the knowledge base.
	 * @throws Exception
	 */
	private KnowledgeBase loadRuleBase() throws Exception {
		final KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		addRuleResources(builder);
		if (builder.hasErrors()) {
			for (KnowledgeBuilderError error : builder.getErrors()) {
				System.out.println(error.getMessage());
				log.error(error.getMessage());
			}
			throw new RuntimeDroolsException();
		}
		final KnowledgeBaseConfiguration conf = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
		final KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(conf);
		kbase.addKnowledgePackages(builder.getKnowledgePackages());
		return kbase;
	}

	
}
