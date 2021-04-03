package xml;

public class kuir {
	public static void main(String[] args) {
		String func = "";
		String dir = "";
		
		if (args.length != 2) {
			System.out.println("인자는 반드시 2개이어야 합니다.");
			System.exit(0);
		} else {
			func = args[0];
			dir = args[1];
		}
		
		if ("-c".equals(func))
			makeCollection.writeCollectionFile(dir);
		else if ("-k".equals(func))
			makeKeyword.writeKeyworkdFile(dir);
		else if ("-i".equals(func))
			indexer.indexer(dir);
		else
			System.out.println("기능은 -c 또는 -k이어야 합니다.");
	}
}