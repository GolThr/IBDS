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

@WebServlet(name = "ModifyDriverServlet")
public class ModifyDriverServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ModifyDriverServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ModifyDriverServlet...");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        boolean flag = false;
        System.out.println("getJson...");
        JSONObject json = GetRequestJsonUtils.getRequestJsonObject(request);
        System.out.println("json:" + json);
        System.out.println("getJson:OK");
        String Email = json.getString("email");
        String Route = json.getString("data_new");

        try {
            flag = DAOFactory.getUserDaoInstance().ModifyDriverRoute(Email,Route);
            System.out.println("司机线路修改成功！");
        } catch (Exception var8) {
            System.out.println("DataBaseQueryError!!数据库修改错误。。。");
        }

        JSONObject object = new JSONObject();
        object.put("ifsuccess", flag);
        System.out.println(object);
        response.getWriter().print(object);

    }


}
