package aziz.data;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


public class FactoryMain {

    public static void main(String[] args) {
        Product p1 = ProductFactory.createProduct("loan");

        Supplier<Product> loanSupplier = Loan::new;
        Product p2 = loanSupplier.get();

        Product p3 = ProductFactory.createProductLambda("loan");

    }

    static private class ProductFactory {
        public static Product createProduct(String name){
            switch(name){
                case "loan": return new Loan();
                case "stock": return new Stock();
                case "bond": return new Bond();
                default: throw new RuntimeException("No such product " + name);
            }
        }

        public static Product createProductLambda(String name){
            Supplier<Product> p = map.get(name);
            if(p != null) return p.get();
            throw new RuntimeException("No such product " + name);
        }
    }

    public interface Product {}
    public static class Loan implements Product {
        @Override
        public String toString() {
            return "Loan{}";
        }
    }
    public static class Stock implements Product {
        @Override
        public String toString() {
            return "Stock{}";
        }
    }
    public static class Bond implements Product {
        @Override
        public String toString() {
            return "Bond{}";
        }
    }

    final static private Map<String, Supplier<Product>> map = new HashMap<>();
    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }
}
