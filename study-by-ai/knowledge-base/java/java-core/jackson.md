# 📚 Serialization/Deserialization Guide with Jackson

This guide covers the main concepts and practices for working with **Jackson**, a popular library for Java object serialization and deserialization. We include practical examples with **Spring Boot** to facilitate daily use.

---

## 📖 Table of Contents
1. [⚙️ Basic Structure](#️-basic-structure)
2. [🏷️ Useful Annotations](#️-useful-annotations)
3. [🔗 Complex Types and Collections](#-complex-types-and-collections)
4. [🛠️ Dynamic or Generic Fields](#️-dynamic-or-generic-fields)
5. [🎨 Custom Serialization/Deserialization](#-custom-serializationdeserialization)
6. [🚨 Exception Handling](#-exception-handling)
7. [🌟 Examples with Spring Boot](#-examples-with-spring-boot)
8. [✅ Requirements Summary](#-requirements-summary)

---

## ⚙️ Basic Structure

### 🔑 Default Constructor (no-args)
⚠️ **Important Alert**: Jackson requires a no-args constructor to create instances during deserialization.

```java
public class MyObject {
    public MyObject() {} // Default constructor
}
```

### 📥 Getters and Setters
- **Getters**: Used for serialization.
- **Setters**: Used for deserialization.

If they don't exist, you can configure direct field access with `@JsonAutoDetect`.

---

## 🏷️ Useful Annotations

### 🔖 Key Annotations
- **`@JsonInclude`**: Defines when a field should be included in JSON (e.g., `JsonInclude.Include.NON_NULL`).
- **`@JsonIgnore`**: Ignores a field during serialization/deserialization.
- **`@JsonProperty`**: Defines the field name in JSON.
- **`@JsonFormat`**: Formats dates, numbers, etc.

### 🗂️ Example with JSON Output
```java
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
    @JsonProperty("full_name")
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date birthDate;

    // Getters and Setters
}
```

**JSON Input:**
```json
{
  "full_name": "João Silva",
  "birthDate": "01-01-2000"
}
```

**JSON Output:**
```json
{
  "full_name": "João Silva",
  "birthDate": "01-01-2000"
}
```

---

## 🔗 Complex Types and Collections

### 🛠️ Working with Collections
Jackson naturally handles `List`, `Map`, etc., as long as the elements are serializable.

```java
public class Classroom {
    private List<String> students;

    // Getters and Setters
}
```

**JSON Input:**
```json
{
  "students": ["João", "Maria", "Pedro"]
}
```

**JSON Output:**
```json
{
  "students": ["João", "Maria", "Pedro"]
}
```

---

## 🛠️ Dynamic or Generic Fields

### 🔧 Dynamic Properties
Use `@JsonAnyGetter` and `@JsonAnySetter` to store extra fields in a `Map`.

```java
public class Config {
    private Map<String, String> properties = new HashMap<>();

    @JsonAnySetter
    public void setProperty(String key, String value) {
        properties.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }
}
```

**JSON Input:**
```json
{
  "theme": "dark",
  "language": "en-US"
}
```

**JSON Output:**
```json
{
  "theme": "dark",
  "language": "en-US"
}
```

---

## 🎨 Custom Serialization/Deserialization

### ✨ Custom Serializers
Implement `JsonSerializer` for custom logic.

```java
public class MySerializer extends JsonSerializer<MyType> {
    @Override
    public void serialize(MyType value, JsonGenerator gen, SerializerProvider provider) {
        gen.writeString("Custom value: " + value.getField()); // Custom logic
    }
}
```

---

## 🚨 Exception Handling

### ⚠️ Ignore Unknown Fields
Avoid errors by configuring the `ObjectMapper`.

```java
objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
```

### ⚠️ Ignore Unknown Fields Programmatically

You can configure the `ObjectMapper` programmatically to ignore unknown fields in JSON.

```java
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperExample {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String json = "{\"name\":\"João\",\"age\":30,\"extraField\":\"unknown value\"}";

        Person person = objectMapper.readValue(json, Person.class);
        System.out.println(person);
    }
}

class Person {
    private String name;
    private int age;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}
```

**JSON Input:**
```json
{
  "name": "João",
  "age": 30,
  "extraField": "unknown value"
}
```

**Console Output:**
```
Person{name='João', age=30}
```

---

## 🌟 Examples with Spring Boot

### 🛠️ Configuring `ObjectMapper`
In Spring Boot, the `ObjectMapper` can be configured directly in `application.properties`:

```properties
spring.jackson.deserialization.fail-on-unknown-properties=false
spring.jackson.date-format=dd-MM-yyyy
```

### 🌐 Endpoint Example
```java
@RestController
@RequestMapping("/api/people")
public class PersonController {

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        return ResponseEntity.ok(person);
    }
}
```

**JSON Input:**
```json
{
  "full_name": "Ana Paula",
  "birthDate": "15-08-1995"
}
```

**JSON Response:**
```json
{
  "full_name": "Ana Paula",
  "birthDate": "15-08-1995"
}
```

---

## ✅ Requirements Summary

- Default constructor (or use of `@JsonCreator`).
- Getters/Setters or configuration for direct field access.
- Annotations for serialization control (`@JsonInclude`, `@JsonIgnore`, etc.).
- Nested types must be serializable.
- `ObjectMapper` configurations to handle edge cases.

---

[⬅️ Back](../README.md)