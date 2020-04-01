package parser;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLParser {

    private static final Logger logger = LogManager.getLogger(XMLParser.class);

    public String getValueFromXML(String path, String tagName){

        File file = new File(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error(e.getMessage());
        }
        Document document = null;
        try {
            document = documentBuilder.parse(file);
        } catch (SAXException | IOException e) {
            logger.error(e.getMessage());
        }
        logger.info("Getting info from the " + path + " about " + tagName);
        return document.getElementsByTagName(tagName).item(0).getTextContent();
    }
}