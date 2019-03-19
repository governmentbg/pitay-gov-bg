package indexbg.pdoi.db;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.utils.JSFUtils;

import indexbg.pdoi.db.dao.PublicationDAO;

@Ignore
public class PublicationTest {
	static final Logger LOGGER = LoggerFactory.getLogger(PublicationTest.class);

//	public static void main(String[] args) {
//		
	@Test
	public void testSaveViaDAO() {
//		Publication pub = new Publication(null, "Test Bublikation KG", -1l, new Date(), new Date(), "Тест Анннотатион",
//				null, "Tets Full Text", null, null);
//
//		try {
//			JPA.getUtil().begin();
//
//			new PublicationDAO(-1l).save(pub);
//			JPA.getUtil().commit();
//
//			assertNotNull(pub.getId());
//
//		} catch (DbErrorException e) {
//			LOGGER.error("Грешка при запис на публикация в базата!", e);
//			e.printStackTrace();
//			JPA.getUtil().rollback();
//		} catch (Exception e) {
//			LOGGER.error("Грешка при запис на публикация в базата!", e);
//			e.printStackTrace();
//			JPA.getUtil().rollback();
//		} finally {
//			JPA.getUtil().closeConnection();
//
//		}
	}
	
	@Test
	public void testRebuildIndexes() {
		EntityManager em=JPA.getUtil().getEntityManager();
		FullTextEntityManager fullTextEntityManager=Search.getFullTextEntityManager(em);
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testQuery() {
		try {
			EntityManager em=JPA.getUtil().getEntityManager();
			FullTextEntityManager	fullTextEntityManager=org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
//		em.getTransaction().begin();
			
			// create native Lucene query using the query DSL
			// alternatively you can write the Lucene query using the Lucene query parser
			// or the Lucene programmatic API. The Hibernate Search DSL is recommended though
			QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Publication.class).get();
			Query query = qb.keyword()
			.onFields("title","annotation","fullText")
			.matching("Vasko")
			.createQuery();
			
			//wrap Lucene query in a javax.persistence.Query
			javax.persistence.Query persistanceQuery = fullTextEntityManager.createFullTextQuery(query, Publication.class);	
			
			// execute search
			List result	=persistanceQuery.getResultList();
			
//			em.getTransaction().commit();
			em.close();
			
			int a=0;
			a=1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@AfterClass
	public static void testShutDownEM() {
		JPA.getUtil().shutdown();
	}
}
