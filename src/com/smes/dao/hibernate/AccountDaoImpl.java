package com.smes.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.SQLQuery;

import com.smes.dao.AccountDao;
import com.smes.domain.Page;
import com.smes.domain.PageSetting;
import com.smes.domain.hibernate.Account;
import com.smes.domain.hibernate.AccountTransaction;
import com.smes.util.PropertyFileConstant;

public class AccountDaoImpl extends BaseDao<Account>
	implements AccountDao{

	@Override
	protected Class<Account> getDomainClass() {
		return Account.class;
	}
	
	public Page<AccountTransaction> getUnpaidTransactions (int customerId,
			PageSetting pageSetting) {
		return getUnpaidTransactions(customerId, null, pageSetting);
	}

	@Override
	public Page<AccountTransaction> getUnpaidTransactions(int customerId,
			List<Integer> exclude, PageSetting pageSetting) {
		ResourceBundle rb =
			ResourceBundle.getBundle(PropertyFileConstant.ACCOUNT_PROP);
		String selectSQL = rb.getString("unpaid.account.sql.select");
		String fromSQL = rb.getString("unpaid.account.sql.from");
		String whereSQL = rb.getString("unpaid.account.sql.where");
		String groupSQL = rb.getString("unpaid.account.sql.group");
		String orderSQL = rb.getString("unpaid.account.sql.order");
		
		if (exclude != null)
			for (Integer id : exclude) 
				whereSQL += " AND T1.ACCOUNT_ID !=" + id;

		return getUnpaidTransaction(customerId, selectSQL, fromSQL,
				whereSQL, groupSQL, orderSQL, pageSetting);
	}

	private Page<AccountTransaction> getUnpaidTransaction (int customerId, String selectSQL,
			String fromSQL, String whereSQL, String groupSQL, String orderSQL, PageSetting pageSetting) {
		String sql = selectSQL+ " " + fromSQL + " " +whereSQL + " " + groupSQL + " " + orderSQL;
		QRHandler handler = new QRHandler(customerId);
		return getAllAsPage(sql, pageSetting, handler);
	}
	
	@Override
	public Page<AccountTransaction> getUnpaidAccounts(int customerId,
			List<Integer> ids, PageSetting pageSetting) {
		ResourceBundle rb =
			ResourceBundle.getBundle(PropertyFileConstant.ACCOUNT_PROP);
		String selectSQL = rb.getString("unpaid.account.sql.select");
		String fromSQL = rb.getString("unpaid.account.sql.from");
		String whereSQL = rb.getString("unpaid.account.sql.where");
		String groupSQL = rb.getString("unpaid.account.sql.group");
		String orderSQL = rb.getString("unpaid.account.sql.order");
		boolean createAnd = true;
		if (ids != null) {
			for (int id : ids) {
				if (createAnd) {
					whereSQL += "AND (T1.ACCOUNT_ID =" + id;
					createAnd = false;
				} else {
					whereSQL += " OR T1.ACCOUNT_ID =" + id;
				}
			}
			whereSQL += ")";
		}
			
		return getUnpaidTransaction(customerId, selectSQL, fromSQL, whereSQL,
				groupSQL, orderSQL, pageSetting);
	}
	
	private static class QRHandler implements QueryResultHandler<AccountTransaction> {
		private final int customerId;
		
		private QRHandler (int customerId){
			this.customerId = customerId;
		}

		@Override
		public List<AccountTransaction> convert(List<Object[]> queryResult) {
			List<AccountTransaction> ret = new ArrayList<AccountTransaction>();
			for (Object[] row : queryResult){
				AccountTransaction at = new AccountTransaction();
				at.setAccountId((Integer) row[0]);
				at.setCustomerId((Integer) row[1]);
				at.setAccountDate((Date) row[2]);
				at.setDueDate((Date) row[3]);
				at.setReferenceNumber(row[4].toString());
				at.setDescription(row[5].toString());
				at.setAmount((Double) row[6]);
				at.setEarnedInterest((Double) row[7]);
				at.setTotalAmount((Double) row[8]);
				at.setRunningTotal((Double) row[9]);
				ret.add(at);
			}
			return ret;
		}
		
		@Override
		public int setParamater(SQLQuery query) {
			int index = 0;
			query.setParameter(index++, customerId);
			query.setParameter(index++, customerId);
			query.setParameter(index++, customerId);
			query.setParameter(index, customerId);
			return index;
		}
	}
}
