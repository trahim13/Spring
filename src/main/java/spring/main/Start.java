package spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.dao.impls.SQLiteDAO;
import spring.dao.objects.MP3;

import java.util.ArrayList;
import java.util.List;

public class Start {
    public static void main(String[] args) {
        MP3 mp3 = new MP3();
        mp3.setName("Song name0");
        mp3.setAuthor("Song author0");

        MP3 firstMP3 = new MP3();
        firstMP3.setName("Song name");
        firstMP3.setAuthor("Same author");

        MP3 secondMP3 = new MP3();
        secondMP3.setName("Song name1");
        secondMP3.setAuthor("Same author2");

        ArrayList<MP3> list = new ArrayList<>();
        list.add(mp3);
        list.add(firstMP3);
        list.add(secondMP3);

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        SQLiteDAO sqLiteDAO = (SQLiteDAO) context.getBean("sqliteDAO");

//        System.out.println(sqLiteDAO.insert(firstMP3));
        System.out.println(sqLiteDAO.batchInsert(list));


    }
}
