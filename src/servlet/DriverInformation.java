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

public class DriverInformation extends HttpServlet {

    public DriverInformation() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servlet-driverInformation...");
        req.setCharacterEncoding("UTF-8");
//        response.setHeader("content-type","text/html;charset=UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        User user=new User();
        // 读取请求内容
        System.out.println("getJson...");
        //修改信息ajax_modifyDriverInfo_POST
        //发出(data)：用户名username, 性别gender, 手机号phone, 邮箱email, 公交公司company, 地址address
        //接收(json)：ifsuccess:0(失败),1(成功)
        JSONObject json = GetRequestJsonUtils.getRequestJsonObject(req);
        System.out.println("json:"+json);
        System.out.println("getJson:OK");

        String username = json.getString("username");
        String sex = json.getString("gender");
        String tel = json.getString("phone");
        String email = json.getString("email");
        String company = json.getString("company");
        String address = json.getString("address");


        String ifsuccess="0";
        int success=0;
        try{
            success= DAOFactory.getUserDaoInstance().ModifyManagerInfor(email,tel,address,company,sex,username);
            if (success==1){
                ifsuccess="1";
                System.out.println("修改个人信息成功");
            }else{
                System.out.println("修改个人·不成功");
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
