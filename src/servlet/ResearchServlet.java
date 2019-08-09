package servlet;

import bean.User;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import factory.DAOFactory;
import utils.GetRequestJsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchServlet")
public class ResearchServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ResearchServlet() {
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
        // 读取请求内容
        System.out.println("getJson...");
        //
        JSONObject json = GetRequestJsonUtils.getRequestJsonObject(request);
        System.out.println("json:"+json);
        System.out.println("getJson:OK");

        boolean isChange = false;
        List<User> userList=new ArrayList<>();

        //数据库查询
        try {
            userList = DAOFactory.getUserDaoInstance().findUserByStatus(1);
            System.out.println("查找了！");
            if (userList.size()>0) {
                System.out.println("查找成功,islogin:");
            } else {
                //isLogin = 2;
                System.out.println("查找失败");
            }
        } catch (Exception var9) {
            System.out.println("DataBaseQueryError!!数据库查询错误。。。");
        }


        JSONArray JsonArray = new JSONArray();
        for(User user : userList){
            JSONObject object = new JSONObject();
            object.put("driver_email",user.getEmail());

            object.put("driver_id", user.getWorknumber());
            object.put("driver_name", user.getUsername());
            object.put("driver_gender", user.getSex());
            object.put("driver_phone", user.getTel());
            object.put("driver_line", user.getRoute());
            JsonArray.add(object);
        }
        System.out.println(JsonArray);
        response.getWriter().print(JsonArray);

    }

}
