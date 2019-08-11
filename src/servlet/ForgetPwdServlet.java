package servlet;

import com.alibaba.fastjson.JSONObject;
import factory.DAOFactory;
import utils.GetRequestJsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ForgetPwdServlet")
public class ForgetPwdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ForgetPwdServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ForgetPasswordServlet...");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        boolean flag = false;
        System.out.println("getJson...");
        JSONObject json = GetRequestJsonUtils.getRequestJsonObject(request);
        System.out.println("json:" + json);
        System.out.println("getJson:OK");
        String Email = json.getString("email");
        String Password = json.getString("password");
        try {
            flag = DAOFactory.getUserDaoInstance().ForgetPwd(Email, Password);
            System.out.println("密码找回成功！");
        } catch (Exception var8) {
            System.out.println("DataBaseQueryError!!数据库修改错误。。。");
        }

        JSONObject object = new JSONObject();
        object.put("ifsuccess", flag);
        System.out.println(object);
        response.getWriter().print(object);

    }


}
