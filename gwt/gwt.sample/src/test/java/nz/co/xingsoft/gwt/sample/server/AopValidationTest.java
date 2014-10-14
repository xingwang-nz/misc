package nz.co.xingsoft.gwt.sample.server;

import nz.co.xingsoft.gwt.sample.services.GwtSampleService;
import nz.co.xingsoft.gwt.sample.shared.dto.UserDto;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/gwtsample/application-context.xml" })
public class AopValidationTest {
    @Autowired
    GwtSampleService gwtSampleService;

    @Test
    @Ignore
    public void testValidationAop() throws Exception {
        final UserDto dto = new UserDto();
        dto.setName("Xing");
        dto.setEmail("@x.com");
        gwtSampleService.validateGroup1("any", dto);
    }
}
