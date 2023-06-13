package com.mail;

import com.utils.FrameworkConfig;
import org.aeonbits.owner.ConfigFactory;

/**
 * The EmailConfig class holds the configuration for sending emails.
 * <p>
 * It provides static fields for server, port, sender, password, recipient, and subject.
 */
public class EmailConfig {

    private static FrameworkConfig frameworkConfig = ConfigFactory.create(FrameworkConfig.class);

    /**
     * The SMTP server address for sending emails.
     */
    public static final String SERVER = frameworkConfig.server();
    /**
     * The SMTP server port number for sending emails.
     */
    public static final String PORT = frameworkConfig.port();
    /**
     * The email address of the sender.
     * Fill in the appropriate value.
     */
    public static final String FROM = frameworkConfig.from_email_address();
    /**
     * The password for the sender's email account.
     * Fill in the appropriate value.
     */
    public static final String PASSWORD = frameworkConfig.password();
    /**
     * <p>
     * An array of email addresses of the recipients.
     * Fill in the appropriate values.
     */
    public static final String[] TO = frameworkConfig.to_email_addresses().split(",");
    /**
     * The subject of the email.
     * It uses the "report_title" configuration property from FrameworkConfig.
     * The default value is obtained from the framework configuration.
     */
    public static final String SUBJECT = frameworkConfig.email_subject();
}
