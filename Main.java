import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    
    public static void main(String[] args) {
        try {
            Scanner keyb = new Scanner(System.in);
            File file = new File(keyb.nextLine());
            Dictionary d = new Dictionary(file);
            System.out.print("check word> ");
            String a = keyb.nextLine();
            if (d.findWord(a))
                System.out.println("word found");
            else 
                System.out.println("word not found");
            System.out.print("add new word> ");
            a = keyb.nextLine();
            d.addWord(a);
            System.out.print("remove word> ");
            a = keyb.nextLine();
            d.deleteWord(a);
            System.out.print("search for similar words> ");
            a = keyb.nextLine();
            System.out.println(Arrays.toString(d.findSimilar(a)));
            System.out.print("Save Updated Dictionary (Y/N)> ");
            if (keyb.nextLine().toUpperCase().equals("Y"))
                saveDictionary(d);
            keyb.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        
    }

    public static void saveDictionary(Dictionary d){
        Scanner keyb = new Scanner(System.in);
            LinkedList words = d.getWords();
            Node current = words.head;
            System.out.println("Enter filename> ");
            File outFile = new File(keyb.nextLine());
            keyb.close();
            try (PrintWriter writer = new PrintWriter(outFile)) {
                while (current != null){
                    writer.println(current.data);
                    current = current.next;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("Dictionary saved successfully.");
    }
}
