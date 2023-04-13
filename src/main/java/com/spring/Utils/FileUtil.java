package com.spring.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;



import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public class FileUtil {

    public static String readDocOrDocx(String path) {
        StringBuilder content = new StringBuilder();
        File file = new File(path);

        try (FileInputStream fis = new FileInputStream(file)) {
            if (file.getName().toLowerCase().endsWith(".doc")) {
                HWPFDocument doc = new HWPFDocument(fis);
                WordExtractor extractor = new WordExtractor(doc);
                content.append(extractor.getText());
                extractor.close();
            } else if (file.getName().toLowerCase().endsWith(".docx")) {
                XWPFDocument docx = new XWPFDocument(fis);
                XWPFWordExtractor extractor = new XWPFWordExtractor(docx);
                content.append(extractor.getText());
                extractor.close();
            } else {
                throw new IllegalArgumentException("Unsupported file format. Only .doc and .docx are supported.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    public static String readTxt(String path) throws Exception {
        // 读取纯文本文件
        InputStream inputStream = new FileInputStream(new File(path));
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        inputStream.close();
        String content = new String(bytes, getEncoding(path));
        return content;
    }

    public static String readPdf(String path) throws Exception {
        // 读取PDF文件
        PDDocument document = PDDocument.load(new File(path));
        PDFTextStripper stripper = new PDFTextStripper();
        String content = stripper.getText(document);
        document.close();
        return content.replaceAll("\n", "");
    }

    private static String getEncoding(String path) throws Exception {
        // 获取文件编码类型
        FileInputStream inputStream = new FileInputStream(new File(path));
        byte[] bytes = new byte[3];
        inputStream.read(bytes);
        inputStream.close();
        String encoding = "UTF-8";
        if (bytes[0] == -17 && bytes[1] == -69 && bytes[2] == -65) {
            encoding = "UTF-8";
        } else {
            encoding = "GBK";
        }
        return encoding;
    }


}
