package threadTest;

import java.util.Random;

/**
 * Created by wqs on 2017/3/13.
 */
public class Test {
    public static void main(String arg[]) {
        Rabbit rab =new Rabbit();
        rab.start();
        Tors tor = new Tors();
        tor.start();
    }
}
