package controller;

import java.util.HashMap;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/
public class Demo {
    public static void main(String[] args) {

        HashMap<String, Integer> map = new HashMap<>();
        //how to save a value in a map
        //first arguement = Key
        //second arguement = Value
        map.put("A",100);
        map.put("B",200);
        map.put("C",300);
        map.put("D",400);

        //how to get a value from the map using the key
        Integer a = map.get("B");
        System.out.println(a);

        //how to get all keys of the map
        map.keySet();//all keys of the map


        //how to print all the key set of the map
        for (String s : map.keySet()) {
            System.out.println(s);
        }





    }
}
