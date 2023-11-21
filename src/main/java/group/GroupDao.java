package group;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GroupDao extends DaoBase {
	private Connection conn;
	private PreparedStatement pStmt;
	
	public void insert(int userId,int roomId) {
		try {
			open();
			String sql="Insert Into Group(userId,roomId)values(?,?)";
			pStmt=conn.prepareStatement(sql);
			pStmt.setInt(1, userId);
			pStmt.setInt(2, roomId);
			pStmt.executeUpdate();
			close();
		
		}catch(SQLException e) {
			
		}
		
	}
	
	public void grant(int roomId,int userId) {
		try {			
			open();
			String sql="UPDATE group SET isAdmin = 1 WHERE userId = ? AND roomId = ? ;";
			pStmt=conn.prepareStatement(sql);
			pStmt.setInt(1, userId);
			pStmt.setInt(2, roomId);
			pStmt.executeUpdate();
			close();
			
		}catch(SQLException e) {
			
		}
	}
}
