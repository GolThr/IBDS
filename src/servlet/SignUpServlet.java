package servlet;

import bean.SignUp;
import bean.User;
import com.alibaba.fastjson.JSONObject;
import factory.DAOFactory;
import utils.GetRequestJsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        System.out.println("SignUpServlet...");
        request.setCharacterEncoding("UTF-8");
//        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setContentType("text/html;charset=utf-8");
        SignUp signup = new SignUp();
        // 读取请求内容
        System.out.println("getJson...");
        //
        JSONObject json = GetRequestJsonUtils.getRequestJsonObject(request);
        System.out.println("json:"+json);
        //
        System.out.println("getJson:OK");
        String Email = json.getString("email");
        boolean ifsuccess = false;

        //数据库查询
        try{
            signup=DAOFactory.getSignupDaoInstance().Signup(Email);
            ifsuccess=true;
        } catch (Exception var9) {
            System.out.println("DataBaseQueryError!!数据库修改错误。。。");
        }
        System.out.println(signup.getDate());
        //返回JSON数据
        JSONObject object = new JSONObject();
        object.put("ifsuccess", ifsuccess);
        object.put("signup_days", signup.getTimes());


        System.out.println(object);
        response.getWriter().print(object);
    }
}
