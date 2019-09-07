package factory;


import bean.SignUp;
import dao.users.*;

public class DAOFactory {
//    public static AthleteDaoImpl getAthleteDaoInstance() throws Exception{
//        System.out.println("getInstance:OK");
//        return new AthleteDaoImpl();
//    }
    public static UserDaoImpl getUserDaoInstance() throws Exception{
        System.out.println("getInstance:OK");
        return new UserDaoImpl();
    }
    public static MessageDaoImpl getMessageDaoInstance() throws Exception{
        System.out.println("getInstance-Message:OK");
        return new MessageDaoImpl();
    }
    public static SignUpDaoImpl getSignupDaoInstance() throws Exception{
        System.out.println("getInstance-Signup:OK");
        return new SignUpDaoImpl();
    }
    public static DepatureDaoImpl getDepatureDaoInstance() throws Exception{
        System.out.println("getInstance-Depature:OK");
        return new DepatureDaoImpl();
    }
    public static CompanyDaoImpl getCompanyDaoInstance()throws Exception{
        System.out.println("getInstance-Depature:OK");
        return new CompanyDaoImpl();
    }



}
