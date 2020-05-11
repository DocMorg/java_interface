package todos.core.File;

import org.xml.sax.SAXException;
import todos.core.Outputs.Output;
import todos.core.Outputs.XmlOutput;
import todos.core.Todos.DefaultTodo;
import todos.core.Todos.Todo;

import org.w3c.dom.*;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.util.Objects;

public class XmlFile implements NewFile {

    private final File file;

    public XmlFile(File file) {
        this.file = file;
    }

    @Override
    public List<String[]> read() {
        List<String[]> data = new LinkedList<>();
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(file);
            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = "/data/item";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                Element eElement = (Element) nNode;
                Element name =  (Element) eElement.getElementsByTagName("name").item(0);
                Element date =  (Element) eElement.getElementsByTagName("date").item(0);
                String[] array = {name.getTextContent(), date.getTextContent()};
                data.add(array);
            }
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void save(List<Todo> list) {
        Output xmlout = null;
        try {
            xmlout = new XmlOutput(new PrintStream(new PrintStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (Todo todo: list){
            todo.saveTodo(xmlout);
        }
        xmlout.save();
    }
}
