package servlet;

import bean.User;
import com.alibaba.fastjson.JSONObject;
import factory.DAOFactory;
import utils.GetRequestJsonUtils;

import javax.naming.Name;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdDriverServlet")
public class AddDriverServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AddDriverServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        System.out.println("servlet-addBook...");
        request.setCharacterEncoding("UTF-8");
//        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setContentType("text/html;charset=utf-8");
        User user=new User();
        // 读取请求内容
        System.out.println("getJson...");
        //
        JSONObject json = GetRequestJsonUtils.getRequestJsonObject(request);
        System.out.println("json:"+json);
        System.out.println("getJson:OK");

        String Email = json.getString("email");
        String Name = json.getString("name");
        String Worknumber =json.getString("id");
        String Tel = json.getString("phone");
        String Route =json.getString("line");
        String Sex = json.getString("gender");
        int status=1;
        String Password="123456";
        boolean flag = false;
        System.out.println(Email+" "+Name+" " + Worknumber+" "+ Tel+" "+Route+" "+Sex);
        try{
            System.out.println("abc");
            flag=DAOFactory.getUserDaoInstance().addDriver(Email,Password,Name,Worknumber,Tel,Route,Sex,status);
        }catch (Exception var9){
            System.out.println("DataBaseQueryError!!数据库添加错误。。。");
        }

        JSONObject object = new JSONObject();
        object.put("ifsuccess",flag);
        System.out.println(object);
        response.getWriter().print(object);

    }

}
