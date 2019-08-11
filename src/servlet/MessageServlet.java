package servlet;

import bean.Message;
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

public class MessageServlet extends HttpServlet {
    public MessageServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servlet-Message...");
        req.setCharacterEncoding("UTF-8");
//        response.setHeader("content-type","text/html;charset=UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        // 读取请求内容
        System.out.println("getJson...");

        System.out.println("getJson:OK");
        JSONObject json = GetRequestJsonUtils.getRequestJsonObject(req);
        String Type = json.getString("init_type");
        List<Message> messageList=new ArrayList<>();
        //数据库查询
        if(Type.equals("all")){
            try {
                messageList= DAOFactory.getMessageDaoInstance().findAllUsers();
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
            for(Message message : messageList){
                JSONObject object = new JSONObject();
                object.put("author", message.getName());
                object.put("author_avatar", message.getAvater());
                object.put("publish_time", message.getDate());
                object.put("notes_content",message.getMessage());

                JsonArray.add(object);
            }
            System.out.println(JsonArray);
            resp.getWriter().print(JsonArray);
        }else{
            try {
                messageList= DAOFactory.getMessageDaoInstance().findSelfMessage(Type);
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
            for(Message message : messageList){
                JSONObject object = new JSONObject();
                object.put("author", message.getName());
                object.put("author_avatar", message.getAvater());
                object.put("publish_time", message.getDate());
                object.put("notes_content",message.getMessage());

                JsonArray.add(object);
            }
            System.out.println(JsonArray);
            resp.getWriter().print(JsonArray);
        }
    }
}
