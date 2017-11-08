package javatest;

/**
 * Created by wqs on 2017/2/10.
 */
public class Child extends Canshu {

    {
        System.out.print("子构造块");
    }
    static{
        System.out.print("子静态代码块");
    }

    public Child(){
        System.out.print("子构造函数");
    }
    public static void main(String arg[]){
        System.out.print("子main函数");
        Child a=new Child();

    }
    public void lalla(){
        System.out.print("子普通方法");
    }
    public static void bca(){
        System.out.print("子静态方法");
    }


}
