import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args){

        // loads a new start form
        Start Start = new Start();
        Start.setVisible(true);

    File file = new File("/Users/leahhumphries/Documents/GitHub/COMP2000-Coursework/resources/stockDatabase.txt");
    if (file.exists()){
        System.out.println("Name: " + file.getName());
    }
    else {
        System.out.println("This file does not exist.");
    }

    }
}
