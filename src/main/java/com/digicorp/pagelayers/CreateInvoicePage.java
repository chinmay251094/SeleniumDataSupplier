package com.digicorp.pagelayers;

import com.digicorp.enums.WaitTill;
import com.github.javafaker.Faker;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static com.digicorp.utils.UsefulFunctionUtils.elementSynchronization;
import static com.digicorp.utils.UsefulFunctionUtils.getCurrentDay;

public final class DashboardPage extends BasePage {
    private final By txtInvoiceDueDate = By.id("invoiceDueDate");
    private final By btnCalendarNext = By.id("next");
    private final By btnCalendarPrevious = By.id("previous");
    private final By datesCalendar = By.xpath("//table[@class='days weeks']/tbody//span[@class='ng-star-inserted']");
    private final By txtInvoiceTitle = By.xpath("//input[@formcontrolname='invoiceTitle']");
    private final By txtPurchaseOrder = By.xpath("//input[@formcontrolname='invoicePurchaseOrder']");
    private final By txtInvoiceValue = By.id("invalid-outline-input");
    private final By txtDiscount = By.xpath("//input[@formcontrolname='invoiceDiscountValue']");
    private final By listCustomerNames = By.xpath("//ng-dropdown-panel[@aria-label='Options list']//span");
    private final By btnSendInvoice = By.xpath("(//button[normalize-space()='Send Invoice'])[1]");
    private final By listOptionsToSendInvoice = By.xpath("(//button[normalize-space()='Send Invoice'])[1]/following::div[@class='dropdown-menu show']/a");

    public DashboardPage selectInvoiceDueDate() throws Exception {
        elementSynchronization();
        click(txtInvoiceDueDate, WaitTill.VISIBLE, "Invoice Due-Date Calendar");

        int btnClicks = new Faker().random().nextInt(0, 1);
        int date = new Faker().random().nextInt(getCurrentDay(), 31);

        if(btnClicks == 0) {
            selectOptions(fetchElements(datesCalendar), String.valueOf(date));
        }

        for (int i = 0; i < btnClicks; i++) {
            click(btnCalendarNext, WaitTill.NONE, "Calendar Next-Button");
        }

        selectOptions(fetchElements(datesCalendar), String.valueOf(date));
        elementSynchronization();
        return this;
    }
}
