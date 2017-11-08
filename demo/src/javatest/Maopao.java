package javatest;

/**
 * Created by wqs on 2016/10/19.
 */
public class Maopao {
    public static void main(String arg[]){
        int a[]={4,12,6,5,20,7,15};
        paiXu(a);
        outp(a);

    }

   static  void paiXu(int a[]){
       for(int i=0;i<a.length;i++){
           for(int j=0;j<a.length-1;j++){
               if(a[j]>a[j+1]){
                   int tem=a[j];
                   a[j]=a[j+1];
                   a[j+1]=tem;
               }
           }
       }
   }
    static void outp(int arr[]){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+",");
        };
    }
}
