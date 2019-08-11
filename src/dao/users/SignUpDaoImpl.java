package dao.users;

import bean.Message;
import bean.SignUp;
import bean.User;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


//这里边涉及的表格为signup_table
public class SignUpDaoImpl {
    private DBConnection dbc = null;
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private PreparedStatement pstmt1 = null;

    public SignUpDaoImpl() throws Exception {
        this.dbc = new DBConnection();
        this.conn = this.dbc.getConnection();
    }

    //司机签到     根据传来的邮箱对Times和Date进行更新操作，对应的是SignUpServlet
    public SignUp Signup(String Email) throws Exception {
        System.out.println("ForgetPassword:...");
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

    //显示信息（司机页面的小图标的初始化）对应的是DriversServlet
    public SignUp findsignbyemail(String Email, String Company) throws Exception{
        System.out.println("findsignbyemail:....");
        String sql1="SELECT * from signup_table,user_table where user_table.Email=? and user_table.Email=signup_table.Email";
        this.pstmt=this.conn.prepareStatement(sql1);
        this.pstmt.setString(1, Email);
        SignUp signUp=new SignUp();
        ResultSet rs = this.pstmt.executeQuery();
        if(rs.next()){
            System.out.println("123");
            signUp.setDate(rs.getDate("Date"));
            signUp.setRoute(rs.getString("route"));
            signUp.setTimes(rs.getString("times"));
        }
        this.pstmt.close();
        System.out.println("route是"+signUp.getRoute());
        System.out.println("--SelectSignup:executeOK");
        String sql2="SELECT * from user_table where status=0 and company=?";
        this.pstmt=this.conn.prepareStatement(sql2);
        this.pstmt.setString(1,Company);
        System.out.println("--Selectname:executeOK");
        ResultSet rs1 = this.pstmt.executeQuery();
        if(rs1.next()){
            System.out.println("123");
            signUp.setName(rs1.getString("username"));
            signUp.setTel(rs1.getString("tel"));
        }
        this.pstmt.close();
        System.out.println("Findsignbyemail:OK");
        return signUp;
    }

}


