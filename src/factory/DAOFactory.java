package factory;


import bean.SignUp;
import dao.users.MessageDaoImpl;
import dao.users.SignUpDaoImpl;
import dao.users.UserDaoImpl;

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
        System.out.println("getInstance-Message:OK");
        return new SignUpDaoImpl();
    }
}
