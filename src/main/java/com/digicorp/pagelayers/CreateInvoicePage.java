package com.digicorp.pagelayers;

import com.digicorp.driver.DriverManager;
import com.digicorp.enums.WaitTill;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.digicorp.utils.UsefulFunctionUtils.*;

public final class CreateInvoicePage extends BasePage {
    private final By txtInvoiceDueDate = By.id("invoiceDueDate");
    private final By btnCalendarNext = By.id("next");
    private final By btnCalendarPrevious = By.id("previous");
    private final By datesCalendar = By.xpath("//table[@class='days weeks']/tbody//span[@class='ng-star-inserted']");
    private final By txtInvoiceTitle = By.xpath("//input[@formcontrolname='invoiceTitle']");
    private final By txtPurchaseOrder = By.xpath("//input[@formcontrolname='invoicePurchaseOrder']");
    private final By txtInvoiceValue = By.id("invalid-outline-input");
    private final By txtDiscount = By.xpath("//input[@formcontrolname='invoiceDiscountValue']");
    private final By txtCustomerNames = By.xpath("(//div[text()='Customer Name']/following::input[@role='combobox'])[1]");
    private final By listCustomerNames = By.xpath("//ng-dropdown-panel[@aria-label='Options list']//span");
    private final By btnSendInvoice = By.xpath("(//button[normalize-space()='Send Invoice'])[1]");
    private final By listOptionsToSendInvoice = By.xpath("//div[@class='dropdown-menu show']/a/span[text()='Email' or text()='SMS' or text()='WhatsApp' or text()='Invoice Link']");
    private final String btnConfirmSendInvoiceAction = "//div[@class='modal-footer justify-content-start mt-2']/button[text()='%s']";

    public CreateInvoicePage selectInvoiceDueDate() throws Exception {
        click(txtInvoiceDueDate, WaitTill.VISIBLE, "Invoice Due-Date Calendar");

        int btnClicks = new Faker().random().nextInt(0, 1);

        if (btnClicks == 0) {
            selectDesiredOption(datesCalendar, String.valueOf(new Faker().random().nextInt(getCurrentDay(), 31)));
        } else {
            for (int i = 0; i < btnClicks; i++) {
                click(btnCalendarNext, WaitTill.NONE, "Calendar Next-Button");
            }
            selectDesiredOption(datesCalendar, String.valueOf(new Faker().random().nextInt(1, 31)));
        }
        elementSynchronization();
        return this;
    }

    public CreateInvoicePage setInvoiceTitle(String value) throws Exception {
        sendKeys(txtInvoiceTitle, value, WaitTill.NONE, "Invoice Title");
        return this;
    }

    public CreateInvoicePage setPurchaseOrder(String value) throws Exception {
        sendKeys(txtPurchaseOrder, value, WaitTill.NONE, "Purchase Order");
        return this;
    }

    public CreateInvoicePage setInvoiceValue(String value) throws Exception {
        sendKeys(txtInvoiceValue, value, WaitTill.NONE, "Invoice Value");
        return this;
    }

    public CreateInvoicePage setInvoiceDiscount(String value) throws Exception {
        sendKeys(txtDiscount, value, WaitTill.NONE, "Invoice Discount");
        return this;
    }

    public CreateInvoicePage setCustomerName(String value) throws Exception {
        click(txtCustomerNames, WaitTill.NONE, "Customer Name");
        for (int i = 0; i < 3; i++) {
            fetchElement(txtCustomerNames, WaitTill.NONE).sendKeys(value.substring(i, i + 1));
            dynamicElementSynchronization(1);
        }
        selectDesiredOption(listCustomerNames, fetchElements(listCustomerNames, WaitTill.PRESENCE).get(0).getText());
        return this;
    }

    public CreateInvoicePage clickSendInvoice() throws Exception {
        click(btnSendInvoice, WaitTill.NONE, "Send Invoice");
        return this;
    }

    public CreateInvoicePage selectSendInvoiceOptions(String value) throws Exception {
        selectDesiredOption(listOptionsToSendInvoice, value);
        return this;
    }

    public CreateInvoicePage takeActionToSendInvoiceOrNot(String btnChoice) throws Exception {
        String xpath = String.format(btnConfirmSendInvoiceAction, btnChoice);
        click(generateElementUsingDynamicXpath(WaitTill.VISIBLE, xpath), WaitTill.VISIBLE, btnChoice);
        return this;
    }
}
