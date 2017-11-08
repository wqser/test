package javatest;

/**
 * Created by wqs on 2016/11/28.插入排顺序
 */
public class InsertSort {
    public static void main(String arg[]){
        int[] a={5,3,8,6,1,7,2,};
        for(int i = 1;i <a.length; i++){
            int j = -1;
            while(j <= i && a[i] > a[++j]);//找到element[i]应该摆放的位置，此处可以利用查找算法进行优化.用while循环找出在aa[i]前面的数组中比a[i]大的数的位置

            if(j < i){//如果相等的话就会从i=2开始
                //将j之后的数据移动一位，然后把a[i]移动到j处
                int temp = a[i];
                for(int k = i-1;k >= j;k--){
                    a[k+1] = a[k];
                }
                a[j] = temp;
            }
        }
      for(int b=0;b<a.length;b++){
      System.out.println(a[b]+" ");
    }

        }


    }


