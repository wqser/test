package threadTest;

/**
 * Created by wqs on 2017/3/13.
 */
public class Pragram implements Runnable{
    @Override
    public void run() {
        for(int i= 0;i<100;i++){
            System.out.print("\t"+i);
        }
    }
}
