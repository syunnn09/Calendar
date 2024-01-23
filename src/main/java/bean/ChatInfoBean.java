package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatInfoBean implements Serializable {
	private ArrayList<ChatBean> chatArray = new ArrayList<>();

	public ChatInfoBean() { }

	public void setChatBean(ArrayList<ChatBean> chatArray) {
		this.chatArray = chatArray;
	}

	public void add(ChatBean chat) {
		this.chatArray.add(chat);
	}

	public void prepend(ChatBean chat) {
		this.chatArray.add(0, chat);
	}

	public int getSize() {
		return chatArray.size();
	}

	public ArrayList<ChatBean> getChatArray() {
		return this.chatArray;
	}

}
