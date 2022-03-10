package controller;

import java.util.HashMap;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/
public class Demo {
    public static void main(String[] args) {

        HashMap<String, Integer> map = new HashMap<>();
        map.put("A",100);
        map.put("B",200);
        map.put("C",300);
        map.put("D",400);


        Integer a = map.get("B");
        System.out.println(a);

        map.keySet();//all keys of the map


        //how to get all the key set of the map
        for (String s : map.keySet()) {
            System.out.println(s);
        }





    }
}
