package nz.co.xingsoft.ftpserver.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.ftpserver.ftplet.Authentication;
import org.apache.ftpserver.ftplet.AuthenticationFailedException;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.usermanager.AnonymousAuthentication;
import org.apache.ftpserver.usermanager.PasswordEncryptor;
import org.apache.ftpserver.usermanager.UsernamePasswordAuthentication;
import org.apache.ftpserver.usermanager.impl.AbstractUserManager;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.ConcurrentLoginPermission;
import org.apache.ftpserver.usermanager.impl.TransferRatePermission;
import org.apache.ftpserver.usermanager.impl.WritePermission;
import org.springframework.beans.factory.annotation.Value;

/**
 * A dummy FTP account manager only
 */
public class FtpUserAccountManager
        extends AbstractUserManager {

    private final Map<String, String> ftpAttributesMap = new HashMap<String, String>();

    private final Map<String, String> ftpUsersMap = new HashMap<String, String>();

    @Value("${ftp.users.file}")
    private File usersFile;

    @PostConstruct
    public void init() {
        try {
            this.ftpUsersMap.clear();

            if (usersFile == null || !usersFile.canRead()) {
                throw new RuntimeException(String.format("FTP users file %s does not exist or is not readable.", usersFile));
            }

            final Properties userProp = new Properties();
            try (FileInputStream inputStream = new FileInputStream(usersFile)) {
                userProp.load(inputStream);
                for (final String user : userProp.stringPropertyNames()) {
                    ftpUsersMap.put(user.toLowerCase(), userProp.getProperty(user));
                }
            }
        } catch (final Exception e) {
            throw new RuntimeException(String.format("Error occurred while init FtpUserAccountManager %s", e.getMessage()));
        }

    }

    public FtpUserAccountManager(final PasswordEncryptor passwordEncryptor) {
        super("dummyAdmin", passwordEncryptor);
    }

    @Override
    public User authenticate(final Authentication authentication)
            throws AuthenticationFailedException {
        if (authentication instanceof AnonymousAuthentication) {
            throw new AuthenticationFailedException("Anonymous login is not accepted");
        }

        if (authentication instanceof UsernamePasswordAuthentication) {
            final UsernamePasswordAuthentication usernamePasswordAuthentication = (UsernamePasswordAuthentication) authentication;

            final String username = usernamePasswordAuthentication.getUsername();

            if (!doesExist(username)) {
                throw new AuthenticationFailedException("Authentication failed: user " + username + " does not exist");
            }

            if (getPasswordEncryptor().matches(usernamePasswordAuthentication.getPassword(), getStoredPassword(username))) {
                try {
                    return getUserByName(username);
                } catch (final FtpException e) {
                    throw new AuthenticationFailedException(e);
                }
            } else {
                throw new AuthenticationFailedException("Authentication failed");
            }

        } else {
            throw new AuthenticationFailedException("Unknow Authentication type");
        }
    }

    @Override
    public boolean doesExist(final String username) {

        return StringUtils.isNotBlank(username) ? ftpUsersMap.containsKey(username.toLowerCase()) : false;
    }

    @Override
    public User getUserByName(final String username)
            throws FtpException {
        final BaseUser user = new BaseUser();
        user.setName(username);

        user.setHomeDirectory(getFtpAttribute(ATTR_HOME));

        final List<Authority> authorities = new ArrayList<Authority>();

        if (getFtpAttributeAsBoolean(ATTR_WRITE_PERM)) {
            authorities.add(new WritePermission());
        }

        final int maxLogin = getFtpAttributeAsInt(ATTR_MAX_LOGIN_NUMBER);
        final int maxLoginPerIP = getFtpAttributeAsInt(ATTR_MAX_LOGIN_PER_IP);

        authorities.add(new ConcurrentLoginPermission(maxLogin, maxLoginPerIP));

        final int uploadRate = getFtpAttributeAsInt(ATTR_MAX_UPLOAD_RATE);
        final int downloadRate = getFtpAttributeAsInt(ATTR_MAX_DOWNLOAD_RATE);

        authorities.add(new TransferRatePermission(downloadRate, uploadRate));

        user.setAuthorities(authorities);

        user.setMaxIdleTime(getFtpAttributeAsInt(ATTR_MAX_IDLE_TIME));

        return user;

    }

    @Override
    public String[] getAllUserNames()
            throws FtpException {
        return (String[]) ftpUsersMap.values().toArray();
    }

    @Override
    public void save(final User username)
            throws FtpException {
    }

    @Override
    public void delete(final String username)
            throws FtpException {
    }

    public void setFtpAttributesMap(final Map<String, String> ftpAttributesMap) {
        this.ftpAttributesMap.clear();
        this.ftpAttributesMap.putAll(ftpAttributesMap);
    }

    public void setFtpUsersMap(final Map<String, String> ftpUsersMap) {
        this.ftpUsersMap.clear();
        for (final Map.Entry<String, String> entry : ftpUsersMap.entrySet()) {
            if (StringUtils.isNotBlank(entry.getKey())) {
                this.ftpUsersMap.put(entry.getKey().toLowerCase(), entry.getValue());
            }
        }
    }

    private String getFtpAttribute(final String attribute) {
        return ftpAttributesMap.get(attribute);
    }

    private boolean getFtpAttributeAsBoolean(final String attribute) {
        return ftpAttributesMap.get(attribute) != null ? Boolean.valueOf(ftpAttributesMap.get(attribute)) : false;
    }

    private int getFtpAttributeAsInt(final String attribute) {
        return ftpAttributesMap.get(attribute) != null ? Integer.parseInt(ftpAttributesMap.get(attribute)) : 0;
    }

    private String getStoredPassword(final String username) {
        return ftpUsersMap.get(username.toLowerCase());
    }

}
