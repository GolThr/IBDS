package servlet;

import bean.Depature;
import bean.Message;
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

public class LogServlet extends HttpServlet {
    public LogServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servlet-Log...");
        req.setCharacterEncoding("UTF-8");
//        response.setHeader("content-type","text/html;charset=UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        // 读取请求内容
        System.out.println("getJson...");

        System.out.println("getJson:OK");
        JSONObject json = GetRequestJsonUtils.getRequestJsonObject(req);
        String Email = json.getString("email");
        String Date = json.getString("date");
        List<Depature> messageList=new ArrayList<>();
        //数据库查询
            try {
                messageList= DAOFactory.getDepatureDaoInstance().ResearchLog(Email,Date);
                System.out.println("查找了！");
                if (messageList.size()>0) {

                    System.out.println("查找留言成功,islogin:");
                } else {
                    System.out.println("没有人");
                }
            } catch (Exception var9) {
                System.out.println("DataBaseQueryError!!数据库查询错误。。。");
            }

            //接收(jsonArray)：发布用户author, 发布用户头像author_avatar, 发布时间publish_time(时间格式示例：2019-08-05), 留言内容notes_content
            JSONArray JsonArray = new JSONArray();
            for(Depature message : messageList){
                JSONObject object = new JSONObject();
                object.put("driver_name", message.getUesrname());
                object.put("start_time", message.getTime());
                JsonArray.add(object);
            }
            System.out.println(JsonArray);
            resp.getWriter().print(JsonArray);

    }
}
