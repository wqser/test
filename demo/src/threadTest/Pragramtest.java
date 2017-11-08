package threadTest;

/**
 * Created by wqs on 2017/3/13.
 */
public class Pragramtest {
    public static void main(String arg[]){
        Pragram pra = new Pragram();
        Thread proxy = new Thread(pra);
        proxy.start();
    }
}
