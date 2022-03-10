package controller;

import java.util.HashMap;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/
public class Demo {
    public static void main(String[] args) {

        //Usage of Hash Map

        HashMap<String, Integer> map = new HashMap<>();
        //How to save a value in a map
        //First argument = Key
        //Second argument = Value
        map.put("A",100);
        map.put("B",200);
        map.put("C",300);
        map.put("D",400);

        //How to get a value from the map using the key
        Integer a = map.get("B");
        System.out.println(a);

        //How to get all keys of the map
        map.keySet();//all keys of the map


        //How to print all the key set of the map
        for (String key : map.keySet()) {
            Integer value = map.get(key);
            System.out.println(key+" "+value);
        }





    }
}
