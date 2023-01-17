package com.digicorp.tests;

import com.digicorp.annotations.TeamDigicorp;
import com.digicorp.driver.DriverManager;
import com.digicorp.enums.SDET;
import com.digicorp.enums.Groups;
import com.digicorp.pagelayers.LoginPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Map;

public final class LoginTest extends BaseTest {
    private LoginTest() {
    }

    @TeamDigicorp(author = SDET.CHINMAY, category = Groups.LOGINPAGE)
    @Test
    public void testValidLogin(Map<String, String> data) throws Exception {
        new LoginPage().setEmail(data.get("LoginEmail")).setPassword(data.get("LoginPassword")).clickSubmit();

        String expected = "https://staging.filspayments.com/create-invoice";
        Assertions.assertThat(new LoginPage().getErrorMessage()).isEmpty();
        Assertions.assertThat(DriverManager.getDriver().getCurrentUrl()).isEqualTo(expected);
    }

    @TeamDigicorp(author = SDET.CHINMAY, category = Groups.LOGINPAGE)
    @Test
    public void testInvalidLogin(Map<String, String> data) throws Exception {
        new LoginPage().setEmail(data.get("LoginEmail")).setPassword(data.get("LoginPassword")).clickSubmit();

        Assertions.assertThat(new LoginPage().getErrorMessage()).isNotEmpty();
    }
}
