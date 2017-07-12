package com.fire.app.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fire.app.util.ContextHolderUtils;

/**
 * @createDate 2017年3月28日下午3:57:31
 * @author wangzhiwang
 * @description
 */
@RequestMapping(value = "/app/evaluate")
@Controller
public class EvaluateController {

    @RequestMapping()
    private String toFireEvent() {

        if (!ContextHolderUtils.isLogin()) {
            return "login/login";
        }

        return "evaluation/evaluation";
    }

    
    @RequestMapping("/download")
    private void downLoad(@RequestParam(required = true) Long id,HttpServletResponse response) {
        String path=File.separator+"home"+File.separator+"fireVisual"+File.separator+"evaluateFile"+File.separator;
        
        String savePath=path+id+".pdf";
        FileInputStream fis = null;
        try {
            // 读取zip文件，并下载到浏览器
            File file = new File(savePath);
            fis = new FileInputStream(file);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            String fileName=null;
            if(id==1){fileName="广州市公安局天河区分局聘请第三方技术服务机构开展消防检测及培训等工作服务采购项目——效能评估报告.pdf";}
            else if(id==2){fileName="天河区幼儿园、小学、中学、大学建筑消防设施隐患评估结果.pdf";}
            else if(id==3){fileName="天河区行政办公建筑单位消防设施隐患评估结果.pdf";}
            else if(id==4){fileName="天河区建筑消防设施隐患评估.pdf";}
            else if(id==5){fileName="天河区特营场所、酒店、宾馆建筑消防设施隐患评估结果.pdf";}
            else if(id==6){fileName="天河区养老院建筑消防设施隐患评估结果.pdf";}
            else if(id==7){fileName="天河区医院建筑消防设施隐患评估结果.pdf";}
            else if(id==8){fileName="天河区幼儿园、小学、中学、大学建筑消防设施隐患评估结果.pdf";}
            
            
            response.reset();
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream ous = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            ous.write(buffer);
            ous.flush();
            ous.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
