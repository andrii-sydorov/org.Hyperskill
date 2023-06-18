package InsideJVM.ClassLoader;

import java.util.Scanner;

public class GetClassLoader {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ClassLoader cl = Scanner.class.getClassLoader();
        System.out.println(cl);
        cl = GetClassLoader.class.getClassLoader();
        System.out.println(cl);
        System.out.println(cl.getName());
        cl = A.class.getClassLoader();
        System.out.println(cl);
        System.out.println(cl.getName());
    }

}

class A {
}
