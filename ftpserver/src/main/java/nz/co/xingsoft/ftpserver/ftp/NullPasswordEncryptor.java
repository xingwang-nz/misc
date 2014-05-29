package nz.co.xingsoft.ftpserver.ftp;

import org.apache.ftpserver.usermanager.PasswordEncryptor;

public class NullPasswordEncryptor
    implements PasswordEncryptor
{

  @Override
  public String encrypt(String s)
  {
    return s;
  }

  @Override
  public boolean matches(String s, String s1)
  {
    return s.equals(s1);
  }

}
