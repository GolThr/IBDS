package factory;


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
}
