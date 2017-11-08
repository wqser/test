package javatest;


/**
 * Created by wqs on 2017/2/10.
 */
public class Canshu {
    {
        System.out.print("父构造块");
    }
  static{
     System.out.print("父静态代码块");
 }

  public Canshu(){
     System.out.print("父构造函数");
 }
  public static void main(String arg[]){
     System.out.print("父main函数");
 }
  public void a(){
      System.out.print("父普通方法");
  }
  public static void abc(){
      System.out.print("父静态方法");
  }
}
