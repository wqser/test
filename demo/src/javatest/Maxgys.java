package javatest;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

/**
 * Created by wqs on 2016/10/18.
 */
public class Maxgys {
    public static void main(String arg[]){
        int m=0;
        List<Integer> num=new ArrayList<Integer>();
        List<Integer> num1=new ArrayList<Integer>();
        System.out.println("输入第一个数");
        Scanner can1=new Scanner(System.in);
        int a=can1.nextInt();
        System.out.println("输入第二个数");
        Scanner can2=new Scanner(System.in);
        int b=can2.nextInt();
        if(a>b){
            if(a%b==0){
                System.out.println(b+"和"+a+"的最大公约数是"+b);
            }
            else{
             for(int i=1;i<=b;i++){
                 if(b%i==0){
                     System.out.println(b+"的约数有"+i+"\t");
                     num.add(i);
                 }
                 for(int c:num){
                    if(a%c==0){
                     num1.add(c);
                    }
                 }
             }
            }
            for(int n=0;n<num1.size();n++){

                if(m<num1.get(n)){
                    m=num1.get(n);
                }

            }
            System.out.println("他们的最大公约数为"+m);
        }

        if(b>a){
            if(b%a==0){
                System.out.println(b+"和"+a+"的最大公约数是"+a);
            }
            else{
                for(int i=1;i<a;i++){
                    if(a%i==0){
                        System.out.println(a+"的约数有"+i+"\t");
                        num.add(i);
                    }
                    for(int c:num){
                        if(b%c==0){
                            num1.add(c);
                        }
                    }
                }
            }

        }
        for(int n=0;n<num1.size();n++){

            if(m<num1.get(n)){
                m=num1.get(n);
            }
        }
        System.out.println("他们的最大公约数为"+m);
    }
}
