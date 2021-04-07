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
			System.out.println("인자는 반드시 2개 또는 4개이어야 합니다.");
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
			System.out.println("기능은 -c 또는 -k이어야 합니다.");
	}
}