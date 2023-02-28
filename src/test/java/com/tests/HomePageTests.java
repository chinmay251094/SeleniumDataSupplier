package com.tests;

import com.annotations.TestDescription;
import com.base.BaseTest;
import com.enums.Author;
import com.enums.Category;
import com.github.javafaker.Faker;
import com.pagecomponents.LeftMenuBarComponents;
import com.pagecomponents.PIMButtonComponents;
import com.pagecomponents.PIMTextboxComponents;
import com.pagecomponents.ProfileComponents;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import org.testng.annotations.Test;

import static com.pages.LoginPage.useLoginPage;
import static com.pages.PIMPage.usePIMPage;
import static com.utils.VerificationUtils.validate;

public final class HomePageTests extends BaseTest {
    private HomePageTests() {
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test whether user is able to successfully logout",
            author = {Author.CHINMAY, Author.NIKHIL}, category = {Category.SMOKE, Category.SANITY})
    void testLogout(TestDataSupplier dataSupplier) {
        useLoginPage()
                .setEmailAddress(dataSupplier.getUsername())
                .setPassword(dataSupplier.getPassword())
                .clickLogin()
                .selectProfileOption(ProfileComponents.LOGOUT);

        validate(useLoginPage().isLoginPageElementPresent(), true, "User is redirected to Login page when clicks Logout");
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test whether user is able to add PIM user",
            author = Author.CHINMAY, category = {Category.SMOKE, Category.SANITY})
    void testPIMAddUser(TestDataSupplier dataSupplier) {
        String firstName = new Faker().name().firstName();
        String lastName = new Faker().name().lastName();

        useLoginPage()
                .performLogin(dataSupplier.getUsername(), dataSupplier.getPassword())
                .selectMenu(LeftMenuBarComponents.PIM);

        usePIMPage()
                .addNewUser(PIMButtonComponents.ADD, PIMTextboxComponents.FIRSTNAME, PIMTextboxComponents.LASTNAME, firstName, lastName);
    }
}
