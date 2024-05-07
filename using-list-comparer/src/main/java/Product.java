import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

//@Data
@Getter
@Setter
@ToString
public class Product  {
    private BigInteger id;
    private String description;
    private BigDecimal price;
}

