package dao.users;

import bean.User;
import bean.UserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StuInfoDaoImpl implements StuInfoDao {
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public StuInfoDaoImpl(Connection conn){
        this.conn = conn;
    }


    @Override
    public boolean ModifyStuInfo(String stu_id, String subSql) throws Exception {
        System.out.println("ModifyStuInfo:...");
        boolean flag = false;
        String sql = "UPDATE student_information SET "+subSql+" WHERE stu_id = "+"'"+stu_id+"'";
        this.pstmt = this.conn.prepareStatement(sql);
//        this.pstmt.setString(1, stu_id);
        System.out.println("ModifyStuInfo-sql:"+sql);

        if(this.pstmt.executeUpdate() > 0){
            flag = true;
        }
        this.pstmt.close();

        return flag;
    }

    @Override
    public boolean hasChecked(String stu_id) throws Exception{
        System.out.println("hasChecked:...");
        boolean flag = false;
        String sql = "UPDATE user_info SET ischecked = '1' WHERE stu_id = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, stu_id);
        System.out.println("ModifyStuInfo-sql:"+sql);

        if(this.pstmt.executeUpdate() > 0){
            flag = true;
        }
        this.pstmt.close();

        return flag;
    }

    @Override
    public UserInfo findInfoById(String stu_id) throws Exception {
        System.out.println("findInfoById:...");
        UserInfo userInfo = new UserInfo();
        String sql = "SELECT * FROM student_information WHERE stu_id=?";

        System.out.println("--findInfoById:SQLOK");
        this.pstmt = this.conn.prepareStatement(sql);
        System.out.println("--findInfoById:StatementOK");
        this.pstmt.setString(1, stu_id);
        System.out.println("--findInfoById:SetStringOK");
        System.out.println("findInfoById-sql:"+sql);

        ResultSet rs = this.pstmt.executeQuery();
        System.out.println("--findInfoById:executeOK");
        if(rs.next()){
            System.out.println("--findInfoById:If_OK");
            userInfo.setStu_id(rs.getString("stu_id"));
            System.out.println("--findInfoById:stu_id-OK");
            userInfo.setUsername(rs.getString("username"));
            System.out.println("--findInfoById:username-OK");
            userInfo.setAccount_type(rs.getString("account_type"));
            System.out.println("--findInfoById:account_type-OK");
            userInfo.setGender(rs.getString("gender"));
            System.out.println("--findInfoById:gender-OK");
            userInfo.setClass_duty(rs.getString("class_duty"));
            System.out.println("--findInfoById:class_duty-OK");
            userInfo.setStu_union_duty(rs.getString("stu_union_duty"));
            System.out.println("--findInfoById:stu_union_duty-OK");
            userInfo.setPhone_number(rs.getString("phone_number"));
            System.out.println("--findInfoById:phone_number-OK");
            userInfo.setPhone_num_type(rs.getString("phone_num_type"));
            System.out.println("--findInfoById:phone_num_type-OK");
            userInfo.setQq(rs.getString("qq"));
            System.out.println("--findInfoById:qq-OK");
            userInfo.setBirthplace(rs.getString("birthplace"));
            System.out.println("--findInfoById:birthplace-OK");
            userInfo.setId_card(rs.getString("id_card"));
            System.out.println("--findInfoById:id_card-OK");
            userInfo.setBirth_date(rs.getString("birth_date"));
            System.out.println("--findInfoById:birth_date-OK");
            userInfo.setEnglish_grade(rs.getString("english_grade"));
            System.out.println("--findInfoById:english_grade-OK");
            userInfo.setFamily_contact(rs.getString("family_contact"));
            System.out.println("--findInfoById:family_contact-OK");
            userInfo.setAddress_on_id(rs.getString("address_on_id"));
            System.out.println("--findInfoById:address_on_id-OK");
            userInfo.setAddress_now(rs.getString("address_now"));
            System.out.println("--findInfoById:address_now-OK");
            System.out.println("--findInfoById:IfEnd_OK");
        }
        this.pstmt.close();

        System.out.println("findInfoById:OK");
        return userInfo;
    }
}
