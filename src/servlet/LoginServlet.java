package servlet;

import bean.User;
import com.alibaba.fastjson.JSONObject;
import factory.DAOFactory;
import utils.GetRequestJsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        System.out.println("servlet...");
        request.setCharacterEncoding("UTF-8");
//        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setContentType("text/html;charset=utf-8");
        User user = new User();
        String isLogin = "false";
        // 读取请求内容
        System.out.println("getJson...");
        //
        JSONObject json = GetRequestJsonUtils.getRequestJsonObject(request);
        System.out.println("json:"+json);
        //
        System.out.println("getJson:OK");
        String username = json.getString("username");
        String password = json.getString("password");
        System.out.println("username:"+username);
        System.out.println("password:"+password);

        //数据库查询
        try{
            user = DAOFactory.getUserDaoInstance().findUserById(username);
            System.out.println("查询成功！"+"stu_id:"+user.getStu_id()+"password:"+user.getPassword());
            if(user.getPassword() != null){
                if(user.getPassword().equals(password)){
                    isLogin = "true";
                    System.out.println("登录成功,islogin:");
                }else{
                    isLogin = "pwdError";
                    System.out.println("登录失败,islogin:");
                }
            }else{
                isLogin = "userNull";
                System.out.println("该用户未注册，请先注册！");
            }
        }catch (Exception e){
            System.out.println("DataBaseQueryError!!数据库查询错误。。。");
        }
        //

        //返回JSON数据
        JSONObject object = new JSONObject();
        object.put("stu_id", user.getStu_id());
        object.put("password", user.getPassword());
        object.put("gender", user.getGender());
        object.put("theme", user.getTheme());
        object.put("username", user.getUsername());
        object.put("ischecked", user.getIschecked());
        object.put("isLogin", isLogin);
        System.out.println(object);
        response.getWriter().print(object);
    }
}
