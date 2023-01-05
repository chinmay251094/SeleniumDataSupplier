package com.digicorp.utils;

import com.digicorp.driver.DriverManager;
import com.digicorp.reports.ExtentReportLogger;
import com.github.javafaker.Faker;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public final class UsefulFunctionUtils {
    private UsefulFunctionUtils() {
    }

    public static String generateName() {
        String AlphabeticalString = "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(6);

        for (int i = 0; i < 9; i++) {
            int index = (int) (AlphabeticalString.length() * Math.random());
            sb.append(AlphabeticalString.charAt(index));
        }
        return sb.toString();
    }

//	public static String generateEmail() {
//		String AlphabeticalString = "abcdefghijklmnopqrstuvxyz";
//		StringBuilder sb = new StringBuilder(9);
//
//		for (int i = 0; i < 9; i++) {
//			int index = (int) (AlphabeticalString.length() * Math.random());
//			sb.append(AlphabeticalString.charAt(index));
//		}
//		return sb.toString() + "@gmail.com";
//	}

    public static String generateEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public static String generateEmployeeId() {
        char[] chars = "0123456789".toCharArray();
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder((100000 + rnd.nextInt(900000)));
        for (int i = 0; i < 5; i++) {
            sb.append(chars[rnd.nextInt(chars.length)]);
        }
        return sb.toString();
    }

    public static String geNodeText(WebElement element) {
        String text = element.getText();
        for (WebElement child : element.findElements(By.xpath("./*"))) {
            text = text.replaceFirst(child.getText(), "");
        }
        return text;
    }

    public static void clickDropdownOption(List<WebElement> ele, String value) {
        try {
            for (int i = 0; i < ele.size(); i++) {
                String option = ele.get(i).getText();
                if (option.contains(value)) {
                    ele.get(i).click();
                    ExtentReportLogger.pass(value + " is selected", false);
                    Thread.sleep(1000);
                    break;
                }
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    public static String getRandomValue(List<String> list) {
        int randomIndex = (int) (Math.random() * list.size());
        return list.get(randomIndex);
    }

    public static List<String> getListStringValues(By ele) {

        List<String> list = new ArrayList<>();

        for (WebElement a : DriverManager.getDriver().findElements(ele)) {
            String text = a.getText();
            list.add(text);
        }
        return list;
    }

    public static void elementSynchronization() {
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
    }

    public static void dynamicElementSynchronization(int waitFor) {
        Uninterruptibles.sleepUninterruptibly(waitFor, TimeUnit.SECONDS);
    }

//	public static void sendEmailWithAttachment() {
//		EmailAttachment attachment = new EmailAttachment();
//		attachment.setPath(FrameworkConstants.getExtentReportFilepath());
//		attachment.setDisposition(EmailAttachment.ATTACHMENT);
//		attachment.setDescription("Automation Test Reports");
//
//		try {
//			MultiPartEmail email = new MultiPartEmail();
//			email.setHostName(PropertyUtils.get(ConfigProperties.HOSTNAME));
//			email.setAuthenticator(new DefaultAuthenticator(PropertyUtils.get(ConfigProperties.MAILAUTHENTICATOR),
//					PropertyUtils.get(ConfigProperties.MAILPASSWORD)));
//			email.setSSLOnConnect(true);
//			email.setSmtpPort(587);
//			email.addTo(PropertyUtils.get(ConfigProperties.MANAGER), PropertyUtils.get(ConfigProperties.MANAGERNAME));
//			email.setFrom(PropertyUtils.get(ConfigProperties.SETFROMMAIL), PropertyUtils.get(ConfigProperties.SETFROMNAME));
//			email.setSubject(PropertyUtils.get(ConfigProperties.MAILSUBJECT));
//			email.setMsg(PropertyUtils.get(ConfigProperties.MAILBODY));
//
//			// add the attachment
//			email.attach(attachment);
//
//			// send the email
//			email.send();
//		}
//		catch (EmailException ex) {
//			ex.printStackTrace();
//		}
//	}

//	public static void gmailUtils() {
//		try {
//			Properties props = System.getProperties();
//			props.setProperty("mail.store.protocol", "imap");
//			props.setProperty("mail.imaps.partialfetch", "false");
//			props.put("mail.imap.ssl.enable", "true");
//			props.put("mail.mime.base64.ignoreerrors", "true");
//			props.put("mail.smtp.socketFactory.fallback", "true");
//
//			Session session = Session.getDefaultInstance(props, null);
//			session.setDebug(true);
//			Store store = session.getStore("imap");
//			store.connect(PropertyUtils.get(ConfigProperties.HOSTNAME), 993, PropertyUtils.get(ConfigProperties.MANAGER), "MyPassword");
//
//			Folder folder = store.getFolder("INBOX");
//			folder.open(Folder.READ_WRITE);
//
//			System.out.println("Total Messages:" + folder.getMessageCount());
//			System.out.println("Unread Messages:" + folder.getUnreadMessageCount());
//
//			// creates a search criterion
//			SearchTerm searchCondition = new SearchTerm() {
//				@Override
//				public boolean match(Message message) {
//					try {
//						if (message.getSubject().contains("JetPacked Automation Test Reports")) {
//							return true;
//						}
//					} catch (MessagingException ex) {
//						ex.printStackTrace();
//					}
//					return false;
//				}
//			};
//
//			// performs search through the folder
//			Message[] messages = folder.search(searchCondition);
//
//			for (Message mail : messages) {
//				if (!mail.isSet(Flags.Flag.SEEN)) {
//
//					System.out.println("***************************************************");
//					System.out.println("MESSAGE : \n");
//
//					System.out.println("Subject: " + mail.getSubject());
//					System.out.println("From: " + mail.getFrom()[0]);
//					System.out.println("To: " + mail.getAllRecipients()[0]);
//					System.out.println("Date: " + mail.getReceivedDate());
//					System.out.println("Size: " + mail.getSize());
//					System.out.println("Flags: " + mail.getFlags());
//					System.out.println("ContentType: " + mail.getContentType());
//					System.out.println("Body: \n" + getEmailBody(mail));
//				}
//			}
//		}
//		catch (Exception exception) {
//			exception.printStackTrace();
//		}
//	}
//
//	public static String getEmailBody(Message email) throws MessagingException, IOException {
//
//		String line, emailContentEncoded;
//		StringBuffer bufferEmailContentEncoded = new StringBuffer();
//		BufferedReader reader = new BufferedReader(new InputStreamReader(email.getInputStream()));
//		while ((line = reader.readLine()) != null) {
//			bufferEmailContentEncoded.append(line);
//		}
//
//		System.out.println("**************************************************");
//
//		System.out.println(bufferEmailContentEncoded);
//
//		System.out.println("**************************************************");
//
//		emailContentEncoded = bufferEmailContentEncoded.toString();
//
//		if (email.getContentType().toLowerCase().contains("multipart/related")) {
//
//			emailContentEncoded = emailContentEncoded.substring(emailContentEncoded.indexOf("base64") + 6);
//			emailContentEncoded = emailContentEncoded.substring(0, emailContentEncoded.indexOf("Content-Type") - 1);
//
//			System.out.println(emailContentEncoded);
//
//			String emailContentDecoded = new String(Base64.getDecoder().decode(emailContentEncoded.toString().getBytes()));
//			return emailContentDecoded;
//		}
//
//		return emailContentEncoded;
//
//	}
}
