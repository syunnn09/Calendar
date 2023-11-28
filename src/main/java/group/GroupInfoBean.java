package group;
import java.util.ArrayList;

public class GroupInfoBean {
	private ArrayList<GroupBean> groupArray=new ArrayList<>();
	
	public GroupInfoBean() {
		
	}
	
	public void addGroup(GroupBean obj){
		groupArray.add(obj);
	}
	
	public int getArraySize() {
		return groupArray.size();
		
	}

	public ArrayList<GroupBean> getGroupArray() {
		return groupArray;
	}

	public void setGroupArray(ArrayList<GroupBean> groupArray) {
		this.groupArray = groupArray;
	}
	
	
}
