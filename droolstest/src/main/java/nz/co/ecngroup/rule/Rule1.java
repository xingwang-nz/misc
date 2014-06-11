package nz.co.ecngroup.rule;

import nz.co.ecngroup.droolstest.fact.Applicant;
import nz.co.ecngroup.droolstest.helper.Car;
import nz.co.ecngroup.droolstest.helper.Club;
import nz.co.ecngroup.droolstest.helper.Color;

import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class Rule1 extends AppBaseRule {

	@Override
	public void doRunRule(StatefulKnowledgeSession session) throws Exception {
			
			Applicant applicant1 = new Applicant("John", 10, "Male");
			applicant1.setClub(Club.c1);
			
			Car redCar = new Car();
			redCar.setColor(Color.red);
			applicant1.setCar(redCar);
			
			session.setGlobal("dbService", dbService);
			session.insert(applicant1);
			
//			Applicant applicant2 = new Applicant("Jenny", 20, "Female");
//			session.insert(applicant2);
//			Applicant applicant3 = new Applicant("Xing", 44, "Male");
//			session.insert(applicant3);

			
			
			session.fireAllRules();

			System.out.println(applicant1);
//			System.out.println(applicant2);
//			System.out.println(applicant3);
		
	}

}
