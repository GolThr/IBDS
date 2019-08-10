package dao.users;

import bean.SignUp;
import bean.User;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SignUpDaoImpl {
    private DBConnection dbc = null;
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private PreparedStatement pstmt1 = null;

    public SignUpDaoImpl() throws Exception {
        this.dbc = new DBConnection();
        this.conn = this.dbc.getConnection();
    }

    //司机签到
    public SignUp SignUp(String Email) throws Exception {
        System.out.println("ForgetPassword:...");
        boolean flag = false;
        SignUp signup=new SignUp();
        String sql = "UPDATE signup_table SET Times=Times+1 ,Date=CURRENT_DATE()WHERE Email = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, Email);
        System.out.println("SignUp-sql:" + sql);
        this.pstmt.executeUpdate();


        String sql2 = "SELECT * FROM signup_table WHERE Email=?";
        this.pstmt = this.conn.prepareStatement(sql2);
        this.pstmt.setString(1,Email);
        ResultSet rs = this.pstmt.executeQuery();

        if(rs.next()){
            System.out.println("123");
            signup.setTimes(rs.getString("Times"));
            signup.setDate(rs.getDate("Date"));
        }

        this.pstmt.close();
        this.dbc.close();

        System.out.println("DaoFindUserById:OK");
        return signup;
    }
}


