package spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.dao.impls.SQLiteDAO;
import spring.dao.objects.MP3;

public class Start {
    public static void main(String[] args) {
        MP3 mp3 = new MP3();
        mp3.setName("Song name");
        mp3.setAuthor("Song author");



        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        SQLiteDAO sqLiteDAO = (SQLiteDAO) context.getBean("sqliteDAO");

        System.out.println(sqLiteDAO.insert(mp3));

        System.out.println(sqLiteDAO.getStat());

    }
}
