package dao.users;

import bean.Depature;
import bean.Message;
import bean.User;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DepatureDaoImpl {
    private DBConnection dbc = null;
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private PreparedStatement pstmt1 = null;

    public DepatureDaoImpl() throws Exception {
        this.dbc = new DBConnection();
        this.conn = this.dbc.getConnection();
    }
    //初始化司机查询线路页面
    public List<Depature> FilterTimeAll(String Company, String Route) throws Exception {
        System.out.println("DaoAll:...");
        String sql = "SELECT * FROM user_table,departure_table WHERE Route =? and Company=? and user_table.email=departure_table.email";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,Route);
        this.pstmt.setString(2,Company);
        ResultSet rs = this.pstmt.executeQuery();
        System.out.println("--findAll:executeOK");

        List<Depature> depatureList = new ArrayList<>();
        while (rs.next()){
            System.out.println("123");
            Depature depature=new Depature();
            depature.setUesrname(rs.getString("Username"));
            depature.setTime(rs.getString("Time"));
            depatureList.add(depature);
            System.out.println("--FilterTimeAll:whileEnd_OK");
            System.out.println("route"+depature.getUesrname());
        }
        this.pstmt.close();
        System.out.println("FindUserByStatus:OK");
        return depatureList;
    }


    public List<Depature> FilterTimeSelf(String Company,String Email,String Route) throws Exception {
        System.out.println("DaoSelf:...");
        String sql = "SELECT * FROM user_table,departure_table  WHERE departure_table.Email=? and user_table.email=departure_table.email";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,Email);
        ResultSet rs = this.pstmt.executeQuery();
        System.out.println("--FilterTimeSelf:executeOK");

        List<Depature> depatureList = new ArrayList<>();
        while (rs.next()){
            System.out.println("123");
            Depature depature=new Depature();
            depature.setUesrname(rs.getString("user_table.Username"));
            depature.setTime(rs.getString("time"));
            depatureList.add(depature);
            System.out.println("--FilterTimeAll:whileEnd_OK");
            System.out.println("route"+depature.getUesrname());

        }
        this.pstmt.close();
        System.out.println("FindUserByStatus:OK");
        return depatureList;
    }

    //司机查看行车日志
    public List<Depature> ResearchLog(String Email,String Date ) throws Exception {
        System.out.println("DaoFindUserByStatus:...");
        String sql = "SELECT * FROM user_table,departure_table WHERE user_table.Email=departure_table.email and departure_table.Email=? and Date=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, Email);
        this.pstmt.setString(2, Date);
        ResultSet rs = this.pstmt.executeQuery();
        System.out.println("--ResearchLog:executeOK");
        List<Depature> MessageList = new ArrayList<>();
        while (rs.next()){
            System.out.println("123");
            Depature message=new Depature();
            message.setTime(rs.getString("Time"));
            message.setUesrname(rs.getString("Username"));
            MessageList.add(message);
            System.out.println("--ResearchLog:whileEnd_OK");
            System.out.println("time"+message.getTime());
            System.out.println("name"+message.getUesrname());

        }
        this.pstmt.close();
        System.out.println("Research:OK");
        return MessageList;
    }
}
