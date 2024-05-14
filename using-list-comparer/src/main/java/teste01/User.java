package teste01;

import java.math.BigInteger;

public class User {
    private BigInteger id;
    private String name;

    @Override
    public String toString() {
        return "teste01.User{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                '}';
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

