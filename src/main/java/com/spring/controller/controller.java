package com.spring.controller;

import com.spring.Service.FileService;
import com.spring.Utils.RemindUtil;
import com.spring.Utils.UploadUtils;
import com.spring.entity.Files;
import com.spring.listener.Event;
import com.spring.result.Result;
import com.spring.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class controller {
    @Value("${base_up}")
    String base_up;

    @Autowired
    private FileService fileService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping("/task/{task}")
    public Result dealTask( @RequestPart("file") MultipartFile file,@PathVariable("task") String task){
        String path="";
        try {
            String originalFilename = file.getOriginalFilename();
            String r_name= UploadUtils.getRandomString(6)+originalFilename;
            file.transferTo(new File(base_up + r_name));
            path= r_name;
            assert originalFilename != null;
        }
        catch (IOException e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult("创建任务失败");
        }
        Files  files =fileService.save(path);
        Event event = new Event(this,files.getFilename(),RemindUtil.getRemind(task),path,files.getId());
        eventPublisher.publishEvent(event);
        return ResultFactory.buildSuccessResult(files.getId());//id
    }

    @GetMapping ("/down/{id}")
    public Result getURL(@PathVariable("id") int id){
        Files files=fileService.getById(id);
        if (files.getStatus()==1)
            return ResultFactory.buildSuccessResult(files.getFilename());
        else return ResultFactory.buildFailResult(files.getDesc_());
    }


    @GetMapping("/test")
    public  Result test(){

        return ResultFactory.buildSuccessResult("yes");
    }


}
