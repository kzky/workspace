package edu.kzk.java_samle.basic.collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapSample {
    public static void main(String[] args) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        System.out.println(map.size());

        Set<String> set = new HashSet<String>();
        set.add("1");
        // set.add("2");
        set.add("3");
        System.out.println(set.size());

        map.keySet().retainAll(set);

        System.out.println(map); // {3=three, 1=one}
        System.out.println(map.size());
        System.out.println(set.size());
    }

}
