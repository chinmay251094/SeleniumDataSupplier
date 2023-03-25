package com.tests;

import com.annotations.TestDescription;
import com.base.BaseTest;
import com.enums.Author;
import com.enums.Category;
import com.jpcomponents.Districts;
import com.jpcomponents.LeftMenuComponents;
import com.jpcomponents.ProgrameNameComponents;
import com.jppages.HomePage;
import com.jppages.LoginPage;
import com.jppages.SchoolsPage;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import com.utils.VerificationUtils;
import org.testng.annotations.Test;

import static com.jppages.HomePage.useHomePage;
import static com.jppages.LoginPage.useLoginPage;
import static com.jppages.SchoolsPage.useSchoolsPage;
import static com.utils.VerificationUtils.validate;

public class SliderTests extends BaseTest {
    private SliderTests() {
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test user is able to move slider", author = Author.CHINMAY, category = Category.SMOKE)
    void testSlider(TestDataSupplier dataSupplier) {
        int min = 20;
        int max = 85;
        useLoginPage().clickLoginModel().setUsername(dataSupplier.getUsername()).setPassword(dataSupplier.getPassword())
                .selectDistrict(Districts.ROCKETSHIP_PUBLIC_SCHOOLS).clickLogin();

        useHomePage().selectMenu(LeftMenuComponents.SCHOOLS);

        useSchoolsPage().selectProgrameName(ProgrameNameComponents.FRECKLE)
                .selectProgrameMinimumRange(min).selectProgrameMaximumRange(max).clickApplyRange();
        boolean flag = useSchoolsPage().verifyRangeApplied(min, max);
        validate(flag, true, "Test program range is applied");
    }
}
