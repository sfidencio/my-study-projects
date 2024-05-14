package teste02;

public enum ComparadorStrategy {

    MAIOR {
        @Override
        public boolean comparar(Integer num1, Integer num2) {
            return num1.compareTo(num2) > 0;
        }
    },

    MENOR {
        @Override
        public boolean comparar(Integer num1, Integer num2) {
            return num1.compareTo(num2) < 0;
        }
    },
    IGUAL {
        @Override
        boolean comparar(Integer num1, Integer num2) {
            return num1.compareTo(num2) == 0;
        }
    };

    abstract boolean comparar(Integer num1, Integer num2);

}

