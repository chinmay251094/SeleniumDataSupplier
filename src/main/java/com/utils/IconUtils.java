package com.utils;

import static com.constants.FrameworkConstants.*;

public final class IconUtils {

    /**
     * Private constructor to avoid external instantiation
     */
    // We do not want anyone to create the object of this class
    private IconUtils() {
    }

    /**
     * Returns the icon name for the current browser
     *
     * @return the icon name for the current browser
     */
    public static String getBrowserIcon() {
        String browserInLowerCase = BrowserInfoUtils.getBrowserInfo().toLowerCase();
        if (browserInLowerCase.contains(ICON_BROWSER_EDGE)) {
            return ICON_BROWSER_PREFIX + ICON_BROWSER_EDGE + ICON_BROWSER_SUFFIX;
        } else {
            return ICON_BROWSER_PREFIX + browserInLowerCase + ICON_BROWSER_SUFFIX;
        }
    }

    /**
     * Returns the icon name for the current OS
     *
     * @return the icon name for the current OS
     */
    public static String getOSIcon() {

        String operSys = OSInfoUtils.getOSInfo().toLowerCase();
        if (operSys.contains("win")) {
            return ICON_OS_WINDOWS;
        } else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
            return ICON_OS_LINUX;
        } else if (operSys.contains("mac")) {
            return ICON_OS_MAC;
        }
        return operSys;
    }
}