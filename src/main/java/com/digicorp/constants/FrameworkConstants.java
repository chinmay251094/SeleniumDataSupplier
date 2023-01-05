package com.digicorp.constants;

import com.digicorp.utils.DateTimeUtils;
import com.digicorp.utils.FrameworkConfig;
import org.aeonbits.owner.ConfigFactory;

public final class FrameworkConstants {
    private static final String RESOURCESPATH = System.getProperty("user.dir") + "/src/test/java/com/digicorp/resources";
    private static final String CONFIGFILEPATH = RESOURCESPATH + "FrameworkConfig.properties";
    private static final String DATAPATH = RESOURCESPATH + "/Data.xlsx";
    private static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir") + "/extent-test-output/";
    private static final int WAITTIME = 10;
    private static String extentReportFilePath = "";
    private static final String RUNMANAGERSHEETNAME = "RUNMANAGER";
    private static final String DATAMANAGERNAME = "DATAMANAGER";
    private static FrameworkConfig frameworkConfig = ConfigFactory.create(FrameworkConfig.class);

    private FrameworkConstants() {
    }

    public static String getExtentReportFilePath() throws Exception {
        if (extentReportFilePath.isEmpty()) {
            extentReportFilePath = createReportPath();
        }
        return extentReportFilePath;
    }

    private static String createReportPath() throws Exception {
        if (frameworkConfig.overridereports().equalsIgnoreCase("no")) {
            return EXTENTREPORTFOLDERPATH + DateTimeUtils.getDateTime() + " Test Report.html";
        } else {
            return EXTENTREPORTFOLDERPATH + "/index.html";
        }
    }

    public static String getPropertyFilePath() {
        return CONFIGFILEPATH;
    }

    public static int getWaitTime() {
        return WAITTIME;
    }

    public static String getDataPath() {
        return DATAPATH;
    }

    public static String getRunManagerSheetName() {
        return RUNMANAGERSHEETNAME;
    }

    public static String getDataManagerSheetName() {
        return DATAMANAGERNAME;
    }
}
