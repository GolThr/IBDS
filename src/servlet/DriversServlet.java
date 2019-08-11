package servlet;

import bean.SignUp;
import bean.User;
import com.alibaba.fastjson.JSONObject;
import factory.DAOFactory;
import utils.GetRequestJsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DriversServlet extends HttpServlet {
    public DriversServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //初始化界面信息ajax_initDriverDays_POST
        //发出(data)：司机邮箱email, 公司名称company

        System.out.println("servlet-Drivers...");
        req.setCharacterEncoding("UTF-8");
//        response.setHeader("content-type","text/html;charset=UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        // 读取请求内容
        System.out.println("getJson...");
        //
        JSONObject json = GetRequestJsonUtils.getRequestJsonObject(req);
        System.out.println("json:"+json);
        //
        System.out.println("getJson:OK");
        String Email = json.getString("email");
        String company = json.getString("company");
       String ifsuccess="0";
       String  ifsignup="0";
        SignUp signUp=new SignUp();
        //数据库查询
        try{
            signUp= DAOFactory.getSignupDaoInstance().findsignbyemail(Email,company);
            System.out.println("添加成功！");
            if (signUp.getName()!=null) {
                ifsuccess="1";
               System.out.println("成功获取到");
            }else {
                System.out.println("不成功");
            }
        } catch (Exception var9) {
            System.out.println("DataBaseQueryError!!数据库查询错误。。。");
        }
        //接收(json)：ifsuccess:0(失败),1(成功), 线路line, 当天时间today_date(日期示例：2019年8月9日),
        // 公司管理员姓名admin, 公司电话phone, 是否签到if_signup(未签到0, 已签到1), 签到天数signup_days
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
       String date=df.format(new Date());
       if(signUp.getDate()!=null&&signUp.getDate().equals(date)){
           ifsignup="1";
       }else{
           ifsignup="0";
       }
        System.out.println(ifsuccess);

        //返回JSON数据
        JSONObject object = new JSONObject();
        object.put("line", signUp.getRoute());
        object.put("today_date",date);
        object.put("admin",signUp.getName());
        object.put("phone",signUp.getTel());
        object.put("if_signup",ifsignup);
        object.put("ifsuccess",ifsuccess);
        object.put("signup_days",signUp.getTimes());


        System.out.println(object);
        resp.getWriter().print(object);
    }
}
