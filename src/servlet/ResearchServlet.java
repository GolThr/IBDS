package servlet;

import bean.User;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import factory.DAOFactory;
import utils.GetRequestJsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ResearchServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("servlet-Research...");
        req.setCharacterEncoding("UTF-8");
//        response.setHeader("content-type","text/html;charset=UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        // 读取请求内容
        System.out.println("getJson...");
        //
 //      JSONObject json = GetRequestJsonUtils.getRequestJsonObject(req);
  //      System.out.println("json:" + json);
        //
        System.out.println("getJson:OK");
        //获取到到的前端
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

//        司机邮箱driver_email, 工号driver_id, 姓名driver_name, 性别driver_gender, 电话driver_phone, 线路driver_line


        JSONArray JsonArray = new JSONArray();
        for(User user : userList){
            JSONObject object = new JSONObject();
            object.put("driver_email",user.getEmail());

            object.put("driver_id", user.getWorknumber());
            object.put("driver_name", user.getCompany());
            object.put("driver_gender", user.getSex());
            object.put("driver_phone", user.getTel());
            object.put("driver_line", user.getRoute());
            JsonArray.add(object);
        }
        System.out.println(JsonArray);
        resp.getWriter().print(JsonArray);

    }
}
