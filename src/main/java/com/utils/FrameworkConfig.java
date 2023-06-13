package com.utils;

import org.aeonbits.owner.Config;

/**
 * This interface provides the framework configuration properties.
 * <p>
 * It is annotated with @Config.Sources which specifies the location of the configuration file.
 */
@Config.Sources(value = "file:${user.dir}/src/test/resources/FrameworkConfig.properties")
public interface FrameworkConfig extends Config {

    /**
     * Returns the value of the "overridereports" property.
     * The default value is "no".
     *
     * @return the value of the "overridereports" property
     */
    @DefaultValue("no")
    String overridereports();

    /**
     * Returns the value of the "passedstepscreenshot" property.
     * The default value is "no".
     *
     * @return the value of the "passedstepscreenshot" property
     */
    @DefaultValue("no")
    String passedstepscreenshot();

    /**
     * Returns the value of the "skippedstepscreenshot" property.
     * The default value is "no".
     *
     * @return the value of the "skippedstepscreenshot" property
     */
    @DefaultValue("no")
    String skippedstepscreenshot();

    /**
     * Returns the value of the "failedstepscreenshot" property.
     * The default value is "no".
     *
     * @return the value of the "failedstepscreenshot" property
     */
    @DefaultValue("no")
    String failedstepscreenshot();

    /**
     * Returns the value of the "video_record" property.
     * The default value is "yes".
     *
     * @return the value of the "video_record" property
     */
    @DefaultValue("yes")
    String video_record();

    /**
     * Returns the value of the "active_page_loaded" property.
     * The default value is "yes".
     *
     * @return the value of the "active_page_loaded" property
     */
    @DefaultValue("yes")
    String active_page_loaded();

    /**
     * Returns the value of the "takescreenshots" property.
     * The default value is "yes".
     *
     * @return the value of the "takescreenshots" property
     */
    @DefaultValue("yes")
    String takescreenshots();

    /**
     * Specifies the default value for the "wait_for_element" configuration property.
     * This property determines the duration, in seconds, to wait for an element to appear on the page.
     * The default value is 20 seconds.
     */
    @DefaultValue("20")
    int wait_for_element();

    /**
     * Specifies the default value for the "failure_retries" configuration property.
     * This property determines the maximum number of retries for a failed test.
     * The default value is 1 retry.
     */
    @DefaultValue("1")
    int failure_retries();

    /**
     * Retrieves the value for the "send_email_to_users" property.
     * @return the value of the "send_email_to_users" property, or "no" if no value is specified
     */
    @DefaultValue("no")
    String send_email_to_users();

    /**
     * Specifies the default value for the "report_title" configuration property.
     * This property determines the subject of email for the test automation reports.
     * The default value is "Test Automation Reports".
     */
    @DefaultValue("Test Automation Reports")
    String email_subject();

    /**
     * Specifies the default value for the "server" configuration property.
     * This property determines the SMTP server address for sending emails.
     * The default value is "smtp.gmail.com".
     */
    @DefaultValue("smtp.gmail.com")
    String server();

    /**
     * Specifies the default value for the "port" configuration property.
     * This property determines the SMTP server port number for sending emails.
     * The default value is "587".
     */
    @DefaultValue("587")
    String port();

    /**
     * Specifies the default value for the "from_email_address" configuration property.
     * This property determines the email address of the sender.
     * The default value is "chinmay.bhagat@digi-corp.com".
     */
    @DefaultValue("from@mail.com")
    String from_email_address();

    /**
     * Retrieves the password for the email sender.
     * This value is not specified with a default value.
     */
    String password();

    /**
     * Specifies the default value for the "to_email_addresses" configuration property.
     * This property determines the email addresses of the recipients.
     * Multiple email addresses can be provided, separated by commas.
     * The default value is "vivek.navadia@digi-corp.com".
     */
    @DefaultValue("to@mail.com")
    String to_email_addresses();
}