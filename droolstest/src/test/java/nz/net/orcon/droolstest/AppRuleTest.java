package nz.net.orcon.droolstest;

import nz.co.ecngroup.rule.AppRule;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/application-config.xml" })
public class AppRuleTest {
    // @Autowired
    // @Qualifier("appRule1")
    private AppRule appRule1;

    @Autowired
    @Qualifier("fromRule")
    private AppRule fromRule;

    // @Autowired
    // @Qualifier("helloWorldFlowRule")
    private AppRule helloWorldFlowRule;

    // @Autowired
    // @Qualifier("milkRule")
    private AppRule milkRule;

    @Test
    @Ignore
    public void rule1Test() throws Exception {
        appRule1.runRule();

    }

    @Test
    public void testFromRule() throws Exception {
        fromRule.runRule();
        Thread.sleep(1000);
    }

    // @Test
    public void testHelloWorldFlowRule() throws Exception {
        helloWorldFlowRule.runRule();
        Thread.sleep(1000);
    }

    // @Test
    public void testMilkRule() throws Exception {
        milkRule.runRule();
    }
}
