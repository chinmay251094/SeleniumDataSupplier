package com.digicorp.tests;

import com.digicorp.annotations.TeamDigicorp;
import com.digicorp.enums.Groups;
import com.digicorp.enums.SDET;
import com.digicorp.pagelayers.LoginPage;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.util.Map;

public class CreateInvoiceTest extends BaseTest {
    @TeamDigicorp(author = SDET.CHINMAY, category = Groups.SMOKE)
    @Test
    public void testCreateSendInvoice(Map<String, String> data) throws Exception {
        String title = new Faker().name().title();
        int value = new Faker().random().nextInt(500, 1000);
        int discount = new Faker().random().nextInt(2, 10);

        new LoginPage().setEmail(data.get("LoginEmail")).setPassword(data.get("LoginPassword")).clickSubmit()
                .selectInvoiceDueDate().setInvoiceTitle(title).setInvoiceValue(String.valueOf(value)).setInvoiceDiscount(String.valueOf(discount))
                .setCustomerName(data.get("CustomerName")).clickSendInvoice().selectSendInvoiceOptions(data.get("SendInvoiceOptions"))
                .takeActionToSendInvoiceOrNot("Send Invoice");
    }
}
