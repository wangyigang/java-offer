package month01.generic;

import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TestHashSet {
    @Test
    public void test(){
//        HashSet<String> set = new HashSet<>();
//        set.add(null);
//        set.add(null);
//        System.out.println(set.size());
        Set<String> set = Collections.synchronizedSet(new HashSet<>());
    }
}
