package plc1;

/**
 * @author Yana Stoykova
 * @id 11916925
 */
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Predicate;

public class ArticleManagement {

    private ArticleDAO dao;

	
	public ArticleManagement(ArticleDAO dao) {
		this.dao = dao;
	}

	public boolean check_if_added(Article art){
		List<Article> helpa = dao.getArticleList();
		
		return helpa.stream().anyMatch(a -> a.getId() == art.getId());
	}
	
	public List<Article> getAllData(){
		return dao.getArticleList();
	}

	public String formatGetAllData(List<Article> articles){

		return articles.stream().map(Article::toString).collect(Collectors.joining(System.lineSeparator()));

	}
	
	public Article getSpecificData(int id){
		return dao.getArticle(id);
	}
	
	public void addArticle(Article art) {
		dao.saveArticle(art);
	}
	
	public void deleteArticle(int id) {
		dao.deleteArticle(id);
	}
	
	public int totalNArticles() {
		return dao.getArticleList().size();
	}
	

	public int count(Predicate <Article> p) {
		int c = 0;
		
			for(Article a: dao.getArticleList()) {
				if(p.test(a)) c++;
			}
		return c;
	}
	
	public String meanPrice() {
		List <Article> articles = dao.getArticleList();
		
		OptionalDouble meanPrice = articles.stream().mapToDouble(Article::getPrice).average();
		
		DecimalFormat df = Article.getDecimalFormat();
		
		return meanPrice.isPresent() ? df.format(meanPrice.getAsDouble()) : df.format(0.0);
	}
	
	public List<Integer> oldestArticles(){
		
		List<Article> articles = dao.getArticleList();
		List<Integer> oldestArticles = new ArrayList<>();
		
		Article oldest = articles.get(0);
		
		for (Article art: articles) {
			if(art.getAge() > oldest.getAge()) oldest = art;
		}
		
		for (Article art: articles) {
			if(art.getAge() == oldest.getAge()) oldestArticles.add(art.getId());
		}
				
		return oldestArticles;
	}
	
}

