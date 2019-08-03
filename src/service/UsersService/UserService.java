package service.UsersService;

import bean.User;
import dao.users.UserDao;
import dao.users.UseDaoImpl;
import db.DBConnection;

public class UserService implements UserDao {
    private DBConnection dbc = null;
    private UserDao dao = null;

    public UserService() throws Exception{
        System.out.println("ServiceUserService:...");
        this.dbc = new DBConnection();
        this.dao = new UseDaoImpl(this.dbc.getConnection());
        System.out.println("ServiceUserService:OK");
    }

    @Override
    public boolean addUser(User user) throws Exception {
        System.out.println("ServiceAddUser:...");
        System.out.println(this.dao+"");
        boolean flag = false;
        try{
            if(this.dao.findUserById(user.getStu_id()).getStu_id() == null){
                flag = this.dao.addUser(user);
            }
            System.out.println("ServiceAddUser:OK");
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }

        return flag;
    }

    @Override
    public boolean changePassword(String stu_id, String password) throws Exception {
        System.out.println("ServicechangePassword:...");
        System.out.println(this.dao+"");
        boolean flag = false;
        try{
            if(this.dao.findUserById(stu_id).getStu_id() != null){
                flag = this.dao.changePassword(stu_id, password);
            }
            System.out.println("ServiceHasChecked:OK");
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }

        return flag;
    }

    @Override
    public User findUserById(String user_id) throws Exception {
        System.out.println("ServiceFindUserById:...");
        User user = null;
        try{
            System.out.println("ServiceFindUserById:try");
            user = this.dao.findUserById(user_id);
            System.out.println("ServiceFindUserById:OK");
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }

        return user;
    }
}
