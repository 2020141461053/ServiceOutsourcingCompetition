package com.spring.Utils;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import java.io.File;
import java.util.HashMap;

public class BaiduOCRUtil {

    private static final String APP_ID = "32003290";
    private static final String API_KEY = "gfTTHI06wOGFnUwvKCCmpB5D";
    private static final String SECRET_KEY = "GLrc6theV0vyOG9wdoRMrGKpvLfozNRH";

    private static final AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

    public static JSONObject basicGeneral(String filePath) {
        return client.basicGeneral(filePath, new HashMap<String, String>());
    }

    public static JSONObject basicGeneral(File file) {
        return client.basicGeneral(file.getAbsolutePath(), new HashMap<String, String>());
    }
}
