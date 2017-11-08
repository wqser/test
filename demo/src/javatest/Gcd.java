package javatest;

/**
 * Created by wqs on 2016/10/25.
 */
public class Gcd {
    public static void main(String arg[]){
        int a=15;
        int b=20;
        int c=gcd2(a,b);
        System.out.println(c);
    }
    public static int gcd(int p,int q){//递归
        if(q==0){
            return p;
        }
        int r=p%q;
        return gcd(q,r);
    }
    public static int gcd1(int p,int q){//逐个取值
        int a;
        if (p > q) {
            a = gcd1Helper(p, q);
        } else {
            a = gcd1Helper(q, p);
        }
        return a;
    }
   static int gcd1Helper(int p, int q){
        int a = q;
        while (p % q != 0 || a % q != 0){
            q--;
        }
        return q;
    }
    static int gcd2(int a,int b){//循环
        while (b != 0){
            int r= a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
