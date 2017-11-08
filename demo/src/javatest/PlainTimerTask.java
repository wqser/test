package javatest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created by wqs on 2017/6/26.
 */
public class PlainTimerTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("当前时间是"+new SimpleDateFormat().format(new Date()));
    }
}
