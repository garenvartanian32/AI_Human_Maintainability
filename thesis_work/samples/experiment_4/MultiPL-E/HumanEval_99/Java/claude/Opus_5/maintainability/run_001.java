import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;

class Problem {
    public static long closestInteger(String value) {
        BigDecimal number = new BigDecimal(value);
        BigDecimal roundedNumber = number.setScale(0, RoundingMode.HALF_UP);

        return roundedNumber.longValue();
    }
}