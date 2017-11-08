package javatest;

/**
 * Created by wqs on 2016/10/20.
 */
public class Movenum {
    public static void main(String []arg){
        int a[]={1,2,3,4,5};
        int n=a.length;
        int m=2;

        moveM(a,m);
        outp(a);
    }

    static void outp(int arr[]){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+",");
        };
    }

     static void move(int []a){
            int temp=a[a.length-1];
            for(int j=a.length-1;j>0;j--){
                a[j]=a[j-1];
            }
            a[0]=temp;
        }
    static void moveM(int a[],int m){
         for(int i=0;i<m;i++) {
             move(a);
         }
        }

}

