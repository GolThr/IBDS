package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String Driver = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/bussystem?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private Connection conn = null;

    public DBConnection() throws Exception{
        System.out.println("DBConnection:...");
        try{
            Class.forName(Driver);
            this.conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("DBConnection:OK");
        }catch (Exception e){
            System.out.println("数据库连接错误:...");
            throw e;
        }
    }

    public Connection getConnection(){
        return this.conn;
    }

    public void close() throws Exception{
        if(this.conn != null){
            try{
                this.conn.close();
            }catch (Exception e){
                throw e;
            }
        }
    }
}
