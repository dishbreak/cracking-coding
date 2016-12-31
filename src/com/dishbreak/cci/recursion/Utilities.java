package com.dishbreak.cci.recursion;

import java.lang.reflect.Array;
import java.util.*;

public class Utilities<T> {

   public static <T> ArrayList<T> pluck(int index, ArrayList<T> list) {
       ArrayList<T> result = new ArrayList<>();
       
       for (int i = 0; i < list.size(); i++) {
           if (i != index) result.add(list.get(i));
       }
       
       return result;
   }
   
   public static <T> T[] pluck(int index, T[] array) {
       if (array.length == 0 || index < 0 || index > array.length-1 ) return array;
       
       T object = null;
       for (T item : array) {
           if (item != null) {
               object = item;
               break;
           }
       }
       if (object == null) return array;
       @SuppressWarnings("unchecked")
       T[] result = (T[]) Array.newInstance(object.getClass(), array.length - 1);
       
       if (index != 0) {
           for (int i = 0; i < index; i++) {
               result[i] = array[i];
           }
       }
       
       if (index != array.length - 1) {
           for (int i = 0; i < array.length - index; i++) {
               result[index + i] = array[index + i + 1];
           }
       }
       
       return result;
   }

}
