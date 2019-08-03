package service.UsersService;

import bean.User;
import bean.UserInfo;
import dao.users.StuInfoDao;
import dao.users.StuInfoDaoImpl;
import dao.users.UseDaoImpl;
import dao.users.UserDao;
import db.DBConnection;

public class StuInfoService implements StuInfoDao {
    private DBConnection dbc = null;
    private StuInfoDao dao = null;

    public StuInfoService() throws Exception{
        System.out.println("StuInfoService:...");
        this.dbc = new DBConnection();
        this.dao = new StuInfoDaoImpl(this.dbc.getConnection());
        System.out.println("StuInfoService:OK");
    }


    @Override
    public boolean ModifyStuInfo(String stu_id, String subSql) throws Exception {
        System.out.println("ServiceModifyStuInfo:...");
        System.out.println(this.dao+"");
        boolean flag = false;
        try{
            if(this.dao.findInfoById(stu_id).getStu_id() != null){
                flag = this.dao.ModifyStuInfo(stu_id, subSql);
            }
            System.out.println("ServiceModifyStuInfo:OK");
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }

        return flag;
    }

    @Override
    public boolean hasChecked(String stu_id) throws Exception{
        System.out.println("ServiceHasChecked:...");
        System.out.println(this.dao+"");
        boolean flag = false;
        try{
            if(this.dao.findInfoById(stu_id).getStu_id() != null){
                flag = this.dao.hasChecked(stu_id);
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
    public UserInfo findInfoById(String stu_id) throws Exception {
        System.out.println("ServicefindInfoById:...");
        UserInfo userInfo = null;
        try{
            System.out.println("ServicefindInfoById:try");
            userInfo = this.dao.findInfoById(stu_id);
            System.out.println("ServicefindInfoById:OK");
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }

        return userInfo;
    }
}
