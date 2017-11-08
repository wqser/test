package threadTest;

/**
 * Created by wqs on 2017/3/13.
 */
public class Tors extends Thread{
    public void run(){
        for(int i=0;i<100;i++){
            System.out.println("龟跑了"+i+"步");
        }
    }
}
