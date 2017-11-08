package javatest;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by wqs on 2016/10/18.
 */
public class Hannuo{
    public static void main(String arg[]) {
        System.out.println("请输入移动的数量：");
        Scanner can=new Scanner(System.in);
        int n=can.nextInt();
        move(n,'a','b','c');
    }
    public static void move(int n,char a,char b, char c){
        if(n==1){
            System.out.println(a+"->"+c);
        }else{
            move(n-1,a,c,b);
            System.out.println(a+"->"+c);
            move(n-1,b,a,c);
        }

    }
}

