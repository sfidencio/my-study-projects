import java.lang.reflect.RecordComponent;
import java.util.HashMap;
import java.util.Map;

public record Product(int id,
                      String name,
                      Double price,
                      int size,
                      int page) {

    public Map<String, Object> toMap() {
        RecordComponent[] recordComponents = this.getClass().getRecordComponents();
        Map<String, Object> map = new HashMap<>();
        for (RecordComponent recordComponent : recordComponents) {
            try {
                Object value = recordComponent.getAccessor().invoke(this);
                if (this.validateExcludeFields(recordComponent) && this.validateNullOrEmptyFields(value))
                    map.put(recordComponent.getName(), value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    private boolean validateExcludeFields(RecordComponent recordComponent) {
        return !recordComponent.getName().contains("page") && !recordComponent.getName().contains("size") && !recordComponent.getName().contains("null");
    }

    private boolean validateNullOrEmptyFields(Object value) {
        if (value instanceof Boolean && value != null)
            return true;
        if (value instanceof String && value != null && !String.valueOf(value).isEmpty())
            return true;
        return value instanceof Number && value != null;
    }
}

