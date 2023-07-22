package Patterns.Decorator;

public class PizzaTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Pizza plainPizza = new PlainPizza();
        Pizza peperonniPizza = new PeperonniPizza(plainPizza);
        Pizza hotPeperPizza = new HotPeperPizza(plainPizza);
        System.out.println(plainPizza.cook());
        System.out.println(peperonniPizza.cook());
        System.out.println(hotPeperPizza.cook());
    }

}

interface Pizza {
    String cook();
}

class PlainPizza implements Pizza {

    @Override
    public String cook() {
        // TODO Auto-generated method stub
        return "Pizza";
    }

}

class ToppingWrapper implements Pizza {

    Pizza pizza;
    
    public ToppingWrapper() {
        super();
    }
    
    public ToppingWrapper(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String cook() {
        // TODO Auto-generated method stub
        return pizza.cook();
    }

}

class PeperonniPizza extends ToppingWrapper {
    
    
    
    public PeperonniPizza(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String cook() {
        return super.cook() + " Peperonni Pizza";
    }
}

class HotPeperPizza extends ToppingWrapper {
    
    
    
    public HotPeperPizza(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String cook() {
        return super.cook() + " HotPeper Pizza";
    }
}
