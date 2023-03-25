package com.jppages;

import com.base.BasePage;
import com.enums.Waits;
import com.jpcomponents.ProgrameNameComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;

import java.util.ArrayList;
import java.util.List;

import static com.factories.WaitFactory.waitForElements;

public class SchoolsPage<T> extends BasePage {
    private SchoolsPage() {
    }

    public static SchoolsPage useSchoolsPage() {
        return new SchoolsPage();
    }

    private static final By LIST_OF_SCHOOLS = By.xpath("//ngx-datatable//datatable-row-wrapper//div[@class='media-body']/a");
    private static final By CHECKBOX_PROGRAME_NAME = By.xpath("(//*[@id='programNameCheckBox'])[1]");
    private static final By LIST_PROGRAME_NAME = By.xpath("//ng-dropdown-panel[@role='listbox']//p");
    private static final By CHECKBOX_PROGRAME_RANGE = By.xpath("(//*[@id='progressRangeCheckBox'])[1]");
    private final String SLIDER_RANGE = "//*[@id='steps-slider']//div[@style='left: %s%%;']";
    private static final By SLIDEBAR = By.xpath("//*[@class='noUi-tooltip']");
    private static final By TXT_MIN_VALUE_RANGE = By.id("sliderValueMin");
    private static final By TXT_MAX_VALUE_RANGE = By.id("sliderValueMax");
    private static final By BTN_SUBMIT_RANGE = By.id("submitProgressRange");
    private static final By LIST_PERCENTAGE_IN_RANGE = By.xpath("//ngx-datatable//span[@class='pmd-dc-percent']");

    public SchoolsPage selectProgrameName(ProgrameNameComponents programeName) {
        waitForElements(LIST_OF_SCHOOLS, Waits.VISIBLITY);
        click(CHECKBOX_PROGRAME_NAME, Waits.CLICKABLE, "Programe Name");
        selectDesiredOption(LIST_PROGRAME_NAME, Waits.VISIBLITY, programeName.getProgrameName());
        return this;
    }

    public SchoolsPage selectProgrameMinimumRange(int minRange) {
        click(CHECKBOX_PROGRAME_RANGE, Waits.CLICKABLE, "Programe Range");
        sendKeys(TXT_MIN_VALUE_RANGE, Waits.CLICKABLE, String.valueOf(minRange), "Minimum Value");
        return this;
    }

    public SchoolsPage selectProgrameMaximumRange(int maxRange) {
        sendKeys(TXT_MAX_VALUE_RANGE, Waits.NONE, String.valueOf(maxRange), "Maximum Value");
        return this;
    }

    public SchoolsPage clickApplyRange() {
        click(BTN_SUBMIT_RANGE, Waits.NONE, "Apply Range");
        return this;
    }

    public boolean verifyRangeApplied(int minRange, int maxRange) {
        List<Integer> percentages = new ArrayList<>();

        for (WebElement element : getElements(LIST_PERCENTAGE_IN_RANGE, Waits.VISIBLITY)) {
            percentages.add(Integer.parseInt(element.getText().substring(0, 2)));
        }

        List<Integer> sortedPercentages = getSortedList(percentages);

        if (sortedPercentages.isEmpty()) {
            throw new SkipException("No percentages generated");
        }
        System.out.println("Sorted % " + sortedPercentages);
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;

        for (Integer value : sortedPercentages) {
            minValue = Math.min(minValue, value);
            maxValue = Math.max(maxValue, value);
        }
        System.out.println("Min " + minValue);
        System.out.println("Max " + maxValue);
        return (minValue >= minRange) && (maxValue <= maxRange);
    }
}
