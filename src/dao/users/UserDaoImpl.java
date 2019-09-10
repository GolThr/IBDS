package dao.users;

import bean.User;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//这里边所有的操作都是对数据库中的user_table表进行的
public class UserDaoImpl {
    private DBConnection dbc = null;
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public UserDaoImpl() throws Exception {
        this.dbc = new DBConnection();
        this.conn = this.dbc.getConnection();
    }

    //管理员删除司机信息     从前端接收到Email，传到数据库删除对应的司机信息(注意可能有触发器的存在),对应的是DeleteServlet
    public boolean DeleteEmail(String Email) throws Exception {
        System.out.println("DeleteEmail:...");
        boolean flag = false;
        String sql = "DELETE FROM user_table WHERE Email = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, Email);
        System.out.println("ModifyStuInfo-sql:"+sql);
        if(this.pstmt.executeUpdate() > 0){
            System.out.println("bef");
            flag = true;
        }
        System.out.println("abc");
        this.pstmt.close();
        return flag;
    }

    //管理员修改司机路线     从前端接收到Email和管理员修改的新的路线Route，传到数据库更新对应的司机的线路信息(注意可能有触发器的存在),对应的是ModifyDriverServlet
    public boolean ModifyDriverRoute(String Email, String Route) throws Exception {
        System.out.println("ModifyDriverRoute:...");
        boolean flag = false;
        String sql = "UPDATE user_table SET route = ? WHERE Email = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, Route);
        this.pstmt.setString(2, Email);
        System.out.println("ForgetPwd-sql:" + sql);
        if (this.pstmt.executeUpdate() > 0) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    //管理员搜索司机信息     从前端接收到输入的key_word，传到数据库选择信息对应的司机的信息(注意可能有触发器的存在),对应的是SearchServlet
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

    //显示查询（即管理公交人员界面）       从前端接收到管理员的Company并且保证查询到的人员身份都为司机（即1），传到数据库选择对应的司机的信息(注意可能有触发器的存在),对应的是ResearchServlet
    public List<User> findUserByStatus(String Company,int Status) throws Exception {
        System.out.println("DaoFindUserByStatus:...");
        String sql = "SELECT * FROM user_table WHERE Status=? and company=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1,Status);
        this.pstmt.setString(2,Company);
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

    //管理员添加司机信息     根据管理员输入的信息将一条新的司机记录插入到表中（注意触发器的存在）对应的是AddDriverServlet
    public boolean addDriver(String Email,String Password,String Tel,String Sex,String Worknumber,String Username,String Route,int status,String company) throws Exception {
        System.out.println("addDriver:...");
        boolean flag = false;
        String sql = "INSERT INTO user_table(Email,Password,Tel,Sex,Worknumber,Username,route,Status,company) VALUES(?,?,?,?,?,?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,Email);
        this.pstmt.setString(2,Password);
        this.pstmt.setString(3,Tel);
        this.pstmt.setString(4,Sex);
        this.pstmt.setString(5,Worknumber);
        this.pstmt.setString(6,Username);
        this.pstmt.setString(7,Route);
        this.pstmt.setInt(8,status);
        this.pstmt.setString(9,company);
        System.out.println("addDriver-sql:" + sql);
        if (this.pstmt.executeUpdate() > 0) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    //注册时添加的信息      根据注册时填入的信息向表中添加一条管理员的记录，对应的是RegisterServlet
    public boolean addUser(String Email,String Password,String Tel,String Address,String Company,String Sex,String Username,int Status) throws Exception {
        System.out.println("addUser:...");
        boolean flag = false;
        String sql = "INSERT INTO user_table(Email, Password, Tel, Address, Company, Sex,Username,Status) VALUES(?,?,?,?,?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,Email);
        this.pstmt.setString(2,Password);
        this.pstmt.setString(3,Tel);
        this.pstmt.setString(4,Address);
        this.pstmt.setString(5,Company);
        this.pstmt.setString(6,Sex);
        this.pstmt.setString(7,Username);
        this.pstmt.setInt(8,Status);
        System.out.println("addUser-sql:" + sql);
        if (this.pstmt.executeUpdate() > 0) {
            flag = true;
        }
        System.out.println("def");
        this.pstmt.close();
        return flag;
    }

    //忘记密码      根据前端传来的Email和新的密码,找到对应的邮箱并更新其密码,对应的是ForgetPwdServlet
    public boolean ForgetPwd(String Email, String Password) throws Exception {
        System.out.println("ForgetPassword:...");
        boolean flag = false;
        String sql = "UPDATE user_table SET Password = ? WHERE Email = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, Password);
        this.pstmt.setString(2, Email);
        System.out.println("ForgetPwd-sql:" + sql);
        if (this.pstmt.executeUpdate() > 0) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    //登录      根据传来的邮箱Email,找到对应的密码在LoginServlet中与前端传来的密码匹配
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
            user.setWorknumber(rs.getString("Worknumber"));
        }
        this.pstmt.close();
        this.dbc.close();
        System.out.println("DaoFindUserById:OK");
        return user;
    }

    //管理员/司机修改个人信息    根据前端传来的Email找到对应的记录并更新信息，对应的是ModifyManagerInforServlet/DriverInformation  共用一个
    public int ModifyManagerInfor(String Email, String Tel,String Address,String Company,String Sex,String Username) throws Exception {
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

    //管理员修改密码     根据前端传来的数据进行修改  对应的是ModifyPwdServlet
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


