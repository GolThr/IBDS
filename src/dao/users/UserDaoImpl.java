package dao.users;

import bean.User;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl {
    private DBConnection dbc = null;
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public UserDaoImpl() throws Exception {
        this.dbc = new DBConnection();
        this.conn = this.dbc.getConnection();
    }
    //管理员删除司机信息
    public boolean DeleteEmail(String stu_id) throws Exception {
        System.out.println("DeleteEmail:...");
        boolean flag = false;
        String sql = "DELETE FROM  user_table WHERE Email = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, stu_id);
        System.out.println("ModifyStuInfo-sql:"+sql);

        if(this.pstmt.executeUpdate() > 0){
            flag = true;
        }
        this.pstmt.close();

        return flag;
    }

//管理员修改司机路线
    public boolean ModifyDriverRoute(String email, String NewRoute) throws Exception {
        System.out.println("ModifyDriverRoute:...");
        boolean flag = false;
        String sql = "UPDATE user_table SET route = ? WHERE Email = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, NewRoute);
        this.pstmt.setString(2, email);
        System.out.println("ForgetPwd-sql:" + sql);
        if (this.pstmt.executeUpdate() > 0) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    //管理员搜索司机信息
    public List<User> findUserBySearch(String key_word) throws Exception {
        System.out.println("FindUserBySearch:...");

        String sql = "SELECT * FROM user_table WHERE worknumber LIKE ? or route LIKE ? or username LIKE ? or tel like ?";

        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,"%"+key_word+"%");
        this.pstmt.setString(2,"%"+key_word+"%");
        this.pstmt.setString(3,"%"+key_word+"%");
        this.pstmt.setString(4,"%"+key_word+"%");
        ResultSet rs = this.pstmt.executeQuery();
        System.out.println("--findAllUser:executeOK");
        List<User> UserList = new ArrayList<>();
        while (rs.next()){
            System.out.println("456");
            User user=new User();
            if(rs.getInt("Status")==1){
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setAddress(rs.getString("Address"));
                user.setAvatar(rs.getString("Avatar"));
                user.setUsername(rs.getString("Username"));
                user.setCompany(rs.getString("Company"));
                user.setSex(rs.getString("Sex"));
                user.setWorknumber(rs.getString("Worknumber"));
                user.setTel(rs.getString("Tel"));
                user.setRoute(rs.getString("route"));
                UserList.add(user);
            }

            System.out.println("--searchAlluser:whileEnd_OK");

        }
        this.pstmt.close();


        System.out.println("FindUserBySearch:OK");
        return UserList;
    }





    //显示查询
    public List<User> findUserByStatus(int Status) throws Exception {
        System.out.println("DaoFindUserByStatus:...");
        String sql = "SELECT * FROM user_table WHERE Status=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1,Status);
        ResultSet rs = this.pstmt.executeQuery();
        System.out.println("--findAllBook:executeOK");

        List<User> UserList = new ArrayList<>();
        while (rs.next()){
            System.out.println("123");
            User user=new User();
            user.setEmail(rs.getString("Email"));
            user.setPassword(rs.getString("Password"));
            user.setAddress(rs.getString("Address"));
            user.setAvatar(rs.getString("Avatar"));
            user.setUsername(rs.getString("Username"));
            user.setCompany(rs.getString("Company"));
            user.setSex(rs.getString("Sex"));
            user.setWorknumber(rs.getString("Worknumber"));
            user.setTel(rs.getString("Tel"));
            user.setRoute(rs.getString("route"));
            UserList.add(user);
            System.out.println("--ResearchAlluser:whileEnd_OK");
            System.out.println("route"+user.getRoute());
            System.out.println("name"+user.getUsername());
            System.out.println(user.getCompany());
        }
        this.pstmt.close();
        System.out.println("FindUserByStatus:OK");
        return UserList;
    }




    //管理员添加司机信息
    public boolean addDriver(String Email,String Password,String Name,String Worknumber,String Tel,String Route,String Sex,int status) throws Exception {
        System.out.println("addDriver:...");
        boolean flag = false;
        String sql = "INSERT INTO user_table(Email,Password,Tel,Sex,Worknumber,Username,route,Status) VALUES(?,?,?,?,?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,Email);
        this.pstmt.setString(2,Password);
        this.pstmt.setString(3,Tel);
        this.pstmt.setString(4,Sex);
        this.pstmt.setString(5,Worknumber);
        this.pstmt.setString(6,Name);
        this.pstmt.setString(7,Route);
        this.pstmt.setInt(8,status);
        System.out.println("addDriver-sql:" + sql);
        if (this.pstmt.executeUpdate() > 0) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }




    //注册时添加的信息
    public boolean addUser(String Email,String Company,String Address,String Tel,String Username,String Password,int Status) throws Exception {
        System.out.println("addUser:...");
        boolean flag = false;
        String sql = "INSERT INTO user_table(Email, Password, Tel, Address, Company, Username,Status) VALUES(?,?,?,?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,Email);
        this.pstmt.setString(2,Password);
        this.pstmt.setString(3,Tel);
        this.pstmt.setString(4,Address);
        this.pstmt.setString(5,Company);
        this.pstmt.setString(6,Username);
        this.pstmt.setInt(7,Status);
        System.out.println("addUser-sql:" + sql);
        if (this.pstmt.executeUpdate() > 0) {
            flag = true;
        }
        System.out.println("def");
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
            user.setTel(rs.getString("tel"));
            user.setRoute(rs.getString("route"));
            user.setStatus(rs.getInt("status"));
        }
        this.pstmt.close();
        this.dbc.close();

        System.out.println("DaoFindUserById:OK");
        return user;
    }

    //管理员修改个人信息
    public int ModifyManagerInfor(String Username, String Sex,String Tel,String Email,String Company,String Address) throws Exception {
        System.out.println("ModifyManagerInfo:...");
        int flag = 0;
        String sql = "UPDATE user_table SET Username=?,Sex=?,Tel=?,Company=?,Address=? WHERE Email = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, Username);
        this.pstmt.setString(2, Sex);
        this.pstmt.setString(3, Tel);
        this.pstmt.setString(4, Company);
        this.pstmt.setString(5, Address);
        this.pstmt.setString(6, Email);
        System.out.println("ForgetPwd-sql:" + sql);
        if (this.pstmt.executeUpdate() > 0) {
            System.out.println("acb");
            flag = 1;
        }
        System.out.println("def");
        this.pstmt.close();
        return flag;
    }

    //管理员修改密码
    public boolean ModifyPwd(String Email, String NewPwd,String OriginPwd) throws Exception {
        System.out.println("ModifyPassword:...");
        boolean flag = false;
        String sql = "UPDATE user_table SET Password = ? WHERE Email = ? and Password= ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, NewPwd);
        this.pstmt.setString(2, Email);
        this.pstmt.setString(3, OriginPwd);
        System.out.println("ForgetPwd-sql:" + sql);
        if (this.pstmt.executeUpdate() > 0) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }
}


