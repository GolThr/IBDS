package dao.users;

import bean.User;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl {
    private DBConnection dbc = null;
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public UserDaoImpl() throws Exception {
        this.dbc = new DBConnection();
        this.conn = this.dbc.getConnection();
    }

    //注册时添加的信息
    public boolean addUser(String Email,String Company,String Address,int Tel,String Username,String Password,int Status) throws Exception {
        System.out.println("addUser:...");
        boolean flag = false;
        String sql = "INSERT INTO user_table(Email, Password, Tel, Address, Company, Username,Status) VALUES(?,?,?,?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,Email);
        this.pstmt.setString(2,Password);
        this.pstmt.setInt(3,Tel);
        this.pstmt.setString(4,Address);
        this.pstmt.setString(5,Company);
        this.pstmt.setString(6,Username);
        this.pstmt.setInt(7,Status);
        System.out.println("addUser-sql:" + sql);
        if (this.pstmt.executeUpdate() > 0) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    //忘记密码
    public boolean ForgetPwd(String email, String password) throws Exception {
        System.out.println("ForgetPassword:...");
        boolean flag = false;
        String sql = "UPDATE user_table SET Password = ? WHERE Email = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, password);
        this.pstmt.setString(2, email);
        System.out.println("ForgetPwd-sql:" + sql);
        if (this.pstmt.executeUpdate() > 0) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

//登录
    public User findUserById(String Email) throws Exception {
        System.out.println("DaoFindUserById:...");
        User user=new User();
        String sql = "SELECT * FROM user_table WHERE Email=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,Email);
        ResultSet rs = this.pstmt.executeQuery();

        if(rs.next()){
            System.out.println("123");
            user.setEmail(rs.getString("Email"));
            user.setPassword(rs.getString("Password"));
            user.setAddress(rs.getString("Address"));
            user.setAvatar(rs.getString("Avatar"));
            user.setUsername(rs.getString("username"));
            user.setCompany(rs.getString("company"));
            user.setSex(rs.getString("sex"));
            user.setTel(rs.getInt("tel"));
            user.setRoute(rs.getString("route"));
            user.setStatus(rs.getInt("status"));
        }
        this.pstmt.close();
        this.dbc.close();

        System.out.println("DaoFindUserById:OK");
        return user;
    }
}


