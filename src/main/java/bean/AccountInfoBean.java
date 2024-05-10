package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountInfoBean implements Serializable {
	private ArrayList<AccountBean> accountRecordArray;
	
	/**
	 * AccountInfoBeanのコンストラクタです。
	 * アカウントレコードのリストを初期化します。
	 *
	 * @method AccountInfoBean
	 * @author rerere
	 * @version 1.0.0
	 */
	public AccountInfoBean() {
		accountRecordArray = new ArrayList<AccountBean>();
	}

	public void addUserRecord(AccountBean bean) {
		this.accountRecordArray.add(bean);
	}

	/**
	 * アカウントレコードを追加します。
	 *
	 * @method addUserRecord
	 * @param bean 追加するアカウントレコード
	 * @author rerere
	 * @version 1.0.0
	 */
	public int getArraySize() {
		return accountRecordArray.size();
	}

	/**
	 * アカウントレコードのリストのサイズを取得します。
	 *
	 * @method getArraySize
	 * @return アカウントレコードのリストのサイズ
	 * @author rerere
	 * @version 1.0.0
	 */
	public ArrayList<AccountBean> getAccountRecordArray() {
		return this.accountRecordArray;
	}

	/**
	 * アカウントレコードのリストを設定します。
	 *
	 * @method setUserRecordArray
	 * @param array アカウントレコードのリスト
	 * @author rerere
	 * @version 1.0.0
	 */
	public void setUserRecordArray(ArrayList<AccountBean> array) {
		this.accountRecordArray = array;
	}
}
