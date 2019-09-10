package dao.users;

import bean.Depature;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl {
    private DBConnection dbc = null;
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private PreparedStatement pstmt1 = null;

    public CompanyDaoImpl() throws Exception {
        this.dbc = new DBConnection();
        this.conn = this.dbc.getConnection();
    }
    //添加公交车运行数据
    public boolean Add_log_bus_inf(int line_number,int year,int month,int day,
                                   int hour,int minute,int all_people,double avg_people,
                                   double max_people,double max2_people,double min_people,
                                   double min2_people) throws Exception {
        System.out.println("Add_log_bus_inf Status:...");
        String sql = "INSERT INTO company_data_table(line_number,year,month,day,hour, minute, all_people, avg_people, max_people, max2_people, min_people,  min2_people) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, line_number);
        this.pstmt.setInt(2, year);
        this.pstmt.setInt(3, month);
        this.pstmt.setInt(4, day);
        this.pstmt.setInt(5, hour);
        this.pstmt.setInt(6, minute);
        this.pstmt.setInt(7, all_people);
        this.pstmt.setDouble(8,avg_people);
        this.pstmt.setDouble(9,max_people);
        this.pstmt.setDouble(10,max2_people);
        this.pstmt.setDouble(11,min_people);
        this.pstmt.setDouble(12,min2_people);

        if (this.pstmt.executeUpdate() > 0) {
            this.pstmt.close();
            return true;
        }
        return false;
    }
    //添加公交司机信息表格
    //添加公交车运行数据

/*
    private String email;
    private String tel;
    private String address;
    private String company;
    private String sex;
    private String worknumber;
    private String username;
    private String route;*/
    public boolean Add_bus_driver_inf(String email, String tel, String address, String company, String sex, String worknumber, String username, String route) throws Exception {
        System.out.println("Add_bus_driver_inf Status:...");
        String sql = "INSERT INTO user_table(email,tel,  address, company, sex, worknumber, username,route,password,status) VALUES(?,?,?,?,?,?,?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, email);
        this.pstmt.setString(2, tel);
        this.pstmt.setString(3, address);
        this.pstmt.setString(4, company);
        this.pstmt.setString(5, sex);
        this.pstmt.setString(6, worknumber);
        this.pstmt.setString(7, username);
        this.pstmt.setString(8, route);
        this.pstmt.setString(9,"123456");
        this.pstmt.setInt(10,1);
        if (this.pstmt.executeUpdate() > 0) {
            this.pstmt.close();
            return true;
        }
        return false;
    }

}
