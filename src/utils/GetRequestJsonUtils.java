package utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class GetRequestJsonUtils {
    public static JSONObject getRequestJsonObject(HttpServletRequest request) throws IOException {
        System.out.println("getRequestJsonObject...");
        String json = getRequestJsonString(request);
        System.out.println("getRequestJsonObject:OK");
        System.out.println("getRequestJsonObject:$json:"+json);
        return JSONObject.parseObject(json);
    }
    /***
     * 获取 request 中 json 字符串的内容
     *
     * @param request
     * @return : <code>byte[]</code>
     * @throws IOException
     */
    public static String getRequestJsonString(HttpServletRequest request) throws IOException {
        System.out.println("getRequestJsonString...");
        String submitMehtod = request.getMethod();
        // GET
        if (submitMehtod.equals("GET")) {
            System.out.println("getRequestJsonString:OK_ifGet");
            return new String(request.getQueryString().getBytes("iso-8859-1"),"utf-8").replaceAll("%22", "\"");
            // POST
        } else {
            System.out.println("getRequestJsonString:OK_else");
            return getRequestPostStr(request);
        }
    }

    /**
     * 描述:获取 post 请求的 byte[] 数组
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request) throws IOException {
        System.out.println("getRequestPostBytes...");
        int contentLength = request.getContentLength();
        if(contentLength<0){
            System.out.println("getRequestPostBytes:return null");
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        System.out.println("getRequestPostBytes:OK");
        return buffer;
    }

    /**
     * 描述:获取 post 请求内容
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestPostStr(HttpServletRequest request) throws IOException {
        System.out.println("getRequestPostStr...");
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        System.out.println("getRequestPostStr:OK");
        return new String(buffer, charEncoding);
    }


}