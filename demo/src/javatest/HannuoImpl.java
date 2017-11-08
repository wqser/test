package javatest;

import java.util.Stack;

/**
 * Created by wqs on 2016/10/31.
 */
public class HannuoImpl {
    public static void main(String arg[]){
        int n=6;
        Stack<HannuoStack> shs=new Stack<HannuoStack>();
        HannuoStack hs=new HannuoStack(n,'a','b','c');
        shs.push(hs);
        while(!shs.empty()){
            hs=shs.peek();
            shs.pop();
            if(hs.n==1){
                System.out.println(hs.from+"->"+hs.to);
            }else{
                shs.push(new HannuoStack(hs.n-1, hs.mid, hs.from, hs.to));
                shs.push(new HannuoStack(1,hs.from,hs.mid,hs.to));
                shs.push(new HannuoStack(hs.n-1,hs.from,hs.to,hs.mid));

            }
        }
    }
}
