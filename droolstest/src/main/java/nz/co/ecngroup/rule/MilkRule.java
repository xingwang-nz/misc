package nz.co.ecngroup.rule;

import nz.co.ecngroup.droolstest.fact.MilkBottle;

import org.drools.runtime.StatefulKnowledgeSession;

public class MilkRule extends AppBaseRule {

	@Override
	protected void doRunRule(StatefulKnowledgeSession session) throws Exception {
		final MilkBottle bottle = new MilkBottle();
		//bottle.setHasMilk(true);
		session.insert(bottle);
		session.fireAllRules();
	}

}
