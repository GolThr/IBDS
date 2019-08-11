package dao.users;

import bean.Message;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//涉及到的表格有message_table和user_table
public class MessageDaoImpl {
    private DBConnection dbc = null;
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public MessageDaoImpl() throws Exception {
        this.dbc = new DBConnection();
        this.conn = this.dbc.getConnection();
    }

    //司机显示查询留言信息    对应的是MessageServlet
    public List<Message> findSelfMessage(String Email ) throws Exception {
        System.out.println("DaoFindUserByStatus:...");
        String sql = "SELECT * FROM user_table,message_table WHERE user_table.Email=message_table.email and message_table.Email=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, Email);
        ResultSet rs = this.pstmt.executeQuery();
        System.out.println("--findSelfMessage:executeOK");
        List<Message> MessageList = new ArrayList<>();
        while (rs.next()){
            System.out.println("123");
            Message message=new Message();
            message.setDate(rs.getDate("Date"));
            message.setMessage(rs.getString("Message"));
            message.setAvater(rs.getString("Avatar"));
            message.setName(rs.getString("Username"));
           MessageList.add(message);
           System.out.println("--ResearchAlluser:whileEnd_OK");
            System.out.println("message"+message.getMessage());
            System.out.println("name"+message.getName());
        }
        this.pstmt.close();
        System.out.println("FindALLuser:OK");
        return MessageList;
    }

    //管理员显示查询留言信息    对应的是MessageServlet
    public List<Message> findAllUsers( ) throws Exception {
        System.out.println("DaoFindUserByStatus:...");
        String sql = "SELECT * FROM user_table,message_table WHERE user_table.Email=message_table.email";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        System.out.println("--findAllUsers:executeOK");
        List<Message> MessageList = new ArrayList<>();
        while (rs.next()){
            System.out.println("123");
            Message message=new Message();
            message.setDate(rs.getDate("Date"));
            message.setMessage(rs.getString("Message"));
            message.setAvater(rs.getString("Avatar"));
            message.setName(rs.getString("Username"));
            MessageList.add(message);
            System.out.println("--ResearchAlluser:whileEnd_OK");
            System.out.println("message"+message.getMessage());
            System.out.println("name"+message.getName());
        }
        this.pstmt.close();
        System.out.println("FindALLuser:OK");
        return MessageList;
    }

    //添加留言信息   将Message填入到对应的Email记录行中，并插入日期
    public boolean addMessage(String Email,String Message) throws Exception{
        System.out.println("AddMessage:....");
        String sql="INSERT INTO message_table (Email,Message,Date) values(?,?,CURRENT_DATE())";
        this.pstmt=this.conn.prepareStatement(sql);
        this.pstmt.setString(1, Email);
        this.pstmt.setString(2, Message);
        System.out.println("--AddMessage:executeOK");
        boolean flag=false;
        System.out.println("ForgetPwd-sql:" + sql);
        if (this.pstmt.executeUpdate() > 0) {
            flag = true;
        }
        this.pstmt.close();
        return  flag;
    }











}
