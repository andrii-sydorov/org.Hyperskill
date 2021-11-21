package File;

import java.io.File;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

public class CollectToTree {

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the path to your's file: ");
    String path = sc.nextLine();
    sc.close();
    List<File> ls = getAllFiles(new File(path));
    ls.forEach(e -> System.out.println(e));
}

    public static List<File> getAllFiles(File f) {
        File[] files = f.listFiles();
        if (files == null || files.length == 0) {
            return Collections.emptyList();
        }

        List<File> ans = new ArrayList<>();
        for (File children : files) {
            if (children.isDirectory()) {
                ans.addAll(getAllFiles(children));
            } else {
                ans.add(children);
            }
        }

        return ans;
    }
}
