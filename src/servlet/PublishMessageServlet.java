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

public class PublishMessageServlet extends HttpServlet {
    public PublishMessageServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("servlet-publishMessage...");
        req.setCharacterEncoding("UTF-8");
//        response.setHeader("content-type","text/html;charset=UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        User user=new User();
        // 读取请求内容
        System.out.println("getJson...");
        //发出(data)：邮箱email, 留言内容note_content
        //接收(json)：ifsuccess:0(失败),1(成功)
        JSONObject json = GetRequestJsonUtils.getRequestJsonObject(req);
        System.out.println("json:"+json);
        System.out.println("getJson:OK");

        String Email = json.getString("email");
        String Message = json.getString("note_content");
             int status=0;
       String ifsuccess="0";
        boolean success=false;
        try{
            success= DAOFactory.getMessageDaoInstance().addMessage(Email,Message);
            if (success){
                ifsuccess="1";
                System.out.println("添加留言成功");
            }else{
                System.out.println("添加留言不成功");
            }
        }catch (Exception var9){
            System.out.println("DataBaseQueryError!!数据库添加错误。。。");
        }

        JSONObject object = new JSONObject();
        object.put("ifsuccess",ifsuccess);
        System.out.println(object);
        resp.getWriter().print(object);

    }


}
