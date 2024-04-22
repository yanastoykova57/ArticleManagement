package plc1;

import java.util.List;
public class ArticleCLI {

    public static void main(String[] args) {

		try{
            if(args.length < 2) throw new IllegalArgumentException("Error: Invalid parameter.");
    	
    	ArticleManagement artmanage = new ArticleManagement(new SerializedArticleDAO(args[0]));
    	
    switch(args[1]) {
    		
    	case "add":
    		
    		if(args[2].equals("book")) {
    			if(args.length != 9) throw new IllegalArgumentException("Error: Invalid parameter.");

				try{
					int help = Integer.parseInt(args[6]);
					int hhelp = Integer.parseInt(args[3]);
				} catch (NumberFormatException e){
					throw new IllegalArgumentException("Error: Invalid parameter.");
				}
				
				Book newBook = new Book(Integer.parseInt(args[3]), args[4], Integer.parseInt(args[6]), args[5], Double.parseDouble(args[7]),Integer.parseInt(args[8]));
				if(artmanage.check_if_added(newBook)){
    			//artmanage.addArticle(newBook);
				throw new IllegalArgumentException("Error: Article already exists. (id=" + args[3] + ")"); 
				}else{
				artmanage.addArticle(newBook);
				System.out.println("Info: Article "+ newBook.getId() + " added.");
				}
    		}
    		else if(args[2].equals("dvd")) {
    			
				if(args.length != 10) throw new IllegalArgumentException("Error: Invalid parameter.");

				try{
					int help = Integer.parseInt(args[6]);
					int hhelp = Integer.parseInt(args[3]);
				} catch (NumberFormatException e){
					throw new IllegalArgumentException("Error: Invalid parameter.");
				}

    			DVD newDVD = new DVD(Integer.parseInt(args[3]), args[4], Integer.parseInt(args[6]), args[5], Double.parseDouble(args[7]),Integer.parseInt(args[8]), Integer.parseInt(args[9]));
				
				if(artmanage.check_if_added(newDVD)){
				throw new IllegalArgumentException("Error: Article already exists. (id=" + args[3] + ")"); 
				} else {
				artmanage.addArticle(newDVD);
				System.out.println("Info: Article "+ newDVD.getId() + " added.");
				}
    		}
    		else throw new IllegalArgumentException("Error: Invalid parameter.");
    		break;
    		
    	case "list":	
    	if(args.length == 3) {
    		int id = Integer.parseInt(args[2]);
    		System.out.println(artmanage.getSpecificData(id));
		} else System.out.println(artmanage.formatGetAllData(artmanage.getAllData()));
    	break;
    	
    	case "delete":
    		int id = Integer.parseInt(args[2]);
    		artmanage.deleteArticle(id);
    		System.out.println("Info: Article " + id + " deleted.");
    		break;
    		
    	case "count":
    		if(args.length == 3) {
    			if(args[2].equalsIgnoreCase("Book")) System.out.println(artmanage.count(a -> a instanceof Book));
    			else if(args[2].equalsIgnoreCase("dvd")) System.out.println(artmanage.count(a -> a instanceof DVD));
    		} else System.out.println(artmanage.totalNArticles());
    		break;
    
    	case "meanprice": 
    		System.out.println(artmanage.meanPrice());
    		break;
    		
    	case "oldest":
    		List<Integer> oldest = artmanage.oldestArticles();
    		for(Integer old: oldest) {
    			System.out.println("Id: "+ old);
    		}
    		break;
		
		default: 
			throw new IllegalArgumentException ("Error: Invalid parameter.");
    }
		} catch (Exception e ) {
			System.out.println(e.getMessage());
		}	

    }

}
