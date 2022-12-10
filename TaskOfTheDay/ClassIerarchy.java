package TaskOfTheDay;

public class ClassIerarchy {

    public static void main(String[] args) {
        C c = new C();
    }
}

class A {

    protected int n = 10;

}

class B extends A {

    protected int n = 20;

    public B() {
        super();
        System.out.println(n);
    }
}

class C extends B {

    protected int n = 30;

    public C() {
        this(5);
        System.out.println(n);
    }

    public C(int n) {
        super();
        super.n = n;
        System.out.println(n);
    }
}
