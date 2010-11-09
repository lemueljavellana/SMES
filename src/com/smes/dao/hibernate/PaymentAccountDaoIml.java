package com.smes.dao.hibernate;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.smes.dao.PaymentAccountDao;
import com.smes.domain.PageSetting;
import com.smes.domain.hibernate.AccountTransaction;

public class PaymentAccountDaoIml extends BaseDao<AccountTransaction> 
	implements PaymentAccountDao {

	@Override
	protected Class<AccountTransaction> getDomainClass() {
		return AccountTransaction.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<AccountTransaction> getAccountsTransactions(int companyId,
			PageSetting pageSetting) {
		StringBuffer queryString = new StringBuffer();
		queryString.append("SELECT X1.REFERENCE_ID, X1.TRANSACTION_TYPE, X1.REFERENCE_DATE,")
		.append("X1.REFERENCE_NUMBER, X1.DESCRIPTION, X1.AMOUNT, SUM(X2.AMOUNT) AS RUNNING_TOTAL,") 
		.append("X1.COMPANY_ID, X1.CREATED_BY, X1.CREATED_DATE, X1.MODIFIED_BY, X1.MODIFIED_DATE FROM") 
		.append("(select concat(REFERENCE_ID, TRANSACTION_TYPE) AS REF_ID, REFERENCE_ID,")
		.append("TRANSACTION_TYPE, REFERENCE_DATE, REFERENCE_NUMBER,")
			.append("DESCRIPTION, AMOUNT, COMPANY_ID, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE ")
			.append("from payment_account_view) AS X1 ") 
		.append("INNER JOIN (select concat(REFERENCE_ID, TRANSACTION_TYPE) AS REF_ID,")
				.append("REFERENCE_ID, TRANSACTION_TYPE, REFERENCE_DATE,")
			.append("REFERENCE_NUMBER, DESCRIPTION, AMOUNT, COMPANY_ID, CREATED_BY, CREATED_DATE, MODIFIED_BY,MODIFIED_DATE ")
			.append("from payment_account_view) AS X2 ")
		.append("on X1.REF_ID >= X2.REF_ID ")
		.append("WHERE X1.COMPANY_ID=").append(companyId).append(" ")
		.append("GROUP BY X1.REF_ID ")
		.append("ORDER BY X1.REFERENCE_DATE ")
		.append("LIMIT ").append(pageSetting.getStartResult()).append(",").append(pageSetting.getMaxResult());
		Session session = null;
		try {
			session = getSession();
			SQLQuery query = session.createSQLQuery(queryString.toString());
			//query.addEntity(AccountTransaction.class);
			List<Object[]> queryResult = query.list();
			return convert(queryResult);
		} finally {
			if (session != null)
				session.close();	
		}
	}
	
	private Collection<AccountTransaction> convert (List<Object[]> queryResult) {
		Collection<AccountTransaction> ret = new HashSet<AccountTransaction>();
		for (Object[] row : queryResult){
			AccountTransaction at = new AccountTransaction();
			at.setReferenceId((Integer)row[0]);
			at.setTransactionType(row[1].toString());
			at.setReferenceDate((Date) row[2]);
			at.setRefereneNumber(row[3].toString());
			at.setDescription(row[4].toString());
			at.setAmount((Double)row[5]);
			at.setRunningTotal((Double) row[6]);
			at.setCompanyId((Integer)row[7]);
			at.setCreatedBy((Integer) row[8]);
			at.setCreatedDate((Date) row[9]);
			at.setModifiedBy((Integer) row[10]);
			at.setModifiedDate((Date) row[11]);
			ret.add(at);
		}
		return ret;
	}

	@Override
	public double getStartingTotal(int pageNumber) {
		String hsq = "select sum(amount) from PaymentAccountView";
		// TODO Auto-generated method stub
		return 0;
	}

}
