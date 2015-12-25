package com.loiot.baqi.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.loiot.baqi.constant.ApplicationConst;
import com.loiot.baqi.controller.response.AjaxResponse;
import com.loiot.baqi.vo.FileUploadBean;
import com.loiot.commons.utils.FileUtil;

@Controller
// 声明该类为控制器类
@RequestMapping("/file")
public class FileUploadController {
    protected Logger logger = Logger.getLogger(FileUploadController.class);

    // 将文件上传请求映射到该方法
    @RequestMapping("/upload"   )  
    public Object handleFormUpload(
            HttpServletResponse response,
            @RequestParam(value = "file", required = false) CommonsMultipartFile[] mFile,
            @RequestParam(value = "CKEditorFuncNum", required = false, defaultValue = "") String CKEditorFuncNum) { 
        String file1Path="";    
       /* if (mFile!=null && !mFile.isEmpty()) {
              //创建一个文件
                file1Path="fck/temp/"+new Date().getTime()+".jpg";
                File newFile1;
                try {
                    newFile1 = FileUtil.createFile(ApplicationConst.UPLOAD_PIC_PATH+file1Path);
                    //将文件写到新的文件当中
                    mFile.getFileItem().write(newFile1);
                    file1Path = ApplicationConst.UPLOAD_PIC_URL+file1Path;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
           String resText =  "<script language='javascript'>"
            +"window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum+ ",'" + file1Path + "',''" + ")"
            +"</script>";*/
            this.printScript(response, "");
          return null;
    }
    
    @RequestMapping(value="/upload2")
    public Object upload2(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {  
    	FileUploadBean  returnBean = new FileUploadBean();
    	//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        System.out.println(myFileName);  
                        //重命名上传后的文件名  
                        String fileName = "demoUpload" + file.getOriginalFilename();  
                        //定义上传路径  
                        String path = "D:/" + fileName;  
                        File localFile = new File(path);  
                        file.transferTo(localFile);  
                    }  
                }  
                //记录上传该文件后的时间  
                int finaltime = (int) System.currentTimeMillis();  
                System.out.println(finaltime - pre);  
            }  
              
        }  
        returnBean.setFilenames("sfsdfd");
        this.printScript(response, "{\"a\":1}");
       return null;
        // return AjaxResponse.OK(returnBean);
    }  
    
    /**
     * 输出脚本
     * 
     * @param str
     */
    public static void printScript(HttpServletResponse response, String str) {
        response.setContentType("application/json;charset='utf-8'");
        response.setCharacterEncoding("utf-8");
        response.setHeader("charset", "utf-8");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.print(str);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pw.close();
        }
    }


   

}