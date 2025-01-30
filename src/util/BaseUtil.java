package util;

import java.util.Random;

public class BaseUtil {

    public static String generateID() {
        Random random = new Random();
        int code = 10000 + random.nextInt(900000);
        return Integer.toString(code);
    }

}
