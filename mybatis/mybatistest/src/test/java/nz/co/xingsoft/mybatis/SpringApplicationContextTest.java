package nz.co.xingsoft.mybatis;

import nz.co.xingsoft.mybatis.application.SpringApplicationContext;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@Configuration
@ContextConfiguration(classes = { SpringApplicationContext.class })
public abstract class SpringApplicationContextTest {

}
