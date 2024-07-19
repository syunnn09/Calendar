package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.AccountBean;
import bean.AccountInfoBean;
import model.UserModel;

public class AccountDao extends DaoBase {
	/**
	 * サンプルメソッドです。
	 * データベースからサンプルテーブルのデータを取得し、表示します。
	 *
	 * @method sample
	 * @author rerere
	 * @version 1.0.0
	 */
	public void sample() {
		this.open();

		String sql = "SELECT * FROM sample;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.print(rs.getInt("id"));
				System.out.print(rs.getString("userName"));
				System.out.println(rs.getInt("age"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.close();
	}

	/**
	 * ユーザーのログインを試みます。
	 * 指定されたメールアドレスとパスワードを使用してデータベースからユーザーを検索し、ログイン状態を返します。
	 *
	 * @method login
	 * @param email ログインするユーザーのメールアドレス
	 * @param password ログインするユーザーのパスワード
	 * @return ログインしたユーザーのユーザーモデル。ログインに失敗した場合はnullを返します。
	 * @throws SQLException データベースに関するエラーが発生した場合
	 * @throws NullPointerException emailまたはpasswordがnullの場合
	 * @author rerere
	 * @version 1.0.0
	 */
	public UserModel login(String email, String password) {
		open();
		try {
			String sql = "SELECT userId, isLogined FROM users WHERE email = ? AND password = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, email);
			pStmt.setString(2, password);
			
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
				int userId = rs.getInt(1);
				boolean isLogined = rs.getInt(2) != 0;
				UserModel user = new UserModel(userId, isLogined);
				return user;
			} else { 
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * アカウント情報をデータベースに追加します。
	 *
	 * @method addUser
	 * @param beans 追加するアカウント情報を持つAccountInfoBeanオブジェクト
	 * @throws SQLException データベースに関するエラーが発生した場合
	 * @throws NullPointerException beansがnullの場合
	 * @author rerere
	 * @version 1.0.0
	 */
	public void addUser(AccountInfoBean beans) {
		this.open();

		try {
			for (AccountBean bean: beans.getAccountRecordArray()) {
				String sql = "INSERT INTO users(name, email, password, birthday) VALUES(?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, bean.getUserName());
				ps.setString(2, bean.getEmail());
				ps.setString(3, bean.getPassword());
				ps.setString(4, bean.getBirthday());
				ps.execute();
			}

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ユーザーのパスワードを変更します。
	 *
	 * @method changePassword
	 * @param userId パスワードを変更するユーザーのID
	 * @param password 新しいパスワード
	 * @return パスワードの変更が成功した場合はtrue、失敗した場合はfalseを返します。
	 * @throws SQLException データベースに関するエラーが発生した場合
	 * @throws NullPointerException passwordがnullの場合
	 * @throws IllegalArgumentException userIdが負の値の場合
	 * @author rerere
	 * @version 1.0.0
	 */
	public boolean changePassword(int userId, String password) {
		this.open();
		try {
			String sql = "UPDATE users SET password = ?, isLogined = 1 WHERE userId = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, password);
			pStmt.setInt(2, userId);
			int result = pStmt.executeUpdate();
			return result > 0;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * アカウント情報を更新します。
	 *
	 * @method update
	 * @param account 更新するアカウント情報を持つAccountBeanオブジェクト
	 * @throws SQLException データベースに関するエラーが発生した場合
	 * @throws NullPointerException accountがnullの場合
	 * @throws IllegalArgumentException userIdが負の値の場合
	 * @version 1.0.0
	 * @author rerere
	 */
	public void update(AccountBean account) {
		this.open();
		String sql = "UPDATE users SET name = ?, password = ? WHERE userId = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, account.getUserName());
			ps.setString(2, account.getPassword());
			ps.setInt(3, account.getUserId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 指定されたユーザーIDに関連付けられたユーザー名を取得します。
	 *
	 * @method getName
	 * @param userId 取得するユーザー名のユーザーID
	 * @return 指定されたユーザーIDに関連付けられたユーザー名。見つからない場合は空の文字列を返します。
	 * @throws SQLException データベースに関するエラーが発生した場合
	 * @throws IllegalArgumentException userIdが負の値の場合
	 * @version 1.0.0
	 * @author rerere
	 */
	public String getName(int userId) {
		this.open();
		String sql = "SELECT name FROM users WHERE userId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
			return "";
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 指定されたメールアドレスに関連付けられたログイン状態を取得します。
	 *
	 * @method getIsLogined
	 * @param email ログイン状態を取得するユーザーのメールアドレス
	 * @return 指定されたメールアドレスに関連付けられたログイン状態。ログインしている場合は1、ログインしていない場合は0を返します。見つからない場合は-1を返します。
	 * @throws SQLException データベースに関するエラーが発生した場合
	 * @throws NullPointerException emailがnullの場合
	 * @version 1.0.0
	 * @author rerere
	 */
	public int getIsLogined(String email) {
		this.open();

		try {
			String sql = "SELECT isLogined FROM users WHERE email=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 指定されたユーザーIDとルームIDの組み合わせが存在するかどうかを確認します。
	 *
	 * @method checkJoin
	 * @param userId チェックするユーザーのID
	 * @param roomId チェックするルームのID
	 * @return 指定されたユーザーIDとルームIDの組み合わせが存在する場合はtrue、それ以外の場合はfalseを返します。
	 * @throws SQLException データベースに関するエラーが発生した場合
	 * @throws IllegalArgumentException userIdまたはroomIdが負の値の場合
	 * @version 1.0.0
	 * @author rerere
	 */
	public boolean checkJoin(int userId, int roomId) {
		try {
			this.open();
			String sql = "SELECT userId FROM joins WHERE userId = ? AND roomId = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, roomId);
			ResultSet rs = ps.executeQuery();
			this.close();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
