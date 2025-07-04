package app.netlify.codelittlestation.codelittlestation.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// 好吧这个类我只写了10%左右，全丢给deepseek了 OvO
public class nt {
    private nt(){}
    public static String send(String host, int port, String message, boolean receiveResponse) throws IOException {
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            out.println(message);
            log.INFOLog("成功向"+host+":"+port+"发送请求："+message);
            if (receiveResponse) {
                String response = in.readLine();
                log.INFOLog("接收到"+host+"发来的信息"+response);
                return response;
            }
            return null;
        }
    }
    public static String get(int port) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket clientSocket = serverSocket.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            // 接收并返回消息
            return in.readLine();
        }
    }
}
