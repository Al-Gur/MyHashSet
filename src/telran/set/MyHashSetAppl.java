package telran.set;

import telran.set.interfaces.ISet;
import telran.set.model.MyHashSet;

public class MyHashSetAppl {
    public static void main(String[] args) {
        ISet<String> mySet = new MyHashSet<>();
        System.out.println(mySet.size());
        mySet.add("Boston");
        mySet.add("Atlanta");
        System.out.println(mySet.add("Chicago"));
        mySet.add("New York");
        mySet.add("Detroit");
        System.out.println(mySet.size());
        System.out.println(mySet.add("Chicago"));
        System.out.println(mySet.size());
        System.out.println("=== Contains ===");
        System.out.println(mySet.contains("Detroit"));
        System.out.println(mySet.contains("Dallas"));
        System.out.println("=== Remove ===");
        System.out.println(mySet.remove("New York"));
        System.out.println(mySet.size());
        System.out.println(mySet.remove("New York"));
        System.out.println(mySet.size());
        mySet.forEach(System.out::println);
    }
}
