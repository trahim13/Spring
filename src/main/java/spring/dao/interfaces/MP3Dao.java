package spring.dao.interfaces;

import spring.dao.objects.MP3;
import java.util.List;

public interface MP3Dao {

    void insert(MP3 mp3);

    void insert(List<MP3> mp3List);

    void delete(MP3 mp3);

    MP3 getMP3ByID(int id);

    List<MP3> getMP3ListByName(String name);

    List<MP3> getMP3ListByAuthor(String author);

    void delete(int id);

    int getMP3Count();


}
