package spring.aop.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.aop.object.FileManager;

public class Start {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        FileManager fileManager = (FileManager) context.getBean("fileManager");

        String folder = "C:\\Windows\\System32";

        fileManager.getExtentionalList(folder);
        fileManager.getExtentionalCount(folder);
    }
}
