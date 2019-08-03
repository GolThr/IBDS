package factory;

import dao.users.StuInfoDao;
import dao.users.UserDao;
import service.UsersService.StuInfoService;
import service.UsersService.UserService;

public class DAOFactory {
    public static UserDao getUserDaoInstance() throws Exception{
        System.out.println("getInstance:OK");
        return new UserService();
    }

    public static StuInfoDao getStuInfoDaoInstance() throws Exception{
        System.out.println("getInstance:OK");
        return new StuInfoService();
    }
}
