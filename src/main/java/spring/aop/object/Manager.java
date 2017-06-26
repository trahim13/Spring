package spring.aop.object;

import java.util.Map;
import java.util.Set;

public interface Manager {
    Set<String> getExtentionalList(String folder);

    Map<String, Integer> getExtentionalCount(String folder);
}
