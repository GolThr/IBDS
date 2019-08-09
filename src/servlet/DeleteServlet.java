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

public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("servlet-Delete...");
        req.setCharacterEncoding("UTF-8");
//        response.setHeader("content-type","text/html;charset=UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        User user = new User();
        // 读取请求内容
        System.out.println("getJson...");
        //
        JSONObject json = GetRequestJsonUtils.getRequestJsonObject(req);
        //删除信息ajax_deleteDriver_POST
        //发出(data)：邮箱email
        //接收(json)：ifsuccess:0(失败),1(成功)
        System.out.println("json:" + json);
        //
        System.out.println("getJson:OK");
        //获取到到的前端
        String Email = json.getString("email");
       String ifsuccess = "0";
        boolean success=false;
        //数据库查询
        try {

            success = DAOFactory.getUserDaoInstance().DeleteEmail(Email);
            System.out.println("删除了！");
            if (success) {
                ifsuccess="1";
                System.out.println("删除成功,islogin:");
            } else {
                ifsuccess="0";
                System.out.println("删除失败！");
            }
        } catch (Exception var9) {
            System.out.println("DataBaseQueryError!!数据库查询错误。。。");
        }

        //返回JSON数据

        JSONObject object = new JSONObject();
        object.put("ifsuccess", ifsuccess);
        System.out.println(object);
        resp.getWriter().print(object);


    }
    }
