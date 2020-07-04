package aziz.data;

public class TransactionWithCurrency {
        private final Currency currency;
        private final double value;

        public TransactionWithCurrency(Currency currency, double value) {
            this.currency = currency;
            this.value = value;
        }

        public Currency getCurrency() {
            return currency;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return currency + " " + value;
        }
    }