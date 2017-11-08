package threadTest;

/**
 * Created by wqs on 2017/3/13.
 */
public class Rabbit extends Thread{

    public void run(){
        for(int i=0;i<100;i++){
            System.out.println("兔子跑了"+i+"步");
        }
    }
}
