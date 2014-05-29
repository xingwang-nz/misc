package nz.co.xingsoft.ftpserver.ftp;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.filesystem.nativefs.NativeFileSystemFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.Ftplet;
import org.apache.ftpserver.listener.ListenerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyFtpServerFactory
{

  @Inject
  private FtpServerFtplet ftpServerFtplet;

  @Inject
  private FtpUserAccountManager ftpUserAccountManager;

  @Value("${ftp.server.port}")
  private int fptServerPort;

  public void initAndStart()
    throws FtpException
  {
    final FtpServerFactory serverFactory = new FtpServerFactory();

    final NativeFileSystemFactory fileSystemFactory = new NativeFileSystemFactory();
    fileSystemFactory.setCaseInsensitive(false);
    fileSystemFactory.setCreateHome(true);
    serverFactory.setFileSystem(fileSystemFactory);

    final Map<String, Ftplet> ftpletMap = new HashMap<String, Ftplet>();
    ftpletMap.put("ftpServerFtplet", ftpServerFtplet);
    serverFactory.setFtplets(ftpletMap);

    serverFactory.setUserManager(ftpUserAccountManager);

    final ListenerFactory factory = new ListenerFactory();
    factory.setPort(fptServerPort);
    serverFactory.addListener("default", factory.createListener());

    final FtpServer server = serverFactory.createServer();
    server.start();

  }
}
