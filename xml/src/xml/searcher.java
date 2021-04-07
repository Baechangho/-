package xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilderFactory;

import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.snu.ids.kkma.ma.MorphemeAnalyzer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class searcher {

//	public static void main(String[] args) {
	public static void search(String dir, String funcForSearcher, String query) {
		File file = new File(dir);
		List<String[]> arrays = new ArrayList<String[]>();
		try{
			
//			File file = new File ("./files/index.post");
			FileInputStream fis = new FileInputStream(file);
	        InputStreamReader reader=new InputStreamReader(fis,"euc-kr");
	        BufferedReader in=new BufferedReader(reader);
	        int ch;
	        int result1st,result2nd,result3rd;
	        String str = "";
	        String[] arr1 = {};
	        List<String[]> arr2 = new ArrayList<String[]>();
	        List<String> kwrdList = new ArrayList<String>();
//	        HashSet<String> kwrdSet = new HashSet<String>();
	        List<Integer> rets = new ArrayList<Integer>();
	        
	        while((ch=in.read())!=-1){
	            str += (char)ch;
	        }

	        arr1 = str.split("\n");
//	        System.out.println(arr1[0]);
//	        System.out.println(arr1[1]);
	        HashMap hm1 = new HashMap<>();
	        for (int i = 0; i < arr1.length; i ++) {
	        	String tmp = arr1[i].replaceFirst(" ", "##");
	        	String tmpName = tmp.split("##")[0];
	        	String tmpVal = tmp.split("##")[1];
	        	String[] values = tmpVal.split(" ");
	        	HashMap hm2 = new HashMap<>();
	        	for (int j = 0; j < (values.length / 2); j ++) {
	        		int doc = Integer.valueOf(values[j*2]);
	        		double val = Double.valueOf(values[j*2 + 1]);
	        		hm2.put(doc, val);
	        	}
	        	hm1.put(tmpName, hm2);
	        }
	        System.out.println(hm1);
	        
	        
//	        for(int i = 0; i < arr1.length; i++) {
//	        	arr2.add(arr1[i].split(" "));
//	        	for(int j = 0; j <arr2.get(i).length; j++) {
//		        	System.out.println(arr2.get(i)[j]);
//	        		
//	        	}
//	        }
	        //��, �и�����, ����, �и�, ���

			// init MorphemeAnalyzer
			MorphemeAnalyzer ma = new MorphemeAnalyzer();
			// init KeywordExtractor
			KeywordExtractor ke = new KeywordExtractor();

//			String query = "��鿡�� ��, �и������� �ִ�.";
			// extract keywords
			KeywordList kl = ke.extractKeyword(query, true);

			// print result
			for( int i = 0; i < kl.size(); i++ ) {
				Keyword kwrd = kl.get(i);
//				System.out.println(kwrd.getString());
				kwrdList.add(kwrd.getString());
				ma.closeLogger();
			}
			
			Map valHm = new HashMap<>();
			double finalVal = 0;
			for (int i = 0; i < kwrdList.size(); i ++) {
				HashMap tmphm = (HashMap) hm1.get(kwrdList.get(i));
//				System.out.println(tmphm);
//				System.out.println(tmphm.get(2));
				for (int j = 1; j < 6; j ++) {
					double v = 0;
					if (tmphm.get(j) != null)
						v = (double) tmphm.get(j);
					double tmpVal = 0;
					if (valHm.get(j) != null)
						tmpVal = (double) valHm.get(j);
					finalVal = tmpVal + v;
					valHm.put(j, finalVal);
				}
				
			}
			
			List<Entry<Integer, Double>> list_entries = new ArrayList<Entry<Integer, Double>>(valHm.entrySet());

			// ���Լ� Comparator�� ����Ͽ� ���� �������� ����
			Collections.sort(list_entries, new Comparator<Entry<Integer, Double>>() {
				// compare�� ���� ��
				public int compare(Entry<Integer, Double> obj1, Entry<Integer, Double> obj2)
				{
					// ���� �������� ����
					return obj2.getValue().compareTo(obj1.getValue());
				}
			});

			List lst = new ArrayList<>();
			// ��� ���
			for(Entry<Integer, Double> entry : list_entries) {
//				System.out.println(entry.getKey() + " : " + entry.getValue());
				lst.add(entry.getKey());
			}
			System.out.println(lst);
			
			
			File folder = new File(dir);
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			docFactory.setIgnoringElementContentWhitespace(true); 
			Document document = docFactory.newDocumentBuilder().parse(folder.getParent() + File.separator + "collection.xml");
			NodeList nList = document.getElementsByTagName("doc");
			
			HashMap hm3 = new HashMap<>();
			for (int i = 0; i < nList.getLength(); i ++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					NodeList nlList = eElement.getElementsByTagName("title").item(0).getChildNodes();
				    Node nValue = (Node) nlList.item(0);
					String title = nValue.getNodeValue();
					hm3.put(i, title);
				}
			}
			
			System.out.println(hm3.get((Integer)lst.get(0) - 1));
			System.out.println(hm3.get((Integer)lst.get(1) - 1));
			System.out.println(hm3.get((Integer)lst.get(2) - 1));
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	}
	

}