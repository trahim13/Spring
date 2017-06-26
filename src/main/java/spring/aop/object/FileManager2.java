package spring.aop.object;

import org.springframework.stereotype.Component;
import spring.aop.annotations.ShowResult;
import spring.aop.annotations.ShowTime;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
@Component
public class FileManager2 {


    public Set<String> getExtentionalList(String folder) {
        File dir = new File(folder);
        Set<String> extList = new TreeSet<>();

        for (String fileName : dir.list()) {
            File file = new File(dir.getAbsolutePath() + "\\" +
                    fileName);

            int i = fileName.lastIndexOf(".");
            if (file.isFile() && i != -1) {
                extList.add(fileName.substring(i + 1, fileName.length()).toLowerCase());
            }
        }

        return extList;
    }


    public Map<String, Integer> getExtentionalCount(String folder) {
        File dir = new File(folder);
        Map<String, Integer> map = new HashMap<>();

        for (String ext : getExtentionalList(folder)) {
            FilenameFilter filenameFilter = new CustomFileFilter(ext);
            map.put(ext, dir.listFiles(filenameFilter).length);
        }
        return map;
    }
}
