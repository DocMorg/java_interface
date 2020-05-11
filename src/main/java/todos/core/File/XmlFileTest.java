package todos.core.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import todos.core.Outputs.Output;
import todos.core.Outputs.XmlOutput;
import todos.core.Todos.DefaultTodo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class XmlFileTest {

    private final String name = "test";
    private final String date = "11/22/63";

    @org.junit.jupiter.api.Test
    void save() {
        DefaultTodo todo = new DefaultTodo(this.name, this.date);
        Output out = null;
        try {
            out = new XmlOutput(new PrintStream(file("backup.xml")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        todo.saveTodo(out);
        out.save();
    }

    @org.junit.jupiter.api.Test
    void read() {
        List<String[]> data = new LinkedList<>();
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(file("backup.xml"));
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

                assertEquals(name.getTextContent(), this.name);
                assertEquals(date.getTextContent(), this.date);
            }
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            e.printStackTrace();
        }

    }

    private File file(String filename){
        return new File((Objects.requireNonNull(getClass().getClassLoader().
                getResource(filename)).getFile()));
    }
}