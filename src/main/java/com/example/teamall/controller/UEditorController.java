package com.example.teamall.controller;
import com.baidu.ueditor.ActionEnter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@RestController
public class UEditorController {

    @Autowired
    private HttpServletRequest request;
    @RequestMapping(value = "/config",method = RequestMethod.GET)
    public void getUEditorConfig(HttpServletResponse response){
        String rootPath = "src/main/resources/static";
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/upload")
    public UEditorUploadModel upload(HttpServletRequest request) throws Exception {
        // 判断enctype属性是否为multipart/form-dat
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart)
            throw new IllegalArgumentException(
                    "上传内容不是有效的multipart/form-data类型.");

//        // Create a factory for disk-based file items
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//
//        // Create a new file upload handler
//        ServletFileUpload upload = new ServletFileUpload(factory);
        //上传到远程服务器
        // Parse the request
//        List<?> items = upload.parseRequest(request);
//        Iterator iterator = items.iterator();

        StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
        Iterator<String> iterator = req.getFileNames();
        while (iterator.hasNext()) {

//            FileItem item = (FileItem) iterator.next();
//
//            InputStream uploadedStream = item.getInputStream();
//            HashMap<String, InputStream> files = new HashMap<String, InputStream>();
//            files.put(item.getName(), uploadedStream);
//            uploadToFarService(files);
//            uploadedStream.close();
            MultipartFile file = req.getFile(iterator.next());
            assert file != null;
            byte[] fileBody = file.getBytes();
            String fileNames = file.getOriginalFilename();

            uploadToFarService(fileNames,fileBody);
//            uploadToFarService(file);
//            uploadedStream.close();

        }
        return null;
    }

    public void uploadToFarService(String fileName,byte[] body_data) {
        final String NEWLINE = "\r\n";  // 换行，或者说是回车
        final String PREFIX = "--";     // 固定的前缀
        final String BOUNDARY = "#";    // 分界线,可以是任意字符串，建议写长一点，这里简单的写了一个#
        HttpURLConnection httpConn = null;
        BufferedInputStream bis = null;
        DataOutputStream dos = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            // 实例化URL对象。调用URL有参构造方法，参数是一个url地址；
            URL urlObj = new URL("http://123.57.161.212:9936/imageserver/upload");
            // 调用URL对象的openConnection()方法，创建HttpURLConnection对象；
            httpConn = (HttpURLConnection) urlObj.openConnection();
            // 调用HttpURLConnection对象setDoOutput(true)、setDoInput(true)、setRequestMethod("POST")；
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            httpConn.setRequestMethod("POST");
            // 设置Http请求头信息；（Accept、Connection、Accept-Encoding、Cache-Control、Content-Type、User-Agent），不重要的就不解释了，直接参考抓包的结果设置即可
            httpConn.setUseCaches(false);
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            httpConn.setRequestProperty("Cache-Control", "no-cache");
            // 这个比较重要，按照上面分析的拼装出Content-Type头的内容
            httpConn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + BOUNDARY);
            // 这个参数可以参考浏览器中抓出来的内容写，用chrome或者Fiddler抓吧看看就行
            httpConn.setRequestProperty(
                    "User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30)");
            // 调用HttpURLConnection对象的connect()方法，建立与服务器的真实连接；
            httpConn.connect();
            // 调用HttpURLConnection对象的getOutputStream()方法构建输出流对象；
            dos = new DataOutputStream(httpConn.getOutputStream());

            // 获取表单中上传附件的数据，写入到输出流对象（根据上面分析的抓包的内容格式拼凑字符串）；
            if (body_data != null && body_data.length > 0) {
                dos.writeBytes(PREFIX + BOUNDARY + NEWLINE);// 像请求体中写分割线，就是前缀+分界线+换行
                // 格式是:Content-Disposition: form-data; name="请求参数名"; filename="文件名"
                // 我这里吧请求的参数名写成了uploadFile，是死的，实际应用要根据自己的情况修改
                // 不要忘了换行
                dos.writeBytes("Content-Disposition: form-data; " + "name=\""
                        + "uploadFile" + "\"" + "; filename=\"" + fileName
                        + "\"" + NEWLINE);
                // 换行，重要！！不要忘了
                dos.writeBytes(NEWLINE);
                dos.write(body_data); // 上传文件的内容
                dos.writeBytes(NEWLINE); // 最后换行
            }
            dos.writeBytes(PREFIX + BOUNDARY + PREFIX + NEWLINE); // 最后的分割线，与前面的有点不一样是前缀+分界线+前缀+换行，最后多了一个前缀
            dos.flush();
            dos.close();
            // 调用HttpURLConnection对象的getInputStream()方法构建输入流对象；
            byte[] buffer = new byte[8 * 1024];
            int c = 0;
            System.out.println(httpConn.getResponseCode());
            // 调用HttpURLConnection对象的getResponseCode()获取客户端与服务器端的连接状态码。如果是200，则执行以下操作，否则返回null；
            if (httpConn.getResponseCode() == 200) {
                System.out.println("=================");
                httpConn.getResponseMessage();
                System.out.println(httpConn.getResponseMessage());
                bis = new BufferedInputStream(httpConn.getInputStream());
                while ((c = bis.read(buffer)) != -1) {
                    baos.write(buffer, 0, c);
                    baos.flush();
                }
            }
//            // 将输入流转成字节数组，返回给客户端。
//            System.out.println(new String(baos.toByteArray()));
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }

//        try {
//            String BOUNDARY = "**********"; // 定义数据分隔线
//            URL url = new URL();
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            // 发送POST请求必须设置如下两行
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            conn.setUseCaches(false);
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
//            conn.setRequestProperty("Charsert", "UTF-8");
//            conn.setRequestProperty("Content-Type",
//                    "multipart/form-data; boundary=" + BOUNDARY);
//
//
//            OutputStream out = new DataOutputStream(conn.getOutputStream());
//
//            byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线
//            Iterator iter = files.entrySet().iterator();
//            int i=0;
//            while (iter.hasNext()) {
//                i++;
//                Map.Entry entry = (Map.Entry) iter.next();
//                String key = (String) entry.getKey();
//                System.out.println("==============key:"+key);
//                InputStream val = (InputStream) entry.getValue();
//                String fname = key;
//                File file = new File(fname);
//                StringBuilder sb = new StringBuilder();
//                sb.append("--");
//                sb.append(BOUNDARY);
//                sb.append("\r\n");
//                sb.append("Content-Disposition: form-data;name=\"file" + i
//                        + "\";filename=\"" + key + "\"\r\n");
//                sb.append("Content-Type:application/octet-stream\r\n\r\n");
//
//                byte[] data = sb.toString().getBytes();
//                out.write(data);
//                DataInputStream in = new DataInputStream(val);
//                int bytes = 0;
//                byte[] bufferOut = new byte[1024];
//                while ((bytes = in.read(bufferOut)) != -1) {
//                    out.write(bufferOut, 0, bytes);
//                }
//                out.write("\r\n".getBytes()); // 多个文件时，二个文件之间加入这个
//                in.close();
//            }
//            out.write(end_data);
//            out.flush();
//            out.close();
//            // 定义BufferedReader输入流来读取URL的响应
//            InputStream is = conn.getInputStream();
//            System.out.println(is);
//            int ch;
//            StringBuffer b = new StringBuffer();
//            while ((ch = is.read()) != -1) {
//                b.append((char) ch);
//            }
//            String s = b.toString();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(
//                    conn.getInputStream(), "UTF-8"));
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }

//        } catch (Exception e) {
//            System.out.println("发送POST请求出现异常！" + e);
//            e.printStackTrace();
//        }
    }

//    @RequestMapping(value = "upload")
//    public String uploadImage(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{
//        response.setContentType("text/html;charset=UTF-8");
//
////        ReturnUploadImage rui = null;
////        https://blog.csdn.net/zzq900503/article/details/72900927
//        return "";
//    }
}
