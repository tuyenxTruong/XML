/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmllab;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class HTTPXMLTest {
public static String numericID;

    public static void main(String[] args) {
        System.out.println("Type in ID Number");
        Scanner scanner = new Scanner(System.in) ;
        numericID =scanner.nextLine();
        
        try {
            new HTTPXMLTest().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start() throws Exception {
        URL url = new URL("http://129.32.92.49/xml/grade.php?id=" + numericID);
        URLConnection connection = url.openConnection();

        Document doc = parseXML(connection.getInputStream());
        NodeList descNodes = doc.getElementsByTagName("name");
        NodeList desGrade = doc.getElementsByTagName("grade");

        for (int i = 0; i < descNodes.getLength(); i++) {
            System.out.println(descNodes.item(i).getTextContent());
        }

        for (int i = 0; i < desGrade.getLength(); i++) {
            System.out.println(desGrade.item(i).getTextContent());
        }
    }


private Document parseXML(InputStream stream)
            throws Exception {
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        
        try {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);
        } catch (Exception ex) {
            throw ex;
        }

        return doc;
    }
}
