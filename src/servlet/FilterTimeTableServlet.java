package servlet;

import bean.Depature;
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

public class FilterTimeTableServlet extends HttpServlet {
    public FilterTimeTableServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FilterTimeTable..");
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
        String Company = json.getString("company");
        String Email = json.getString("email");
        String Filter_Type = json.getString("filter_type");
        String Route=json.getString("line");

        List<Depature> depatureList=new ArrayList<>();
            System.out.println("11All"+Company+" "+Route);
            System.out.println(Filter_Type);

        if(Filter_Type.equals("all")){
            try {
                depatureList = DAOFactory.getDepatureDaoInstance().FilterTimeAll(Company,Route);
                System.out.println("查找了！");
                if (depatureList.size()>0) {
                    System.out.println("查找全部成功");
                } else {
                    System.out.println("没有人");
                }
            } catch (Exception var9) {
                System.out.println("DataBaseQueryError!!数据库查询错误。。。");
            }

            //发出(data)：公司名称company, 司机邮箱email, 筛选类别filter_type(查看全部all, 只看自己self), 线路line
            //接收(jsonArray)：司机姓名driver_name, 发车时间start_time
            JSONArray JsonArray = new JSONArray();
            for(Depature depature : depatureList){
                JSONObject object = new JSONObject();

                object.put("driver_name", depature.getUesrname());
                object.put("start_time", depature.getTime());
                JsonArray.add(object);
            }
            System.out.println(JsonArray);
            resp.getWriter().print(JsonArray);

        }else{
            try {
                depatureList = DAOFactory.getDepatureDaoInstance().FilterTimeSelf(Email);
                System.out.println("查找了！");
                if (depatureList.size()>0) {
                    System.out.println("查找自己成功");
                } else {
                    System.out.println("没有人");
                }
            } catch (Exception var9) {
                System.out.println("DataBaseQueryError!!数据库查询错误。。。");
            }

            //发出(data)：公司名称company, 司机邮箱email, 筛选类别filter_type(查看全部all, 只看自己self), 线路line
            //接收(jsonArray)：司机姓名driver_name, 发车时间start_time
            JSONArray JsonArray = new JSONArray();
            for(Depature depature : depatureList){
                JSONObject object = new JSONObject();

                object.put("driver_name", depature.getUesrname());
                object.put("start_time", depature.getTime());

                JsonArray.add(object);
            }
            System.out.println(JsonArray);
            resp.getWriter().print(JsonArray);

        }
        //数据库查询

    }
}
