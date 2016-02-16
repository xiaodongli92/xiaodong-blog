package com.xiaodong.blog.controller;

import com.xiaodong.blog.service.ExportService;
import com.xiaodong.blog.utils.JsonResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by xiaodong on 2016/2/16.
 */
public class AbstractController {

    @Autowired
    private ExportService exportService;

    protected File getDownloadFile(HttpServletRequest request, String fileName) {
        String path = request.getSession().getServletContext().getRealPath("download");
        File fileDir = new File(path);
        if (!fileDir.exists()){
            fileDir.mkdirs();
        }
        File file = new File(path, fileName);
        if (!file.exists()){
            file.mkdir();
        }
        return file;
    }

    private String checkFile(File file){
        if (exportService.isDone(file) && file.exists()) {
            return JsonResponseUtils.ok();
        }
        return JsonResponseUtils.badResult("文件生成中");
    }
}
