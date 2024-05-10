package bean;

import java.util.ArrayList;

/**
 * GroupInfoBeanクラス
 * GroupBeanを配列にしたもの
 * 
 * @author ootubo,fukumori
 * @version 1.0.0
 */
public class GroupInfoBean {
	private ArrayList<GroupBean> groupArray = new ArrayList<>();

	/**
	 * @author ootubo,fukumori
     * 新しいグループ情報のインスタンスを生成します。
     * @version 1.0.0
     */
	public GroupInfoBean() {

	}
	
	/**
     * グループを追加します。
     *
     * @author ootubo,fukumori
     * @param obj 追加するグループのGroupBeanオブジェクト
     * @version 1.0.0
     */
	public void addGroup(GroupBean obj) {
		groupArray.add(obj);
	}
	
	 /**
     * グループのリストのサイズを取得します。
     *
     * @author ootubo,fukumori
     * @return グループのリストのサイズ
     * @version 1.0.0
     */
	public int getArraySize() {
		return groupArray.size();

	}

	/**
     * グループのリストを取得します。
     *
     * @author ootubo,fukumori
     * @return グループのリスト
     * @version 1.0.0
     */
	public ArrayList<GroupBean> getGroupArray() {
		return groupArray;
	}

	/**
     * グループのリストを設定します。
     *
     * @author ootubo,fukumori
     * @param groupArray 設定するグループのリスト
     * @version 1.0.0
     */
	public void setGroupArray(ArrayList<GroupBean> groupArray) {
		this.groupArray = groupArray;
	}

	 /**
     * 指定されたルームIDに対応するグループを取得します。
     *
     * @author ootubo,fukumori
     * @param roomId 取得するグループのルームID
     * @return 指定されたルームIDに対応するグループのGroupBeanオブジェクト。見つからない場合はnull。
     * @version 1.0.0
     */
	public GroupBean get(int roomId) {
		for (GroupBean bean: this.groupArray) {
			if (bean.getRoomId() == roomId) {
				return bean;
			}
		}
		return null;
	}
}
