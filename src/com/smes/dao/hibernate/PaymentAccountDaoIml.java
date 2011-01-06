package com.smes.dao.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.smes.dao.PaymentAccountDao;
import com.smes.domain.Page;
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
	public Page<AccountTransaction> getAccountsTransactions(int customerId, 
			PageSetting pageSetting) {
		StringBuffer queryString = new StringBuffer();
		StringBuffer fromQuery = new StringBuffer ();
		queryString.append("SELECT X1.REFERENCE_ID, X1.TRANSACTION_TYPE, X1.REFERENCE_DATE, X1.REFERENCE_NUMBER,")
		.append("X1.DESCRIPTION, X1.AMOUNT, X1.AMOUNT_WITH_INTEREST, SUM(X2.AMOUNT_WITH_INTEREST) AS RUNNING_TOTAL,")
		.append("X1.COMPANY_ID, X1.CUSTOMER_ID, X1.CREATED_BY, X1.CREATED_DATE, X1.MODIFIED_BY, X1.MODIFIED_DATE ");
		
		fromQuery.append("FROM (select 	concat(REFERENCE_ID, TRANSACTION_TYPE) AS REF_ID, REFERENCE_ID, TRANSACTION_TYPE,")
				.append("REFERENCE_DATE, REFERENCE_NUMBER, DESCRIPTION, AMOUNT,")
				.append("AMOUNT + (WITH_INTEREST * AMOUNT * DAILY_INTEREST * DATEDIFf(NOW(), REFERENCE_DATE)) AS AMOUNT_WITH_INTEREST,")
				.append("COMPANY_ID, A.CUSTOMER_ID, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE ")
			.append("from PAYMENT_ACCOUNT_VIEW AS A ")
				.append("INNER JOIN (select CUSTOMER_ID, INTEREST, (((INTEREST/100) *12) / 365.25) AS DAILY_INTEREST from CUSTOMER_ACCOUNT_PREFERENCE) AS P ")
				.append("ON A.CUSTOMER_ID = P.CUSTOMER_ID ")
				.append("AND A.CUSTOMER_ID=").append(customerId).append(") AS X1 ")
			.append("INNER JOIN (select 	concat(REFERENCE_ID, TRANSACTION_TYPE) AS REF_ID, AMOUNT,")
						.append("AMOUNT + (WITH_INTEREST * AMOUNT * DAILY_INTEREST * DATEDIFf(NOW(), REFERENCE_DATE)) AS AMOUNT_WITH_INTEREST, REFERENCE_DATE ")
					.append("from PAYMENT_ACCOUNT_VIEW AS T " )
		.append("INNER JOIN (select CUSTOMER_ID, INTEREST, (((INTEREST/100) *12) / 365.25) AS DAILY_INTEREST from CUSTOMER_ACCOUNT_PREFERENCE) AS P ")
		.append("ON T.CUSTOMER_ID = P.CUSTOMER_ID ")
		.append("AND T.CUSTOMER_ID=").append(customerId).append(") AS X2 ")
			.append("on X1.REFERENCE_DATE >= X2.REFERENCE_DATE ")
		.append("WHERE X1.CUSTOMER_ID=").append(customerId).append(" ")
		.append("GROUP BY X1.REF_ID ")
		.append("ORDER BY X1.REFERENCE_DATE ");
		queryString.append(fromQuery);
		queryString.append("LIMIT ").append(pageSetting.getStartResult()).append(",").append(pageSetting.getMaxResult());
		String countSql = "SELECT count(*) as TOTAL_COUNT " + fromQuery;
		
		Session session = null;
		Collection<AccountTransaction> ats = null;
		int totalRecords = 0;
		try {
			session = getSession();
			SQLQuery query = session.createSQLQuery(queryString.toString());
			List<Object[]> queryResult = query.list();
			ats = convert(queryResult);
			query = session.createSQLQuery(countSql);
			List<?> count = query.list();
			
			if (count.size() > 0)
				totalRecords = Integer.valueOf(count.get(count.size()-1).toString());
		} finally {
			if (session != null)
				session.close();	
		}
		return new Page<AccountTransaction>(pageSetting, ats, totalRecords);
	}

	private Collection<AccountTransaction> convert (List<Object[]> queryResult) {
		Collection<AccountTransaction> ret = new ArrayList<AccountTransaction>();
		for (Object[] row : queryResult){
			AccountTransaction at = new AccountTransaction();
			at.setReferenceId((Integer)row[0]);
			at.setTransactionType(row[1].toString());
			at.setReferenceDate((Date) row[2]);
			at.setRefereneNumber(row[3].toString());
			at.setDescription(row[4].toString());
			at.setAmount((Double)row[5]);
			at.setAmountWithInterest((Double)row[6]);
			at.setRunningTotal((Double) row[7]);
			at.setCompanyId((Integer)row[8]);
			at.setCustomerId((Integer)row[9]);
			at.setCreatedBy((Integer) row[10]);
			at.setCreatedDate((Date) row[11]);
			at.setModifiedBy((Integer) row[12]);
			at.setModifiedDate((Date) row[13]);
			ret.add(at);
		}
		return ret;
	}

	@Override
	public double getStartingTotal(int pageNumber) {
		return 0;
	}

}
