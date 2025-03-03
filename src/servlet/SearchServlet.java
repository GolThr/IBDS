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

public class SearchServlet extends HttpServlet {
    public SearchServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servlet-Search...");
        req.setCharacterEncoding("UTF-8");
//        response.setHeader("content-type","text/html;charset=UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        // 读取请求内容
        System.out.println("getJson...");
        //
            JSONObject json = GetRequestJsonUtils.getRequestJsonObject(req);

              System.out.println("json:" + json);

        System.out.println("getJson:OK");
        //获取到到的前端
        String key_word = json.getString("key_words");
        System.out.println("key_word"+key_word);
        String ifsuccess = "0";
        List<User> userList=new ArrayList<>();
        //数据库查询
        try {

            userList = DAOFactory.getUserDaoInstance().findUserBySearch(key_word);
            System.out.println("查找了！");
            if (userList.size()>0) {
             ifsuccess = "1";
                System.out.println("查找成功,islogin:");
            } else {
                System.out.println("没有人");
            }
        } catch (Exception var9) {
            System.out.println("DataBaseQueryError!!数据库查询错误。。。");
        }

        ////发出(data)：关键词key_words
        //接收(jsonArray)：[0:{ifsuccess:0(失败),1(成功)}, 1:{工号id, 姓名name, 性别gender, 电话phone, 线路line}, ...]
        JSONArray JsonArray = new JSONArray();
        for(User user : userList){
            JSONObject object = new JSONObject();
            object.put("driver_id", user.getWorknumber());
            object.put("driver_name", user.getUsername());
            object.put("driver_gender", user.getSex());
            object.put("driver_phone", user.getTel());
            object.put("driver_line", user.getRoute());
            object.put("driver_email", user.getEmail());
            JsonArray.add(object);
        }
        System.out.println(JsonArray);
        resp.getWriter().print(JsonArray);

    }
}
