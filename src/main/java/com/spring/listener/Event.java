package com.spring.listener;


import lombok.Getter;
import org.springframework.context.ApplicationEvent;
@Getter
public class Event extends ApplicationEvent {
    private  String task;
    private  String filePath;

    private  int id;
    private  String o_name;
    public Event(Object source, String filePath,String task, String o_name,int id) {
        super(source);
        this.filePath=filePath;
        this.task=task;
        this.o_name=o_name;
        this.id =id;
    }


}
