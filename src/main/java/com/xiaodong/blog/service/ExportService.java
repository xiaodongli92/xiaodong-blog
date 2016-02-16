package com.xiaodong.blog.service;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.concurrent.*;

/**
 * Created by xiaodong on 2016/2/16.
 */
@Service
public class ExportService {

    private static final Logger LOG = LoggerFactory.getLogger(ExportService.class);

    private ExecutorService executor = Executors.newFixedThreadPool(5);
    private FutureTask futureTask;

    public boolean isDone(File fileName) {
        return futureTask.isDone() && fileName.exists();
    }

    public byte[] getFileDownload(File file) throws IOException {
        return FileUtils.readFileToByteArray(file);
    }

    public Future<?> exportCodeItem(File file,String jsonString) {
        Task task = new Task(file,jsonString);
        futureTask = new FutureTask<>(task);
        return executor.submit(futureTask);
    }

    class Task implements Callable<Boolean> {
        File file;
        String jsonString;
        public Task() {

        }

        public Task(File file,String jsonString) {
            this.file = file;
            this.jsonString = jsonString;
        }

        @Override
        public Boolean call() throws Exception {
            if (file.exists()) {
                FileUtils.forceDelete(file); //如果文件存在首先删除
            }
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(jsonString);
                return Boolean.TRUE;
            } catch (Exception e) {
                LOG.error("导出codeItem失败，{}", e);
                return Boolean.FALSE;
            }
        }
    }
}
