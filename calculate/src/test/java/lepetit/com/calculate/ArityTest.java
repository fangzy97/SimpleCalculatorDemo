package lepetit.com.calculate;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by LePetit on 2018/3/10.
 */
public class ArityTest {

    private Arity arity;
    private Arity arity2;

    @Before
    public void setUp() throws Exception {
        this.arity = new Arity("3.01", "2.69");
        this.arity2 = new Arity("12.34");
    }

    @Test
    public void plus() throws Exception {
        assertEquals("5.7", stringFormat(arity.plus()));
    }

    @Test
    public void minus() throws Exception {
        assertEquals("-0.32", stringFormat(arity.minus()));
    }

    @Test
    public void multiply() throws Exception {
        assertEquals("8.0969", stringFormat(arity.multiply()));
    }

    @Test
    public void divid() throws Exception {
        assertEquals("0.893687707641", stringFormat(arity.divid()));
    }

    @Test
    public void percent() throws Exception {
        assertEquals("0.1234", stringFormat(arity2.percent()));
    }

    private String stringFormat(String string){
        if (string.indexOf(".") > 0){
            string = string.replaceAll("0+?$", "");
            string = string.replaceAll("[.]$", "");
        }
        string = string.replaceAll("^0+", "");

        if (string.indexOf(".") == 0){
            string = "0" + string;
        }

        return string;
    }

}