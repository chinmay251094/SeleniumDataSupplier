package com.tests;

import com.annotations.TestDescription;
import com.base.BaseTest;
import com.enums.Author;
import com.enums.Category;
import com.github.javafaker.Faker;
import com.pagecomponents.LeftMenuBarComponents;
import com.pagecomponents.PIMButtonComponents;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.MyInfoPage;
import com.pages.PIMPage;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import org.testng.annotations.Test;

import static com.pages.HomePage.useHomePage;
import static com.pages.LoginPage.useLoginPage;
import static com.pages.MyInfoPage.useMyInfoPage;
import static com.pages.PIMPage.usePIMPage;

public class MyInfoTests extends BaseTest {
    private MyInfoTests() {
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test user is able to update MyInfo data", author = Author.CHINMAY, category = Category.SMOKE)
    void testMyInfoDataUpdate(TestDataSupplier dataSupplier) {
        String fName = new Faker().name().firstName();
        String lName = new Faker().name().lastName();

        useLoginPage().performLogin(dataSupplier.getUsername(), dataSupplier.getPassword());
        useHomePage().selectMenu(LeftMenuBarComponents.MY_INFO);
        useMyInfoPage().setEmployeeId();
        String employeeId = useMyInfoPage().getEmployeeId();
        useMyInfoPage().setFirstName(fName).setLastName(lName).clickSave();
        useHomePage().selectMenu(LeftMenuBarComponents.PIM);
        usePIMPage().setEmployeeId(employeeId).clickButton(PIMButtonComponents.SEARCH);
    }
}
