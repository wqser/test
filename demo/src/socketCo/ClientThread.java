package socketCo;

/**
 * Created by wqs on 2017/3/9.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    ClientUI ui;
    Socket client;
    BufferedReader reader;
    PrintWriter writer;

    public ClientThread(ClientUI ui) {
        this.ui = ui;
        try {
            client = new Socket("127.0.0.1", 1228);//这里设置连接服务器端的IP的端口
            println("连接服务器成功：端口1228");
            reader = new BufferedReader(new InputStreamReader(
                    client.getInputStream()));
            writer = new PrintWriter(client.getOutputStream(), true);
            // 如果为 true，则 println、printf 或 format 方法将刷新输出缓冲区
        } catch (IOException e) {
            println("连接服务器失败：端口1228");
            println(e.toString());
            e.printStackTrace();
        }
        this.start();
    }

    public void run() {
        String msg = "";
        while (true) {
            try {
                msg = reader.readLine();
            } catch (IOException e) {
                println("服务器断开连接");

                break;
            }
            if (msg != null && msg.trim() != "") {
                println("服务器：" + msg);
            }
        }
    }

    public void sendMsg(String msg) {
        try {
            writer.println(msg);
            println("客户："+msg);
        } catch (Exception e) {
            println(e.toString());
        }
    }

    public void println(String s) {
        if (s != null) {
            this.ui.taShow.setText(this.ui.taShow.getText() + s + "\n");
            System.out.println(s + "\n");
        }
    }

}
