package xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

//import javax.xml.parsers.*;
//import javax.xml.xpath.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.jsoup.Jsoup;
import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
//import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class makeKeyword {
	public static void writeKeyworkdFile(String dir) {
//	public static void main(String[] args) {
		FileOutputStream fos = null;		
		try {
			File folder = new File("./files/collection.xml");
//			File folder = new File(dir);
//			int count = 0;
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			Document document = docFactory.newDocumentBuilder().parse(folder);
			
			XPathFactory xpf = XPathFactory.newInstance();
			XPath xpath = xpf.newXPath();
			
			XPathExpression exp0 = xpath.compile("//docs/doc[@id=0]/body");
			XPathExpression exp1 = xpath.compile("//docs/doc[@id=1]/body");
			XPathExpression exp2 = xpath.compile("//docs/doc[@id=2]/body");
			XPathExpression exp3 = xpath.compile("//docs/doc[@id=3]/body");
			XPathExpression exp4 = xpath.compile("//docs/doc[@id=4]/body");

			NodeList nodes0 = (NodeList) exp0.evaluate(document, XPathConstants.NODE);
			NodeList nodes1 = (NodeList) exp1.evaluate(document, XPathConstants.NODE);
			NodeList nodes2 = (NodeList) exp2.evaluate(document, XPathConstants.NODE);
			NodeList nodes3 = (NodeList) exp3.evaluate(document, XPathConstants.NODE);
			NodeList nodes4 = (NodeList) exp4.evaluate(document, XPathConstants.NODE);
			
			String result0 = "";
			for (int i = 0; i < nodes0.getLength(); i++) {
				String body0 = nodes0.item(i).getTextContent();
				KeywordExtractor ke = new KeywordExtractor();
				KeywordList kl = ke.extractKeyword(body0, true);
				for (int j = 0; j < kl.size(); j ++) {
					Keyword kwrd = kl.get(j);
					result0 += kwrd.getString() + ":" + kwrd.getCnt() + "#";
				}
				System.out.println(result0);
			}
			
			String result1 = "";
			for (int i = 0; i < nodes1.getLength(); i++) {
				String body1 = nodes1.item(i).getTextContent();
				KeywordExtractor ke = new KeywordExtractor();
				KeywordList kl = ke.extractKeyword(body1, true);
				for (int j = 0; j < kl.size(); j ++) {
					Keyword kwrd = kl.get(j);
					result1 += kwrd.getString() + ":" + kwrd.getCnt() + "#";
				}
				System.out.println(result1);
			}
			String result2 = "";
			for (int i = 0; i < nodes0.getLength(); i++) {
				String body2 = nodes2.item(i).getTextContent();
				KeywordExtractor ke = new KeywordExtractor();
				KeywordList kl = ke.extractKeyword(body2, true);
				for (int j = 0; j < kl.size(); j ++) {
					Keyword kwrd = kl.get(j);
					result2 += kwrd.getString() + ":" + kwrd.getCnt() + "#";
				}
				System.out.println(result2);
			}
			String result3 = "";
			for (int i = 0; i < nodes3.getLength(); i++) {
				String body3 = nodes3.item(i).getTextContent();
				KeywordExtractor ke = new KeywordExtractor();
				KeywordList kl = ke.extractKeyword(body3, true);
				for (int j = 0; j < kl.size(); j ++) {
					Keyword kwrd = kl.get(j);
					result3 += kwrd.getString() + ":" + kwrd.getCnt() + "#";
				}
				System.out.println(result3);
			}
			String result4 = "";
			for (int i = 0; i < nodes4.getLength(); i++) {
				String body4 = nodes4.item(i).getTextContent();
				KeywordExtractor ke = new KeywordExtractor();
				KeywordList kl = ke.extractKeyword(body4, true);
				for (int j = 0; j < kl.size(); j ++) {
					Keyword kwrd = kl.get(j);
					result4 += kwrd.getString() + ":" + kwrd.getCnt() + "#";
				}
				System.out.println(result4);
			}
			
			
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document documentt = docBuilder.newDocument();
			
			// docs element
			Element docs = documentt.createElement("docs");
			documentt.appendChild(docs);
						
			// doc element
			Element doc = documentt.createElement("doc");
			docs.appendChild(doc);
			doc.setAttribute("id", "0");
			
			// title element
			Element title2 = documentt.createElement("title");
			title2.appendChild(documentt.createTextNode("떡"));
			doc.appendChild(title2);
			
			// body element
			Element body2 = documentt.createElement("body");
			body2.appendChild(documentt.createTextNode(result0));
			doc.appendChild(body2);			
			
			
			// doc element
			Element doc3 = documentt.createElement("doc");
			docs.appendChild(doc);
			doc.setAttribute("id", "0");
			
			// title element
			Element title3 = documentt.createElement("title");
			title3.appendChild(documentt.createTextNode("라면"));
			doc.appendChild(title3);
			
			// body element
			Element body3 = documentt.createElement("body");
			body3.appendChild(documentt.createTextNode(result1));
			doc.appendChild(body3);	
			
			
			
			// doc element
			Element doc4 = documentt.createElement("doc");
			docs.appendChild(doc);
			doc.setAttribute("id", "0");
			
			// title element
			Element title4 = documentt.createElement("title");
			title4.appendChild(documentt.createTextNode("아이스크림"));
			doc.appendChild(title4);
			
			// body element
			Element body4 = documentt.createElement("body");
			body4.appendChild(documentt.createTextNode(result2));
			doc.appendChild(body4);	
			
			
			
			
			
			// doc element
			Element doc5 = documentt.createElement("doc");
			docs.appendChild(doc);
			doc.setAttribute("id", "0");
			
			// title element
			Element title5 = documentt.createElement("title");
			title5.appendChild(documentt.createTextNode("초밥"));
			doc.appendChild(title5);
			
			// body element
			Element body5 = documentt.createElement("body");
			body5.appendChild(documentt.createTextNode(result3));
			doc.appendChild(body5);	
			
			// doc element
			Element doc6 = documentt.createElement("doc");
			docs.appendChild(doc);
			doc.setAttribute("id", "0");
			
			// title element
			Element title6 = documentt.createElement("title");
			title6.appendChild(documentt.createTextNode("파스타"));
			doc.appendChild(title6);
			
			// body element
			Element body6 = documentt.createElement("body");
			body6.appendChild(documentt.createTextNode(result4));
			doc.appendChild(body6);	
			
			
			
			
			
			File collectionFile = new File("./files/keyword.xml");
			fos = new FileOutputStream(collectionFile);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
//			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(documentt);
			StreamResult result =  new StreamResult(fos);
			transformer.transform(source, result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			try {
//				fos.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
	}
}
