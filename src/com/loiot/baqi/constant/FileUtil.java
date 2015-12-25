package com.loiot.baqi.constant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
    public static File getFile(MultipartFile imgFile, String desDir, String oldFilePath) {
        List<String> fileTypes = new ArrayList<String>();
        fileTypes.add("jpg");
        fileTypes.add("jpeg");
        fileTypes.add("bmp");
        fileTypes.add("gif");
        fileTypes.add("png");
        String fileName = imgFile.getOriginalFilename();
        // 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        // 对扩展名进行小写转换
        ext = ext.toLowerCase();
        File file = null;
        if (fileTypes.contains(ext)) { // 如果扩展名属于允许上传的类型，则创建文件
            File desdir = new File(desDir);
            if (!desdir.exists()) {
                desdir.mkdirs();
            }
            File oldfile=new File(""+"/"+oldFilePath);
            if(oldfile.exists()){
                oldfile.delete();
            }
            file = new File(desdir, new Date().getTime() + "." + ext);
            try {
                imgFile.transferTo(file); // 保存上传的文件
                Thread.sleep(500);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return file;
    }
}
