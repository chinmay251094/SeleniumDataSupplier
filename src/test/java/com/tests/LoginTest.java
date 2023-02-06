package com.tests;

import com.annotations.TestDescription;
import com.base.BaseTest;
import com.driver.DriverManager;
import com.enums.Author;
import com.enums.Category;
import com.github.javafaker.Faker;
import com.pages.GooglePage;
import com.pages.LoginPage;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import org.testng.annotations.Test;

import static com.utils.VerificationUtils.*;

public final class LoginTest extends BaseTest {
    private LoginTest() {
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test login feature", author = Author.CHINMAY, category = Category.SANITY)
    public void loginTest(TestDataSupplier dataSupplier) {
        String urlAfterLogin = DriverManager.getDriver().getCurrentUrl();

        new LoginPage()
                .setUsername(dataSupplier.getUsername())
                .setPassword(dataSupplier.getPassword())
                .clickLogin();

        assertURL(urlAfterLogin, dataSupplier.getExpectedURL(), "Login with valid credentials");
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test whether user is not allowed to login with invalid credentials", author = {Author.CHINMAY, Author.NEELESH}, category = {Category.SMOKE, Category.REGRESSION})
    public void loginUsingInvalidCredentialsTest(TestDataSupplier dataSupplier) {
        String fakeEmail = new Faker().internet().emailAddress();
        new LoginPage()
                .setUsername(fakeEmail)
                .setPassword(dataSupplier.getPassword())
                .clickLogin();

        validate(new LoginPage().getInvalidCredentialsMessage(), "Error message Invalid Credentials is displayed");
    }
}
