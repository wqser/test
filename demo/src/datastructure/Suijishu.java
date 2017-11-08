package datastructure;

import java.util.Random;

/**
 * Created by wqs on 2016/11/2.
 */
public class Suijishu {
    public static void main(String a[]){
        double num1=3.0;
        double num2=4.0;
        System.out.println(suiJi(num1,num2));


    }
    public  static double suiJi(double a,double b){
        double sum;
        if(a>b) {
         sum = a + Math.random() * (a - b);
        }else{
            sum = a + Math.random() * (b - a);
        }
        return sum;
    }
}
