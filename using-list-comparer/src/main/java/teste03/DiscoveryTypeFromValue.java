package teste03;

public enum DiscoveryTypeFromValue {

    STRING {
        @Override
        public boolean isType(Object value) {
            return value instanceof String;
        }
    },
    INTEGER {
        @Override
        public boolean isType(Object value) {
            return value instanceof Integer;
        }
    },
    BOOLEAN {
        @Override
        public boolean isType(Object value) {
            return value instanceof Boolean;
        }
    };

    abstract boolean isType(Object value);
}
