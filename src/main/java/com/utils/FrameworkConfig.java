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
}