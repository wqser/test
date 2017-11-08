package javatest;

/**
 * Created by wqs on 2016/10/21.
 */
public class Eightqueen {
     final int max=8;
     int num=0;
     int array[]=new int[max];
    public static void main(String arg[]){
        Eightqueen e=new Eightqueen();
        e.check(0);
    }

    public void check(int n){
        if(n==max){
            print();
           return;
        }
        for(int i=0;i<max;i++) {
            array[n]=i;
            if(choose(array,n)){
                check(n+1);
            }
        }
    }
    static boolean choose(int rows[],int n){//n为要检查的列
        for(int i=0;i<n;i++){
            if(rows[n]==rows[i]||Math.abs(n-i)==Math.abs(rows[n]-rows[i])){
                return false;
            }
        }
        return true;
    }
    public void print(){
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+1+",");
        };
        System.out.println();
        num++;
    }
}
