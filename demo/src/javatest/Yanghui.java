package javatest;

/**
 * Created by wqs on 2016/10/18.
 */
public class Yanghui {
    public static void main(String[] args){
        int i=1;
        int yh[] = new int[8];
        for(i=0;i<8;i++) {
            yh[i]=1;  //yh[0] 初始值设为1

            for (int j=i-1;j>0;j--){  //循环赋值
                yh[j]= yh[j-1]+yh[j];
            }
            for (int j=0;j<=i;j++) {
                System.out.print(yh[j]+"\t");
            }
            System.out.println();

        }
    }
}


