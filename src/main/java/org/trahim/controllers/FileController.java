package org.trahim.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file")MultipartFile file) {
        String name = null;

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                name = file.getOriginalFilename();

                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.pathSeparator + "tmpFiles");

                if (!dir.exists()) {
                    dir.mkdir();
                }

                File uploadFile = new File(dir.getAbsolutePath() + File.separator + name);

                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadFile));
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();

                logger.info("uploaded" + uploadFile.getAbsolutePath());

                return "You successfully upload file= " + name;
            } catch (IOException e) {
                return "You failed upload file= " + name + " = >" + e.getMessage();
            }

        } else {
            return "You failed upload file= " + name + "because the file was empty ";
        }

    }

}


