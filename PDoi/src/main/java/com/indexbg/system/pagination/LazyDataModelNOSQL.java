package com.indexbg.system.pagination;

import com.indexbg.ocr.dao.ApplicationTree;
import com.indexbg.system.db.JPA;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.*;

public class LazyDataModelNOSQL extends LazyDataModel<Object[]> implements Serializable {

	private final SelectMetadata searchMetaData;
	private final String defaultSortColumn;

	public LazyDataModelNOSQL(SelectMetadata smd, String defaultSortColumn) {
		this.searchMetaData = smd;
		this.defaultSortColumn  = defaultSortColumn;
		load(1,15,defaultSortColumn, SortOrder.ASCENDING, Collections.emptyMap());
	}

	@Override
	public List<Object[]> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		SortMeta sortMeta = new SortMeta(null, sortField, sortOrder, null);
		return load(first,pageSize, Arrays.asList(sortMeta),filters);

	}

	@Override
	public List<Object[]>load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String,Object> filters) {
		EntityManager em = null;

		try {
			em = JPA.getUtil().getEntityManager();
			System.out.println(em.getTransaction().isActive());

			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
			QueryBuilder qb = fullTextEntityManager.getSearchFactory()
					.buildQueryBuilder().forEntity(ApplicationTree.class).get();

			org.apache.lucene.search.Query query = null;
			String textToSearchFor = (String) searchMetaData.getSqlParameters().get("text");
			textToSearchFor = (textToSearchFor==null)?"":textToSearchFor;

			BooleanJunction<BooleanJunction> bool = qb.bool();
			if (!textToSearchFor.equals("")) {
				bool
						.must(
								qb.keyword().fuzzy().withPrefixLength(1).withEditDistanceUpTo(1)
										//				.onFields("fullNames", "status", "request")
										.onField("fullNames").andField("request").boostedTo(1).andField("add_info").andField("attachments.files.text.contentText")
										.matching(textToSearchFor).createQuery()
						);
			}
			Date dateVal = (Date) searchMetaData.getSqlParameters().get("dateFrom");
			if (dateVal!=null ) {

				bool.must(qb.range().onField("registrationDate")
						.above(dateVal)
						.createQuery()
				);
			}
			dateVal = (Date) searchMetaData.getSqlParameters().get("dateTo");
			if (dateVal!=null) {
				bool.must(qb.range().onField("registrationDate")
						.below(dateVal)
						.createQuery()
				);
			}

			Long longVal = (Long) searchMetaData.getSqlParameters().get("status");
			if (longVal!=null ) {
				bool.must(qb.keyword().onField("status").matching(longVal).createQuery());
			}
			ArrayList<Long> arrayVal = (ArrayList<Long>) searchMetaData.getSqlParameters().get("selectedThemas");
			if (arrayVal!=null && !arrayVal.isEmpty()) {
				BooleanJunction inner = qb.bool();
				for (Long localVal:
				     arrayVal) {
					 inner = inner.should(qb.keyword().onField("themethemeValue").matching(localVal).createQuery());
				}
				bool.must(inner.createQuery());
			}

			String stringVal = (String) searchMetaData.getSqlParameters().get("nomer");
			if (stringVal!=null && !stringVal.equals("")){
				bool.must(qb.keyword().onField("applicationUri").matching(longVal).createQuery());
			}

			longVal = (Long) searchMetaData.getSqlParameters().get("responseSubj");
			if (longVal!=null ) {
				bool.must(qb.keyword().onField("responseSubjectId").matching(longVal).createQuery());
			}

			stringVal = (String) searchMetaData.getSqlParameters().get("fromAdmin");
			if (stringVal!=null && !stringVal.isEmpty()&& stringVal.equalsIgnoreCase("true")){
				bool.must(qb.range().onField("responseDate").ignoreFieldBridge().above("1966-01-01").createQuery()).not();
			}
			//TODO dobavi ostanalite fields
			if(bool.isEmpty()) return Collections.emptyList();
			query = bool.createQuery();


// wrap Lucene query in a javax.persistence.Query
			FullTextQuery persistenceQuery =
					fullTextEntityManager.createFullTextQuery(query, ApplicationTree.class);

			List<SortField> sortedFields = new ArrayList<SortField>();
			//TODO use the multisortmeta
			for (SortMeta currentSort:multiSortMeta
			) {

				String sortField = currentSort.getSortField();
				if (sortField!=null){
					boolean reverse = currentSort.getSortOrder() != null && currentSort.getSortOrder().ordinal()==1 ? true : false;
					SortField.Type fieldType = SortField.Type.STRING;
					sortedFields.add(new SortField(sortField,fieldType , reverse ));
				}
			}
			if (!sortedFields.isEmpty()) {
				Sort sort = new Sort( sortedFields.toArray(new SortField[sortedFields.size()]));
				persistenceQuery.setSort(sort);
			}
			persistenceQuery.setProjection(FullTextQuery.THIS,FullTextQuery.SCORE);

// execute search

			persistenceQuery.setFirstResult(first);
//			persistenceQuery.setMaxResults(pageSize);

			List resultList = persistenceQuery.getResultList();

			this.setRowCount(resultList.size());
			return resultList;
		} finally {
			JPA.getUtil().closeConnection();		}
	}
}
