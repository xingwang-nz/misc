package nz.net.orcon.droolstest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import nz.co.ecngroup.droolstest.RuleRunner;
import nz.co.ecngroup.droolstest.fact.Applicant;

import org.drools.command.BatchExecutionCommand;
import org.drools.command.Command;
import org.drools.command.CommandFactory;
import org.drools.runtime.ExecutionResults;
import org.junit.Test;

public class RulesTest {
	

	
	@Test
	public void testLicenseRule() throws Exception {
		final RuleRunner ruleRunner = new RuleRunner("license/licenseApplication.drl");
		
		
		Applicant applicant = new Applicant("Mr John Smith", 16, "F");
		Applicant applicant2 = new Applicant("Xing", 19, "M");
		Applicant applicant3 = new Applicant("Wang", 20, "M");
		ruleRunner.runRule(applicant, applicant2, applicant3);
		assertTrue(applicant2.isValid());
		assertTrue(applicant3.isValid());
		
		
	}
	
	/*
	@Test
	public void testLicenseRule2() throws Exception {
		final RuleRunner ruleRunner = new RuleRunner("license/licenseApplication.drl");
		Applicant applicant = new Applicant("Mr John Smith", 19);
		
		final List<Command> commands = new ArrayList<Command>();
		
		Command<ExecutionResults> cmd = CommandFactory.newInsert(applicant, "applicant1"); 
		commands.add(cmd);
		BatchExecutionCommand batchExecutionCommand = CommandFactory.newBatchExecution( commands );

		ExecutionResults executionResults =  ruleRunner.runRules(batchExecutionCommand);
		
		Applicant applicant1 = (Applicant) executionResults.getValue("applicant1");
		assertTrue(applicant1.isValid());
		
		String checkResult = (String)executionResults.getValue("checkResult");
		
		System.out.println(checkResult);
		
	}
	*/
	
	
	
	/*

	@Before
	public void init() {
		RuleAgent.startPooling();
	}
	
	@After
	public void tearDown() {
		RuleAgent.stopPooling();
	}
	
	@Test
	public void testLicenseRuleAgent() {
		RuleAgent agent = new RuleAgent("LicenseAgent", getChangeSetURL("license"));
		Applicant applicant = new Applicant("Mr John Smith", 19);
		agent.ruleRules(applicant);
		System.out.println("Applicant1 ========== " + applicant.isValid());
		assertFalse(applicant.isValid());
		
		try {
			Thread.sleep(65000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Applicant applicant2 = new Applicant("Mr John Smith", 19);
		agent.ruleRules(applicant2);
		
		
		System.out.println("Applicant1 ========== " + applicant.isValid());
		System.out.println("Applicant2 ========== " + applicant2.isValid());
		
		//assertTrue(applicant.isValid());
	}

	private URL getChangeSetURL(String changeSetPackagePath) {
		return this.getClass().getClassLoader().getResource(changeSetPackagePath + "/change-set.xml");
	}
	*/
}
