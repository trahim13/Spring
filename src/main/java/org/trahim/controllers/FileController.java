package org.trahim.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.trahim.exceptions.BadFileNameException;
import org.trahim.objects.UploadedFile;
import org.trahim.validator.FileValidator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@SessionAttributes("filename")

public class FileController {

    @Autowired
    private FileValidator fileValidator;

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView uploadFile(@ModelAttribute("uploadedFile") UploadedFile uploadedFile, BindingResult result) throws IOException, BadFileNameException {
        ModelAndView modelAndView = new ModelAndView();
        String fileName = null;

        MultipartFile file = uploadedFile.getFile();
        fileValidator.validate(uploadedFile, result);

        if (result.hasErrors()) {
            modelAndView.setViewName("main");
        } else {




                byte[] bytes = file.getBytes();
                fileName = file.getOriginalFilename();

                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.pathSeparator + "tmpFiles");

                if (!dir.exists()) {
                    dir.mkdir();
                }

                File loadFile = new File(dir.getAbsolutePath() + File.separator + fileName);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(loadFile));
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();

                logger.info("uploaded " + loadFile.getAbsolutePath());

                RedirectView redirectView = new RedirectView("fileuploaded");
                redirectView.setStatusCode(HttpStatus.FOUND);
                modelAndView.setView(redirectView);
                modelAndView.addObject("filename", fileName);

            throw new IOException("Folder not found!");
//            throw new BadFileNameException("Bad file name: " + fileName);

        }
        return modelAndView;

    }

    @RequestMapping(value = "/fileuploaded", method = RequestMethod.GET)
    public String fileUploaded() {
        return "fileuploaded";
    }

//    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "IOException exception. Check arguments!")
//    @ExceptionHandler(value = IOException.class)
//    public void handleIOException() {
//        logger.error("IOException handler execute");
//
//    }

    @ExceptionHandler(value = BadFileNameException.class)
    public ModelAndView handleBadFileNameException(Exception e) {
        logger.error("IOException handle execute");
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", e.getMessage());
        return modelAndView;
    }
}




