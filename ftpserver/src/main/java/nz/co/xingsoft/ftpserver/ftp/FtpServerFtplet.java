package nz.co.xingsoft.ftpserver.ftp;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.ftpserver.ftplet.DefaultFtplet;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.FtpReply;
import org.apache.ftpserver.ftplet.FtpRequest;
import org.apache.ftpserver.ftplet.FtpSession;
import org.apache.ftpserver.ftplet.FtpletContext;
import org.apache.ftpserver.ftplet.FtpletResult;
import org.apache.ftpserver.impl.DefaultFtpStatistics;
import org.apache.ftpserver.impl.FileObserver;
import org.springframework.stereotype.Component;

@Component("ftpServerFtplet")
public class FtpServerFtplet
    extends DefaultFtplet
{

  @Inject
  private FileObserver ftpFileObserver;

  @Override
  public void init(final FtpletContext ftpletContext)
    throws FtpException
  {
    super.init(ftpletContext);
    final DefaultFtpStatistics defaultFtpStatistics = (DefaultFtpStatistics) ftpletContext.getFtpStatistics();
    defaultFtpStatistics.setFileObserver(ftpFileObserver);
  }

  @Override
  public FtpletResult afterCommand(final FtpSession session, final FtpRequest request, final FtpReply reply)
    throws FtpException, IOException
  {
    return super.afterCommand(session, request, reply);
  }

  @Override
  public FtpletResult beforeCommand(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {
    return super.beforeCommand(session, request);
  }

  @Override
  public void destroy()
  {
    super.destroy();
  }

  @Override
  public FtpletResult onAppendEnd(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {
    return super.onAppendEnd(session, request);
  }

  @Override
  public FtpletResult onAppendStart(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {
    return super.onAppendStart(session, request);
  }

  @Override
  public FtpletResult onConnect(final FtpSession session)
    throws FtpException, IOException
  {
    return super.onConnect(session);
  }

  /**
   * Disable Delete
   */
  @Override
  public FtpletResult onDeleteEnd(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {

    return super.onDeleteEnd(session, request);

    // return FtpletResult.SKIP;
  }

  @Override
  public FtpletResult onDeleteStart(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {
    return super.onDeleteStart(session, request);
    // return FtpletResult.SKIP;
  }

  @Override
  public FtpletResult onDisconnect(final FtpSession session)
    throws FtpException, IOException
  {
    return super.onDisconnect(session);
  }

  @Override
  public FtpletResult onDownloadEnd(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {
    return super.onDownloadEnd(session, request);
  }

  @Override
  public FtpletResult onDownloadStart(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {
    return super.onDownloadStart(session, request);
  }

  @Override
  public FtpletResult onLogin(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {
    return super.onLogin(session, request);
  }

  /**
   * Disable Create Directory and Rename Operations
   */
  @Override
  public FtpletResult onMkdirEnd(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {
    return FtpletResult.SKIP;
  }

  @Override
  public FtpletResult onMkdirStart(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {
    return FtpletResult.SKIP;
  }

  @Override
  public FtpletResult onRenameEnd(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {
    return super.onRenameEnd(session, request);
    // return FtpletResult.SKIP;
  }

  @Override
  public FtpletResult onRenameStart(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {
    // return FtpletResult.SKIP;
    return super.onRenameStart(session, request);
  }

  @Override
  public FtpletResult onRmdirEnd(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {
    return FtpletResult.SKIP;
  }

  @Override
  public FtpletResult onRmdirStart(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {
    return FtpletResult.SKIP;
  }

  @Override
  public FtpletResult onSite(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {
    return null;
    // return FtpletResult.SKIP;
  }

  @Override
  public FtpletResult onUploadEnd(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {
    return validateUpload(session, request);
  }

  @Override
  public FtpletResult onUploadStart(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {
    return validateUpload(session, request);
  }

  private FtpletResult validateUpload(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {
    return super.onUploadStart(session, request);
  }

  @Override
  public FtpletResult onUploadUniqueEnd(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {
    return super.onUploadUniqueEnd(session, request);
  }

  @Override
  public FtpletResult onUploadUniqueStart(final FtpSession session, final FtpRequest request)
    throws FtpException, IOException
  {
    return super.onUploadUniqueStart(session, request);
  }

}
