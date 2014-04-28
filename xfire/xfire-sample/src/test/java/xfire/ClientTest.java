package xfire;

import nz.co.xingsoft.xfire.Book;
import nz.co.xingsoft.xfire.BookService;

import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.junit.Test;

public class ClientTest {

    @Test
    public void testClient() {
        final Service serviceModel = new ObjectServiceFactory().create(BookService.class);
        // final Service serviceModel = new XmlBeansServiceFactory().create(BookService.class);

        final XFireProxyFactory factory = new XFireProxyFactory(XFireFactory.newInstance().getXFire());
        // final XFireProxyFactory factory = new XFireProxyFactory();

        final String claimURL = "http://localhost:8080/xfire/services/BookService";

        try {
            final BookService srvc = (BookService) factory.create(serviceModel, claimURL);

            final Book response = srvc.findBook("AA");
            System.out.println("Response: " + response);

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
