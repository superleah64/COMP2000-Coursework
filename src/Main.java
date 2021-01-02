import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args){

    File file = new File("resources\\stockDatabase.txt");
    if (file.exists()){
        System.out.println("Name: " + file.getName());
    }
    else {
        System.out.println("This file does not exist.");
    }

    }
}
