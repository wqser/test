package javatest;

/**
 * Created by wqs on 2017/2/23.
 */
public class StringTest {
    public static void main(String[] args) {
        String aa = "SOF0003B00MLXGodeoed0123456789ff0010,001,20120809EOF";
        String serial=aa.substring(10,30);
        System.out.println("serial:"+serial);

        String message=aa.substring(32);
        System.out.println("信息："+message);
        String []test = message.split(",");
        for(String a:test){
            if(a.length()==4){
                System.out.print("故障类型："+a);
            }
            if(a.length()==3){
                System.out.print("故障代码："+a);
            }
            if(a.length()==11){
                String messagetime1=a.substring(0,8);
                String faultTime = messagetime1.substring(0,4) + "-" + messagetime1.substring(4,6) +"-"+ messagetime1.substring(6);
                System.out.print("日期类型："+faultTime);
            }
        }
    }

}
