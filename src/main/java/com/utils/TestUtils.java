package com.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * A utility class containing various useful methods for testing.
 */
public class TestUtils {

    /**
     * Parses an XML file containing string key-value pairs and returns a map containing the keys and values.
     *
     * @param file an InputStream object containing the XML file to parse
     * @return a HashMap object containing the string key-value pairs
     * @throws Exception if an error occurs during parsing
     */
    static public HashMap<String, String> parseStringXML(InputStream file) throws Exception {
        HashMap<String, String> stringMap = new HashMap<String, String>();

        // Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Build Document
        Document document = builder.parse(file);

        // Normalize the XML Structure; It's just too important !!
        document.getDocumentElement().normalize();

        // Here comes the root node
        Element root = document.getDocumentElement();

        // Get all elements
        NodeList nList = document.getElementsByTagName("string");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;

                // Store each element key value in map
                // stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
                stringMap.put(eElement.getAttribute("name"), eElement.getAttribute("value"));
            }
        }
        return stringMap;
    }

    /**
     * Returns the current date and time in the format "yyyy-MM-dd-HH-mm-ss".
     *
     * @return a string representing the current date and time
     */
    public static String dateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /*
     * public static void log(String txt) { BaseTest base = new BaseTest(); String
     * msg = Thread.currentThread().getId() + ":" + base.getPlatform() + ":" +
     * base.getDeviceName() + ":" +
     * Thread.currentThread().getStackTrace()[2].getClassName() + ":" + txt;
     *
     * System.out.println(msg);
     *
     * String strFile = "logs" + File.separator + base.getPlatform() + "_" +
     * base.getDeviceName() + File.separator + base.getDateTime();
     *
     * File logFile = new File(strFile);
     *
     * if (!logFile.exists()) { logFile.mkdirs(); }
     *
     * FileWriter fileWriter = null; try { fileWriter = new FileWriter(logFile +
     * File.separator + "log.txt", true); } catch (IOException e) { // TODO
     * Auto-generated catch block e.printStackTrace(); } PrintWriter printWriter =
     * new PrintWriter(fileWriter); printWriter.println(msg); printWriter.close(); }
     */

    /**
     * Returns a logger object for the current class.
     *
     * @return A Logger object for the class of the calling method.
     */
    public static Logger log() {
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }
}