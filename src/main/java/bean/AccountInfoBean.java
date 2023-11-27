package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountInfoBean implements Serializable {
	private ArrayList<AccountBean> accountRecordArray;

	public AccountInfoBean() {
		accountRecordArray = new ArrayList<AccountBean>();
	}

	public void addUserRecord(AccountBean bean) {
		this.accountRecordArray.add(bean);
	}

	public int getArraySize() {
		return accountRecordArray.size();
	}

	public ArrayList<AccountBean> getAccountRecordArray() {
		return this.accountRecordArray;
	}

	public void setUserRecordArray(ArrayList<AccountBean> array) {
		this.accountRecordArray = array;
	}
}
