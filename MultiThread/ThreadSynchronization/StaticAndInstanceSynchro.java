package MultiThread.ThreadSynchronization;

public class StaticAndInstanceSynchro {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        AnotherClass ac = new AnotherClass();
        Thread t1 = new MyStaticThread();
        Thread t2 = new MyInstanceThread(ac);
        t1.start();
        t2.start();
    }

}

class MyStaticThread extends Thread {
    
    @Override
    public void run() {
        for (int i = 0; i < 25; i++) {
            AnotherClass.staticMethod();
        }
    }
}

class MyInstanceThread extends Thread {
    
    AnotherClass ac;

    public MyInstanceThread(AnotherClass ac) {
        this.ac = ac;
    }
    
    @Override
    public void run() {
        for(int i = 0; i < 25; i++) {
            ac.instanceMethod();
        }
    }
}

class AnotherClass {
    
    public static synchronized void staticMethod() {
        System.out.println("From static method");
    }
    
    public synchronized void instanceMethod() {
        System.out.println("From instance method");
    }
}
