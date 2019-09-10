package servlet;

import bean.User;
import com.alibaba.fastjson.JSONObject;
import factory.DAOFactory;
import org.apache.commons.mail.HtmlEmail;
import utils.GetRequestJsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GetVerifyCodeServlet extends HttpServlet {

    public GetVerifyCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        request.setCharacterEncoding("UTF-8");
//        response.setHeader("content-type","text/html;charset=UTF-8");
        response.setContentType("text/html;charset=utf-8");
        // 读取请求内容
        //
        JSONObject json = GetRequestJsonUtils.getRequestJsonObject(request);
        //
        System.out.println("发送验证码1");
        String Email = json.getString("email");

        String verifyCode = getRandomNumCode(4);
        boolean flag = sendEmail(Email,verifyCode);
        if(flag == true) {
            System.out.println("发送邮箱验证码成功");
        }else {
            System.out.println("发送邮箱验证码失败");
        }

        //返回JSON数据
        JSONObject object = new JSONObject();
        object.put("verifyCode", "2591");
        System.out.println(object);
        response.getWriter().print(object);
    }
    //邮箱验证码
    public static boolean sendEmail(String emailaddress,String code){
        try {
            HtmlEmail email = new HtmlEmail();//不用更改
            email.setHostName("smtp.qq.com");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setCharset("UTF-8");
            email.addTo(emailaddress);// 收件地址
            email.setFrom("ibds@qq.com", "IBDS");//此处填邮箱地址和用户名,用户名可以任意填写
            email.setAuthentication("ibds@qq.com", "lxaxtgzltjnkcdde");//此处填写邮箱地址和客户端授权码
            Date d = new Date();
            System.out.println(123456);
            String formatted = new SimpleDateFormat("yyyy年MM月dd日").format (d);
            System.out.println(123456);
            email.setSubject("IBDS-邮箱身份验证");//此处填写邮件名，邮件名可任意填写
            email.setMsg("<div style=\"display: flex;flex-direction: column;align-items: center;justify-content: center;\">\n" +
                    "        <div style=\"width: 800px;display: flex;flex-direction: column;align-items: center;justify-content: center;background: #fff;box-shadow: 6px 6px 10px #e9e9e9;border-radius: 5px;margin: 50px 0;border-top: #7dc5c1 solid 5px\">\n" +
                    "            <h1 style=\"font-weight: normal;margin-top: 50px;\">智能公交调度系统</h1>\n" +
                    "            <h2 style=\"width: 95%;margin: 0;font-weight: normal;border-bottom: #e9e9e9 solid 1px;text-align: center;line-height: 70px\">验证码</h2>\n" +
                    "            <h2 style=\"width: 90%;line-height: 30px;font-weight: normal;font-size: 18px;\">亲爱的用户：<br/>您好！<br/>&nbsp;&nbsp;&nbsp;&nbsp;感谢您使用智能公交调度系统。您正在进行邮箱验证，请在验证码输入框中输入此次验证码以完成验证。如非本人操作，请忽略此邮件，由此给您带来的不便请谅解！</h2>\n" +
                    "            <h2 style=\"color: #7dc5c1;margin-bottom: 50px;font-size: 50px;background: #f9f9f9;border: #e9e9e9 solid 1px;border-radius: 5px;padding: 30px 50px\">"+ code+"</h2>\n" +
                    "            <h2 style=\"width: 90%;line-height: 30px;font-weight: normal;font-size: 18px;text-align: right;\">智能公交调度系统运营团队<br/>"+ formatted+ "</h2>\n" +
                    "        </div>\n" +
                    "    </div>");//此处填写邮件内容
            email.send();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static String getRandomNumCode(int number){
        String codeNum = "";
        int [] numbers = {0,1,2,3,4,5,6,7,8,9};
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int next = random.nextInt(10000);//目的是产生足够随机的数，避免产生的数字重复率高的问题
//    			System.out.println(next);
            codeNum+=numbers[next%10];
        }
        System.out.println(codeNum);

        return codeNum;
    }
}



