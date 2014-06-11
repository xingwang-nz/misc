package nz.co.ecngroup.rule;

import nz.co.ecngroup.droolstest.fact.Applicant;

import org.drools.runtime.StatefulKnowledgeSession;

public class ChangeSetRule extends AppBaseRule {

	@Override
	protected void doRunRule(StatefulKnowledgeSession session) throws Exception {
		Applicant applicant = new Applicant("john", 20, "Male");
		session.insert(applicant);
		session.fireAllRules();
	}

}
