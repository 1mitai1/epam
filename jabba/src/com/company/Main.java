package com.company;

import com.company.model.Fish;
import com.company.model.Reptile;
import com.company.model.Root;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Root root = new Root();

        Document doc;
        try {
            doc = buildParseDocument();
        } catch (Exception e) {
            System.out.println("Open parsing error: " + e.toString());
            return;
        }

        Node rootNode = doc.getFirstChild();

        root = parsInmate(getInmateListNode(rootNode, root), root);

        root.countClass();
        root.countCost();
        //System.out.println(root.toString());

        Document doc2 = buildDocument();
        doXML(doc2, root);
    }

    public static void doXML(Document doc, Root root) throws TransformerException {
        Element aquarium = doc.createElement("aquarium");
        doc.appendChild(aquarium);

        doNode(doc, aquarium, "name", root.getName());
        doNode(doc, aquarium, "cost", String.valueOf(root.getCost()));
        doNode(doc, aquarium, "numberOfFish", String.valueOf(root.getNumberOfFish()));
        doNode(doc, aquarium, "numberOfReptile", String.valueOf(root.getNumberOfReptiles()));

        File file2 = new File(".\\aqu.xml");
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(file2));
    }

    public static void doNode(Document doc, Element parentsNode, String name, String data) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(data));
        parentsNode.appendChild(node);
    }

    public static Document buildDocument() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.newDocument();
        } catch (Exception e) {
            System.out.println("Open parsing error: " + e.toString());
        }
        return doc;
    }

    public static Document buildParseDocument() throws Exception {
        File file = new File("aquarium.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        return dbf.newDocumentBuilder().parse(file);
    }

    public static Root parsInmate(Node inmateListNode, Root root) {
        String name = null;
        String className = null;
        Double cost = null;
        Double accessoriesCost = null;
        int number = 0;
        List<Fish> fishList = new ArrayList<>();
        List<Reptile> reptileList = new ArrayList<>();

        NodeList inmateNode = inmateListNode.getChildNodes();
        for (int i = 0; i < inmateNode.getLength(); i++) {
            if (inmateNode.item(i).getNodeType() != Node.ELEMENT_NODE
                    || !inmateNode.item(i).getNodeName().equals("inmate")) {
                continue;
            }
            NodeList inmateChildren = inmateNode.item(i).getChildNodes();
            for (int j = 0; j < inmateChildren.getLength(); j++) {
                if (inmateChildren.item(j).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                switch (inmateChildren.item(j).getNodeName()) {
                    case "name": {
                        name = inmateChildren.item(j).getTextContent();
                        break;
                    }
                    case "cost": {
                        cost = Double.valueOf(inmateChildren.item(j).getTextContent());
                        break;
                    }
                    case "accessoriesCost": {
                        accessoriesCost = Double.valueOf(inmateChildren.item(j).getTextContent());
                        break;
                    }
                    case "class": {
                        className = inmateChildren.item(j).getTextContent();
                        break;
                    }
                    case "number": {
                        number = Integer.valueOf(inmateChildren.item(j).getTextContent());
                        break;
                    }
                }
            }
            switch (className) {
                case "fish": {
                    Fish fish = new Fish();
                    fish.setName(name);
                    fish.setCost(cost);
                    fish.setNumber(number);
                    fish.setAccessoriesCost(accessoriesCost);
                    fishList.add(fish);
                    break;
                }
                case "reptile": {
                    Reptile reptile = new Reptile();
                    reptile.setName(name);
                    reptile.setCost(cost);
                    reptile.setNumber(number);
                    reptileList.add(reptile);
                    break;
                }
            }
        }
        root.setFish(fishList);
        root.setReptile(reptileList);
        return root;
    }

    public static Node getInmateListNode(Node rootNode, Root root) {
        NodeList rootChilds = rootNode.getChildNodes();
        Node inmateListNode = null;
        for (int i = 0; i < rootChilds.getLength(); i++) {

            if (rootChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            switch (rootChilds.item(i).getNodeName()) {
                case "name": {
                    root.setName(rootChilds.item(i).getTextContent());
                    break;
                }
                case "inmateList": {
                    inmateListNode = rootChilds.item(i);
                    break;
                }
            }
        }
        return inmateListNode;
    }
}
