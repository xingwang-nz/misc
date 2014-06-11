package nz.net.orcon.droolstest;

import nz.co.ecngroup.rule.AppRule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/application-config.xml" })
public class ChangeSetTest {
	
	@Autowired
	@Qualifier("changeSetRule")
	private AppRule changeSetRule;
	
	@Autowired
	@Qualifier("changeSetFlow")
	private AppRule changeSetFlow;
	
	
	
	//@Test
	public void testChangeSetRule() throws Exception {
		changeSetRule.runRule();
		Thread.sleep(40000);
		changeSetRule.runRule();
	}
	
	
	@Test
	public void testChangeSetFlow() throws Exception {
		changeSetFlow.runRule();
		Thread.sleep(40000);
		changeSetFlow.runRule();
	}
	
}
