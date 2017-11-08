package javatest;

/**
 * Created by wqs on 2016/10/18.
 */
public class Kuaisupaixu {
public static void main(String arg[]){
    int a[]={8,12,30,21,20,80,15};
    int l=0;
    int r=a.length-1;
    quickSort(a,l,r);

    outp(a);
};
static void quickSort(int a[],int l,int r) {
    int tem;
    if (l < r) {
            tem = _quickSort(a, l, r);
            quickSort(a, l, tem - 1);
            quickSort(a, tem + 1, r);
        }
    }
static void outp(int arr[]){
    for(int i=0;i<arr.length;i++){
        System.out.print(arr[i]+",");
    };
}
static int _quickSort(int []s,int i,int j ){
    int x=s[i];
    while (i < j)
    {
        while(i < j && s[j] >= x){
            j--;}
        if(i < j)
        {
            s[i] = s[j];
            i++;
        }
        while(i < j && s[i] <= x){
            i++;}
        if(i < j)
        {
            s[j] = s[i];
             j--;
        }
    }

    s[i] = x;

    return i;
};

}

