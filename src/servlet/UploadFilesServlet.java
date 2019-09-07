package servlet;

import factory.DAOFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UploadFilesServlet extends HttpServlet {
    public UploadFilesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // response.setHeader("content-type","text/html;charset=UTF-8");
        //直到下一个注释之前的代码不要动
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        try {
            //这些不需要动
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            List items = upload.parseRequest(request);
            Map params = new HashMap();
            for(Object object:items){
                FileItem fileItem = (FileItem) object;
                //在这个循环里，每次读取从formdata获取到的数据，判断是否是文件
                //这个if判断如果是文件返回false，不是文件返回true
                if (fileItem.isFormField()) {
                    System.out.println(fileItem.getFieldName()+"  "+fileItem.getFieldName().toString()+"  "+"userId");
                    //可以查询fileItem.getFieldName函数的作用，类似于获取json中的key
                    if(fileItem.getFieldName().equals("userId")){//如果这个是userId,则需要做哪些事情
                        //这个if里没有什么东西，只是便于以后修改
                    }/*else if(fileItem.getFieldName().equals("checkWord")){
                        String temp = fileItem.getString();
                        //存入数据库query的query——field
                        System.out.println(fileItem.getString());
                    }*/
                }else{
                    readExcel(fileItem.getInputStream());
                }
            }
            //使用params.get获取参数值 send_time就是key
        } catch (FileUploadException e1) {
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void readExcel(InputStream input) throws Exception  {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(input);
            Sheet sheet = wb.getSheetAt(0); // 获得第一个表单
            int totalRow = sheet.getLastRowNum();// 得到excel的总记录条数
            int columtotal = sheet.getRow(0).getPhysicalNumberOfCells();// 表头总共的列数
            System.out.println("总行数:" + totalRow + ",总列数:" + columtotal);
            for (int i = 1; i <= totalRow; i++) {// 遍历行
                for(int j = 0; j < 12; j ++){
                    sheet.getRow(i).getCell(j).setCellType(CellType.STRING);
                }
                int line_number = Integer.parseInt(sheet.getRow(i).getCell(0).getStringCellValue());
                int year = Integer.parseInt(sheet.getRow(i).getCell(1).getStringCellValue());
                int month = Integer.parseInt(sheet.getRow(i).getCell(2).getStringCellValue());
                int day = Integer.parseInt(sheet.getRow(i).getCell(3).getStringCellValue());
                int hour = Integer.parseInt(sheet.getRow(i).getCell(4).getStringCellValue());
                int minute = Integer.parseInt(sheet.getRow(i).getCell(5).getStringCellValue());
                int all_people= Integer.parseInt(sheet.getRow(i).getCell(6).getStringCellValue());
                double avg_people = Double.parseDouble(sheet.getRow(i).getCell(7).getStringCellValue());
                double max_people = Double.parseDouble(sheet.getRow(i).getCell(8).getStringCellValue());
                double max2_people = Double.parseDouble(sheet.getRow(i).getCell(9).getStringCellValue());
                double min_people = Double.parseDouble(sheet.getRow(i).getCell(10).getStringCellValue());
                double min2_people = Double.parseDouble(sheet.getRow(i).getCell(11).getStringCellValue());
                DAOFactory.getCompanyDaoInstance().Add_log_bus_inf(line_number,year,month,day,hour,minute,all_people,avg_people,max_people,max2_people,min_people,min2_people);
                //在这里上传完毕成功
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex);
        }finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }




}
