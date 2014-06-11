/**
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nz.co.ecngroup.droolstest;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.command.Command;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.StatelessKnowledgeSession;
import org.drools.runtime.rule.FactHandle;

public class RuleRunner {
	private final KnowledgeBase kbase;

	public RuleRunner(String[] rules) throws Exception {
		kbase = KnowledgeBaseFactory.newKnowledgeBase();
		final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		for (int i = 0; i < rules.length; i++) {
			String ruleFile = rules[i];
			System.out.println("Loading file: " + ruleFile);
			kbuilder.add(ResourceFactory.newClassPathResource(ruleFile), ResourceType.DRL);
		}

		if (kbuilder.hasErrors()) {
			throw new Exception(kbuilder.getErrors().toString());
		}

		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
	}

	public RuleRunner(String rules) throws Exception {
		this(new String[] { rules });
	}

	
	public void runRule(Object... facts) throws Exception {
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

		for (int i = 0; i < facts.length; i++) {
			Object fact = facts[i];
			System.out.println("Inserting fact: " + fact);
			ksession.insert(fact);
		}

		RuleResult result = new RuleResult();
		ksession.setGlobal("ruleResult", result);
		ksession.fireAllRules();
		
		
		//System.out.println(ksession.getGlobal("ruleResult"));
		
		ksession.dispose();
	}
	
	
	public <T> T runRules(Command<T> command) {
		final StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
		return ksession.execute( command );
	}
	
	

	
	
	

}
