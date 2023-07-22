package AnonymousClasses;

public class Main {
    public static void main(String[] args) {
        Human human = new Human();
        human.takeMilshake();
    }
}

class Human {
    public void takeMilshake() {
        HumanThought ht = new HumanThought() {
            @Override
            public void print() {
                System.out.println("What to take? Chocolate or strawberry milkshake..");
            }
        };
        ht.print();
    }
}

abstract class HumanThought {
    public void print() {
        System.out.println("This is a very important thought.");
    }
}
