package spring.aop.object;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;


public class CustomFileFilter implements FilenameFilter {

    private String extention;

    public CustomFileFilter(String extention) {
        this.extention = extention;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.toUpperCase().endsWith("." + extention.toUpperCase());
    }
}
