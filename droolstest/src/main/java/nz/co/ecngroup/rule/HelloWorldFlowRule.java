package nz.co.ecngroup.rule;

import nz.co.ecngroup.droolstest.fact.Applicant;

import org.drools.runtime.StatefulKnowledgeSession;

public class HelloWorldFlowRule extends AppBaseRule {

	@Override
	protected void doRunRule(StatefulKnowledgeSession session) throws Exception {
		
		session.setGlobal("dbService", dbService);
		
		final Applicant applicant = new Applicant();
		
		session.insert(applicant);
		session.insert(new Applicant());
		session.startProcess("nz.net.orcon.drooltest.helloworldflow");
		session.fireAllRules();
		System.out.println(applicant);
	}
}
