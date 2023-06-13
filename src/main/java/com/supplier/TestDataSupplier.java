package com.supplier;

import com.creditdatamw.zerocell.annotation.Column;
import com.creditdatamw.zerocell.converter.BooleanConverter;
import com.enums.Browsers;
import com.enums.RunModes;
import io.github.sskorol.data.Sheet;

/**
 * This class represents a data supplier for test data.
 * <p>
 * The data is read from an Excel sheet named "TestData".
 */
@Sheet(name = "TestData")
public class TestDataSupplier {
    @Column(name = "TestCase", index = 0)
    private String TestCase;
    @Column(name = "Description", index = 1)
    private String Description;
    @Column(name = "Browser", index = 2, converterClass = StringToBrowserType.class)
    private Browsers Browser;
    @Column(name = "Version", index = 3)
    private String Version;
    @Column(name = "URL", index = 4)
    private String URL;
    @Column(name = "Execute", index = 5, converterClass = BooleanConverter.class)
    private boolean Execute;
    @Column(name = "Mode", index = 6, converterClass = StringToRunMode.class)
    private RunModes Mode;
    @Column(name = "Headless", index = 7, converterClass = BooleanConverter.class)
    private Boolean Headless;
    @Column(name = "Username", index = 8)
    private String Username;
    @Column(name = "Password", index = 9)
    private String Password;
    @Column(name = "ExpectedURL", index = 10)
    private String ExpectedURL;

    public String getTestcase() {
        return TestCase;
    }

    public boolean isExecute() {
        return Execute;
    }

    public Browsers getBrowser() {
        return Browser;
    }

    public RunModes getMode() {
        return Mode;
    }

    public Boolean getHeadless() {
        return Headless;
    }

    public String getDescription() {
        return Description;
    }

    public String getVersion() {
        return Version;
    }

    public String getURL() {
        return URL;
    }

    public String getExpectedURL() {
        return ExpectedURL;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }
}
