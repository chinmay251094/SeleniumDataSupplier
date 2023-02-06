package com.utils;

import org.aeonbits.owner.Config;

@Config.Sources(value = "file:${user.dir}/src/test/resources/FrameworkConfig.properties")
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
