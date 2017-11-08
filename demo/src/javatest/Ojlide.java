package javatest;

/**
 * Created by wqs on 2016/10/21.
 */
public class Ojlide {
    public static void main(String arg[]){
    int  a=20;
    int  b=15;

    int c=num(a,b);

    System.out.println(c);
}
   /*public static int xymod(int a,int b){  //根据欧几里得算法
        if(a<b){                             //两个数的最大公约数等于这俩个数的最大那个数
            int temp;
            temp=a;
            a=b;
            b=temp;
        }
        if(0==b){
            return a;
        }
        return xymod(a-b,b);
    }*/
       public static int num(int a,int b){
       while(b!=0){
           if(a>=b){
               int n=a;
               a=b;
               b=n-a;
           }else{
             int temp=a;
                  a=b;
                  b=temp;
           }
       }
       return a;
   }
}
