package com.spring.listener;


import com.spring.Service.FileService;
import com.spring.Utils.BaiduOCRUtil;
import com.spring.Utils.FileUtil;
import com.spring.Utils.GptApiUtil;
import org.apache.commons.io.FilenameUtils;


import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.Text;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Async
@Component
public class MyListener implements ApplicationListener<Event> {
    @Autowired
    FileService fileService;

    @Value("${base_up}")
    String base_up;
    @Value("${base_down}")
    String base_down;

    @Override
    public void onApplicationEvent(Event event) {
        String task = event.getTask();
        String filePath = event.getFilePath();
        String o_name = base_up + event.getO_name();
        Path path = Paths.get(o_name);
        String contentType = "";
        String input = "";
        System.out.println(o_name);
        List<String> output;
        try {
            contentType = Files.probeContentType(path);
        } catch (IOException e) {
            fileService.setStatus3(event.getId());
            throw new RuntimeException(e);
        }
        if (contentType != null && contentType.startsWith("image/")) {
            JSONArray wordsResult = (JSONArray) BaiduOCRUtil.basicGeneral(o_name).get("words_result");
            for(Object obj:wordsResult) {
                JSONObject  j=(JSONObject)obj;
                input = input + " " + j.get("words");
            }
        } else if ("pdf".equals(FilenameUtils.getExtension(filePath)) || "application/pdf".equals(contentType)) {
            try {
                input = FileUtil.readPdf(o_name);
            } catch (Exception e) {
                fileService.setStatus3(event.getId());
                throw new RuntimeException(e);
            }
        } else if ("doc".equals(FilenameUtils.getExtension(filePath)) || "docx".equals(FilenameUtils.getExtension(filePath)) || "application/msword".equals(contentType)) {
            input = FileUtil.readDocOrDocx(o_name);

        } else if (contentType != null && contentType.startsWith("text/")) {
            try {
                input = FileUtil.readTxt(o_name);
            } catch (Exception e) {
                fileService.setStatus3(event.getId());
                throw new RuntimeException(e);
            }
        } else {
            fileService.setStatus3(event.getId());
            throw new IllegalArgumentException("Unsupported file type: " + filePath);
        }
        String prompt = task;
        try {
            output = GptApiUtil.chat(prompt + input);
        } catch (Exception e) {
            fileService.setStatus3(event.getId());
            throw new RuntimeException(e);
        }
        try {
            createDoc(base_down + filePath, output);
            fileService.setStatus1(event.getId());
        } catch (Exception e) {
            fileService.setStatus3(event.getId());
            throw new RuntimeException(e);
        }
    }

    public static void createDoc(String filePath, List<String> content) {
        System.out.println(content.size());
        try {
            // Create a WordprocessingMLPackage
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
            // Create a new MainDocumentPart
            MainDocumentPart mainDocumentPart = wordMLPackage.getMainDocumentPart();

            // Create a new Paragraph object for each line of content
            for (String line : content) {
                // Replace escape characters with actual line breaks
                line = line.replaceAll("\\s{2,}", "\n");
                P paragraph = new P();
                R run = new R();
                Text text = new Text();
                text.setValue(line);
                run.getContent().add(text);
                paragraph.getContent().add(run);
                mainDocumentPart.getContent().add(paragraph);


            }

            // Save the document to the specified file path
            wordMLPackage.save(new File(filePath));

            System.out.println("Word document created successfully!");
        } catch (Docx4JException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> a=new ArrayList<>();
        a.add("sss");
        a.add("test");
        try {
            createDoc("D:\\test.docx", a);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

