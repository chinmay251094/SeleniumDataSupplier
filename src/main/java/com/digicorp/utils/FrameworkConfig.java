package com.digicorp.utils;

import org.aeonbits.owner.Config;

@Config.Sources(value = "file:${user.dir}/src/test/java/com/digicorp/resources/FrameworkConfig.properties")
public interface FrameworkConfig extends Config {
    @DefaultValue("no")
    String overridereports();

    @DefaultValue("no")
    String passedstepscreenshot();

    @DefaultValue("no")
    String skippedstepscreenshot();

    @DefaultValue("no")
    String failedstepscreenshot();
}
