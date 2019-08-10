package servlet;

import bean.User;
import com.alibaba.fastjson.JSONObject;
import factory.DAOFactory;
import utils.GetRequestJsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        //
        System.out.println("getJson:OK");
        String Email = json.getString("email");
        String password = json.getString("password");
        int isLogin = 0;

        //数据库查询
        try{
            user= DAOFactory.getUserDaoInstance().findUserById(Email);
            System.out.println("添加成功！");
            if (user.getPassword() != null) {
                if (user.getPassword().equals(password)) {
                    isLogin = 1;
                    System.out.println("登录成功,islogin:");
          //          DAOFactory.getDriverDaoInstance().hasLogin(email);
                } else {
                    isLogin = 0;
                    System.out.println("登录失败,islogin:");
                }
            }else {
                isLogin = 2;
                System.out.println("该用户未注册，请先注册！");
            }
        } catch (Exception var9) {
            System.out.println("DataBaseQueryError!!数据库查询错误。。。");
        }

        //返回JSON数据
        JSONObject object = new JSONObject();
        object.put("password", user.getPassword());
        object.put("username",user.getUsername());
        object.put("avatar",user.getAvatar());
        object.put("gender",user.getSex());
        object.put("phone",user.getTel());
        object.put("email",user.getEmail());
        object.put("company",user.getCompany());
        object.put("address",user.getAddress());
        object.put("ifsuccess", isLogin);
        object.put("user_type",user.getStatus());
        object.put("work_number",user.getWorknumber());
        System.out.println(object);
        response.getWriter().print(object);
    }
}
