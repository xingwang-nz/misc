package nz.co.xingsoft.ftpserver.application;

import javax.servlet.ServletContext;

import nz.co.xingsoft.ftpserver.ftp.MyFtpServerFactory;

import org.apache.ftpserver.ftplet.FtpException;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

public class ApplicationContextLoaderListener
    extends ContextLoaderListener
{

  @Override
  protected void configureAndRefreshWebApplicationContext(final ConfigurableWebApplicationContext wac,
    final ServletContext sc)
  {
    super.configureAndRefreshWebApplicationContext(wac, sc);
    // wac.getBean(EspressoDatabase.class).update();
    // try {
    // wac.getBean(FtpServer.class).start();
    // } catch (final FtpException e) {
    // throw new RuntimeException(e);
    // }
    final MyFtpServerFactory ftpServerFactory = wac.getBean(MyFtpServerFactory.class);
    try
    {
      ftpServerFactory.initAndStart();
    }
    catch (FtpException e)
    {
      e.printStackTrace();
    }
  }
}
