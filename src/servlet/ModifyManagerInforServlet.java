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

@WebServlet(name = "ModifyManagerInforServlet")
public class ModifyManagerInforServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ModifyManagerInforServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ModifyManagerInforServlet...");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        int flag = 0;
        System.out.println("getJson...");
        JSONObject json = GetRequestJsonUtils.getRequestJsonObject(request);
        System.out.println("json:" + json);
        System.out.println("getJson:OK");
        String Username = json.getString("username");
        String Sex = json.getString("gender");
        String Tel = json.getString("phone");
        String Email = json.getString("email");
        String Company = json.getString("company");
        String Address = json.getString("address");


        try {
            flag = DAOFactory.getUserDaoInstance().ModifyManagerInfor(Username,Sex,Tel,Email,Company,Address);
            System.out.println("管理员信息修改成功！");
        } catch (Exception var8) {
            System.out.println("DataBaseQueryError!!数据库修改错误。。。");
        }

        JSONObject object = new JSONObject();
        object.put("ifsuccess", flag);
        System.out.println(object);
        response.getWriter().print(object);

    }


}
