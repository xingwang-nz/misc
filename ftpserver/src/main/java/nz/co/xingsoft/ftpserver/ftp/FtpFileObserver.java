package nz.co.xingsoft.ftpserver.ftp;

import org.apache.ftpserver.ftplet.FtpFile;
import org.apache.ftpserver.impl.FileObserver;
import org.apache.ftpserver.impl.FtpIoSession;
import org.springframework.stereotype.Component;

@Component
public class FtpFileObserver
    implements FileObserver
{

  @Override
  public void notifyUpload(final FtpIoSession session, final FtpFile ftpFile, final long size)
  {

  }

  @Override
  public void notifyDownload(final FtpIoSession session, final FtpFile file, final long size)
  {
  }

  @Override
  public void notifyDelete(final FtpIoSession session, final FtpFile file)
  {
  }

  @Override
  public void notifyMkdir(final FtpIoSession session, final FtpFile file)
  {
  }

  @Override
  public void notifyRmdir(final FtpIoSession session, final FtpFile file)
  {
  }

}
