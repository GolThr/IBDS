package dao.users;

import bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UseDaoImpl implements UserDao {
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public UseDaoImpl(Connection conn){
        this.conn = conn;
    }

    @Override
    public boolean addUser(User user) throws Exception {
        System.out.println("addUser:...");
        boolean flag = false;
        String sql = "INSERT INTO user_info(stu_id, password, gender, theme, username, ischecked) VALUES(?,?,?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, user.getStu_id());
        this.pstmt.setString(2, user.getPassword());
        this.pstmt.setString(3, user.getGender());
        this.pstmt.setString(4, user.getTheme());
        this.pstmt.setString(5, user.getUsername());
        this.pstmt.setInt(6, 0);
        System.out.println("addUser-sql:"+sql);

        if(this.pstmt.executeUpdate() > 0){
            flag = true;
        }
        this.pstmt.close();

        return flag;
    }

    @Override
    public boolean changePassword(String stu_id, String password) throws Exception {
        System.out.println("changePassword:...");
        boolean flag = false;
        String sql = "UPDATE user_info SET password = ? WHERE stu_id = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, password);
        this.pstmt.setString(2, stu_id);
        System.out.println("ModifyStuInfo-sql:"+sql);

        if(this.pstmt.executeUpdate() > 0){
            flag = true;
        }
        this.pstmt.close();

        return flag;
    }

    @Override
    public User findUserById(String stu_id) throws Exception {
        System.out.println("DaoFindUserById:...");
        User user = new User();
        String sql = "SELECT * FROM user_info WHERE stu_id=?";

        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, stu_id);

        ResultSet rs = this.pstmt.executeQuery();
        if(rs.next()){
            user.setStu_id(rs.getString("stu_id"));
            user.setPassword(rs.getString("password"));
            user.setGender(rs.getString("gender"));
            user.setTheme(rs.getString("theme"));
            user.setUsername(rs.getString("username"));
            user.setIschecked(rs.getInt("ischecked"));
        }
        this.pstmt.close();

        System.out.println("DaoFindUserById:OK");
        return user;
    }
}
