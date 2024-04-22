package plc1;

import java.util.*;
import java.io.*;

public class SerializedArticleDAO implements ArticleDAO{

    private String file;
	
	public SerializedArticleDAO (String file) {
		
		if(file == null || file.isEmpty()) throw new IllegalArgumentException("Error: Invalid parameter.");
		
		this.file = file;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getArticleList() {
		File f = new File(file);
		List<Article> articles = new ArrayList<Article>(); 

		
		try {
			ObjectInputStream helpr = new ObjectInputStream(new FileInputStream(file));
			articles = (List<Article>) helpr.readObject();
			helpr.close();
		} catch (Exception e) { 
			System.err.println("Error during deserialization: " + e.getMessage());
		}
		
		return articles;
	}


	@Override
	public Article getArticle(int id) {
		List<Article> articles = getArticleList();
		boolean help = false;

		for(Article art: articles) {
			if(art.getId() == id) {
				help = true;
				return art;
				}
		}

		if(help == false) throw new IllegalArgumentException("Error: Article not found. (id=" + id + ")");
		return null;
	}



	@Override
	public void saveArticle(Article article) {
		
		List<Article> articles = getArticleList();

		try{
		
		articles.add(article);

		File f = new File(file);
		if(f.exists()) f.delete();
		
		try {
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file, true));
			writer.writeObject(articles);
			writer.close();
		} catch (Exception e) {
			System.err.println("Error during serialization: " + e.getMessage());
		}
	    } catch(IllegalArgumentException e) {System.out.println(e.getMessage());}
	}

	@Override
	public void deleteArticle(int id) {
		File f = new File(file);
		List<Article> articles = getArticleList();
		
		boolean help = articles.removeIf(a -> a.getId() == id);
		
		if(help == false) throw new IllegalArgumentException("Error: Article not found. (id=" + id + ")");
		
		f.delete();
		for(Article art: articles) this.saveArticle(art);
		
	}
}

