package xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.HashMap;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import scala.collection.Seq;
 
public class indexer {
 
	public static void indexer(String dir) {
//	public static void main(String[] args) {
		
		FileOutputStream fos = null;
		try {
//			File folder = new File("./files/index.xml");
			File folder = new File(dir);
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			docFactory.setIgnoringElementContentWhitespace(true); 
			Document document = docFactory.newDocumentBuilder().parse(folder);
			
			document.getDocumentElement().normalize();
			NodeList nList = document.getElementsByTagName("doc");
			
			List<List<String>> arylist = new ArrayList<>();
			List<String> doclist = new ArrayList<>();
			for (int i = 0; i < nList.getLength(); i ++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					NodeList nlList = eElement.getElementsByTagName("body").item(0).getChildNodes();
				    Node nValue = (Node) nlList.item(0);
					String bodyData = nValue.getNodeValue();
					
					List<String> list = new ArrayList<String>();
					String[] splitData = bodyData.split("#");
					
					for (int j = 0; j < splitData.length; j ++) {
						String dat = splitData[j].split(":")[0];
						String cnt = splitData[j].split(":")[1];
						doclist.add(dat);
						for (int k = 0; k < Integer.parseInt(cnt); k ++) {
							list.add(dat);
						}
					}
					arylist.add(list);
				}
			}
			
			TFIDFCalculator calculator = new TFIDFCalculator();
			String result = "";
			for (int i = 0; i < doclist.size(); i ++) {
				result += doclist.get(i);
				result += " - ";
				for (int j = 0; j < 5; j ++) {
					double tfidf = calculator.tfIdf(arylist.get(j), arylist, doclist.get(i));
					double roundTfidf = Math.round(tfidf * 100) / 100.0;
					if (tfidf != 0)
						result = result + " " + (j + 1) + " " + roundTfidf;
				}
				result += "\n";
			}
			
			File file = new File ("./files/index.post");
			fos = new FileOutputStream(file);
			fos.write(result.getBytes("euc-kr"));
			
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



