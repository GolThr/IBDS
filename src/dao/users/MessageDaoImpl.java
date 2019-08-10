package dao.users;

import bean.Message;
import bean.User;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MessageDaoImpl {
    private DBConnection dbc = null;
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public MessageDaoImpl() throws Exception {
        this.dbc = new DBConnection();
        this.conn = this.dbc.getConnection();
    }


    //显示查询留言信息
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











}
