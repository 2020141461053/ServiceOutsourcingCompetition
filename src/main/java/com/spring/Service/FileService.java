package com.spring.Service;

import com.spring.DAO.FilesDAO;
import com.spring.entity.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FileService {

    @Autowired
    FilesDAO filesDAO;

    public Files getById(int id){
        return  filesDAO.findById(id);
    }

    public Files  save( String filename ){
        String new_name=renameFile(filename);
        Files files=new Files();
        files.setFilename(new_name);
        files.setStatus(0);
        files.setO_name(filename);
        files.setDesc_("正在处理");
        filesDAO.save(files);
        return  files;
    }


    public String renameFile(String filename) {
        System.out.println(filename);
        String extension = "";
        int i = filename.lastIndexOf('.');
        if (i > 0) {
            extension = filename.substring(i + 1);
        }
        StringBuilder builder = new StringBuilder();
        builder.append(filename.substring(0, i)); // 文件名（不包括扩展名）
        builder.append("_"); // 添加分隔符
        Random random = new Random();
        for (int j = 0; j < 6; j++) {
            char c = (char) (random.nextInt(26) + 'a'); // 生成随机字符
            builder.append(c);
        }
        builder.append("."); // 添加扩展名分隔符
        builder.append("docx"); // 添加扩展名
        String newName = builder.toString();
        return newName;
    }

    public void setStatus1(int id){
        Files files=filesDAO.findById(id);
        files.setStatus(1);
        files.setDesc_("处理完成");
        filesDAO.save(files);
    }
    public void setStatus3(int id){
        Files files=filesDAO.findById(id);
        files.setStatus(3);
        files.setDesc_("处理失败");
        filesDAO.save(files);
    }
}
