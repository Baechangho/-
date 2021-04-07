package xml;

public class kuir {
	public static void main(String[] args) {
		String func = "";
		String dir = "";
		
		// params for searcher
		String funcForSearcher = "";
		String query = "";
		System.out.println(args);
		if (args.length == 2) {
			func = args[0];
			dir = args[1];
		}else if(args.length == 4){
			func = args[0];
			dir = args[1];
			funcForSearcher = args[2];
			query = args[3];
		}else {
			System.out.println("���ڴ� �ݵ�� 2�� �Ǵ� 4���̾�� �մϴ�.");
			System.exit(0);
		}
		
		if ("-c".equals(func))
			makeCollection.writeCollectionFile(dir);
		else if ("-k".equals(func))
			makeKeyword.writeKeyworkdFile(dir);
		else if ("-i".equals(func))
			indexer.indexer(dir);
		else if ("-s".equals(func)) 
			searcher.search(dir, funcForSearcher, query);
		else
			System.out.println("����� -c �Ǵ� -k�̾�� �մϴ�.");
	}
}