package xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class makeCollection {
	public static void writeCollectionFile(String dir) {
		FileOutputStream fos = null;		
		try {
//			File folder = new File("C:\\Users\\BaeChangHo\\Downloads\\2���� �ǽ� html\\2���� �ǽ� html");
			File folder = new File(dir);
			int count = 0;
			
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	
			Document document = docBuilder.newDocument();
			
			// docs element
			Element docs = document.createElement("docs");
			document.appendChild(docs);
			
			for (File f: folder.listFiles()) {
				StringBuilder contentBuilder = new StringBuilder();
				try {
				    BufferedReader in = new BufferedReader(new FileReader(f.getAbsoluteFile()));
				    String str;
				    while ((str = in.readLine()) != null) {
				        contentBuilder.append(str);
				    }
				    in.close();
				} catch (IOException e) {
				}
				String content = contentBuilder.toString();
				
				org.jsoup.nodes.Document docum = Jsoup.parse(content);
				
				String title = docum.select("title").toString().trim();
				String titleData = title.substring(7, title.length() - 8);
				
//				String meta = docum.select("meta").toString();
				String pdata = "";
				
				for (org.jsoup.nodes.Element pd: docum.select("p")) {
					String tmpPd = pd.toString();
					tmpPd = tmpPd.substring(3, tmpPd.length() - 4);
					pdata += tmpPd;
				}

				// doc element
				Element doc = document.createElement("doc");
				docs.appendChild(doc);
				doc.setAttribute("id", String.valueOf(count));
				count++;
				
				// title element
				Element title2 = document.createElement("title");
				title2.appendChild(document.createTextNode(titleData));
				doc.appendChild(title2);
				
				// body element
				Element body2 = document.createElement("body");
				body2.appendChild(document.createTextNode(pdata));
				doc.appendChild(body2);
				
			}
			
			File collectionFile = new File("./files/collection.xml");
			fos = new FileOutputStream(collectionFile);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
//			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
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