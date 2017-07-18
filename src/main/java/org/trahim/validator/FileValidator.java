package org.trahim.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.trahim.objects.UploadedFile;

/**
 * Created by root on 18.07.2017.
 */
@Component
public class FileValidator implements Validator {
    @Override
    public void validate(Object o, Errors errors) {
        UploadedFile file = (UploadedFile) o;

        if (file.getFile().getSize() == 0) {
            errors.rejectValue("file", "uploadForm.selectFile", "Please select a file.");

        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
