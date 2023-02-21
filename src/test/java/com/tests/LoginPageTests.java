package com.tests;

import com.annotations.TestDescription;
import com.base.BaseTest;
import com.driver.DriverManager;
import com.enums.Author;
import com.enums.Category;
import com.pages.LoginPage;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import org.testng.annotations.Test;

import static com.pages.LoginPage.useLoginPage;
import static com.utils.VerificationUtils.*;

public final class LoginPageTests extends BaseTest {
    private LoginPageTests() {
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test login feature", author = Author.CHINMAY, category = Category.SMOKE)
    void testLogin(TestDataSupplier dataSupplier) {
        String urlAfterLogin = DriverManager.getDriver().getCurrentUrl();

        useLoginPage()
                .setEmailAddress(dataSupplier.getUsername())
                .setPassword(dataSupplier.getPassword())
                .clickLogin();

        assertURL(urlAfterLogin, dataSupplier.getExpectedURL(), "Login with valid credentials");
    }
}
