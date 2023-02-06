package com.constants;

import com.utils.DateTimeUtils;
import com.utils.FrameworkConfig;
import org.aeonbits.owner.ConfigFactory;

public class FrameworkConstants {
    public static final String BOLD_START = "<b>";
    public static final String BOLD_END = "</b>";
    /* TEAM_MEMBERS*/
    public static final String MANAGER = "Vivek Navadia";
    public static final String MEMBER1 = "Chinmay Bhagat";
    public static final String MEMBER2 = "Ankita Dharsandiya";
    public static final String MEMBER3 = "Neelesh Khatri";
    public static final String ICON_AUTHOR = "&#9998;";
    public static final String ICON_CATEGORY = "&#128193;";
    public static final String ICON_MANAGER = "<i class='fa fa-users' style='font-size:18px'></i>";
    public static final String ICON_TEAM = "<i class='fa fa-user-circle' style='font-size:18px'></i>";
    public static final String ICON_SMILEY_PASS = "<i class='fa fa-smile-o' style='font-size:24px'></i>";
    public static final String ICON_SMILEY_SKIP = "<i class='fas fa-frown-open'></i>";
    public static final String ICON_SMILEY_FAIL = "<i class='fa fa-frown-o' style='font-size:24px'></i>";
    public static final String ICON_OS_WINDOWS = "<i class='fa fa-windows' ></i>";
    public static final String ICON_OS_MAC = "<i class='fa fa-apple' ></i>";

    /* ICONS - START */
    public static final String ASSERTION_FOR = "Assertion for ";
    public static final String LOGS = "logs";
    public static final String ICON_OS_LINUX = "<i class='fa fa-linux' ></i>";
    public static final String ICON_Navigate_Right = "<i class='fa fa-arrow-circle-right' ></i>";
    public static final String ICON_LAPTOP = "<i class='fa fa-laptop' style='font-size:18px'></i>";
    public static final String ICON_BUG = "<i class='fa fa-bug' style='font-size:18px'></i>";
    //public static final String ICON_SOCIAL_GITHUB_PAGE_URL = "https://rajatt95.github.io/";
    public static final String ICON_SOCIAL_LINKEDIN_URL = "https://www.linkedin.com/company/digicorp-information-systems-pvt-ltd/";

//	public static final String ICON_BROWSER_OPERA = "<i class=\"fa fa-opera\" aria-hidden=\"true\"></i>";
//	public static final String ICON_BROWSER_EDGE = "<i class=\"fa fa-edge\" aria-hidden=\"true\"></i>";
	public static final String ICON_BROWSER_CHROME = "<i class=\"fa fa-chrome\" aria-hidden=\"true\"></i>";
	public static final String ICON_BROWSER_FIREFOX = "<i class=\"fa fa-firefox\" aria-hidden=\"true\"></i>";
//	public static final String ICON_BROWSER_SAFARI = "<i class=\"fa fa-safari\" aria-hidden=\"true\"></i>";
    public static final String ICON_ORGANIZATION_SITE = "https://www.digi-corp.com/";
    // public static final String ICON_SOCIAL_GITHUB_URL = "https://github.com/rajatt95";
    public static final String ICON_SOCIAL_LINKEDIN = "<a href='" + ICON_SOCIAL_LINKEDIN_URL
            + "'><i class='fa fa-linkedin-square' style='font-size:24px'></i></a>";
    public static final String ICON_CAMERA = "<i class=\"fa fa-camera\" aria-hidden=\"true\"></i>";
    public static final String ICON_BROWSER_EDGE = "edge";
    /* style="text-align:center;" */
    public static final String ICON_BROWSER_PREFIX = "<i class=\"fa fa-";
    public static final String ICON_BROWSER_SUFFIX = "\" aria-hidden=\"true\"></i>";
    private static final String RESOURCEPATH = System.getProperty("user.dir");
    //public static final String ICON_SOCIAL_GITHUB = "<a href='" + ICON_SOCIAL_GITHUB_URL
    //        + "'><i class='fa fa-github-square' style='font-size:24px'></i></a>";

    //public static final String ICON_SOCIAL_LINKEDIN = "<i class='fa fa-linkedin-square' style='font-size:24px'></i>";
//	public static final String ICON_SOCIAL_GITHUB = "<i class='fa fa-github-square' style='font-size:24px'></i>";
//	public static final String ICON_SOCIAL_LINKEDIN_VALUE = "<a href='https://www.linkedin.com/in/rajat-v-3b0685128/'>LinkedIn</a>";
//	public static final String ICON_SOCIAL_GITHUB_VALUE = "https://github.com/rajatt95";
    private static final String EXCELSHEETPATH = RESOURCEPATH + "/src/test/resources/TestingFile.xlsx";
    private static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir") + "/extent-test-output/";
    private static final String CONFIGFILEPATH = RESOURCEPATH + "FrameworkConfig.properties";
    private static String extentReportFilePath = "";
    private static FrameworkConfig frameworkConfig = ConfigFactory.create(FrameworkConfig.class);

    public static String getExcelSheetPath() {
        return EXCELSHEETPATH;
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
}
