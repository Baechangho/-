package xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class makeKeyword {
	public static void writeKeyworkdFile(String dir) {
//	public static void main(String[] args) {
		FileOutputStream fos = null;		
		try {
//			File folder = new File("./files/collection.xml");
			File folder = new File(dir);
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			docFactory.setIgnoringElementContentWhitespace(true);
			Document document = docFactory.newDocumentBuilder().parse(folder);
			
			document.getDocumentElement().normalize();
			NodeList nList = document.getElementsByTagName("doc");
			
			KeywordExtractor ke = new KeywordExtractor();
			for (int i = 0; i < nList.getLength(); i ++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					NodeList nlList = eElement.getElementsByTagName("body").item(0).getChildNodes();
				    Node nValue = (Node) nlList.item(0);
					String bodyData = nValue.getNodeValue();

					KeywordList kl = ke.extractKeyword(bodyData, true);
					String result0 = "";
					for (int j = 0; j < kl.size(); j ++) {
						Keyword kwrd = kl.get(j);
						result0 += kwrd.getString() + ":" + kwrd.getCnt() + "#";
					}
				    nValue.setTextContent(result0);
				}
			}
			
			File collectionFile = new File("./files/index.xml");
			fos = new FileOutputStream(collectionFile);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(document);
			StreamResult result =  new StreamResult(fos);
			transformer.transform(source, result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}