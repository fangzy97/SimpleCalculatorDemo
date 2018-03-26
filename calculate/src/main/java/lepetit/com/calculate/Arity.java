package lepetit.com.calculate;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Created by LePetit on 2018/3/9.
 */

public class Arity {

    private BigDecimal b1;
    private BigDecimal b2;

    private final BigDecimal PERCENT = new BigDecimal("100");
    private final BigDecimal ZERO = new BigDecimal("0");

    public Arity(String a, String b){
        this.b1 = new BigDecimal(a);
        this.b2 = new BigDecimal(b);
    }

    public Arity(String value){
        this.b1 = new BigDecimal(value);
    }

    public String plus(){
        return b1.add(b2).setScale(12, BigDecimal.ROUND_HALF_UP).toString();
    }

    public String minus(){
        return b2.subtract(b1).setScale(12, BigDecimal.ROUND_HALF_UP).toString();
    }

    public String multiply(){
        return b1.multiply(b2).setScale(12, BigDecimal.ROUND_HALF_UP).toString();
    }

    public String divid(){
        if (b1.equals(ZERO)){
            return String.valueOf(0x3f3f3f3f);
        }
        return b2.divide(b1, 12, BigDecimal.ROUND_HALF_UP).toString();
    }

    public String percent(){
        return b1.divide(PERCENT, 12, BigDecimal.ROUND_HALF_UP).toString();
    }
}
