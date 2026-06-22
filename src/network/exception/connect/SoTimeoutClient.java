package network.exception.connect;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SoTimeoutClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        InputStream input = socket.getInputStream();
        try {
            socket.setSoTimeout(3000); // 타임아웃 시간 설정
            int read = input.read(); // 기본은 무한대기
        } catch (Exception e) {
            e.printStackTrace();
        }
        socket.close();
        // 외부 서버와 통신을 하는 경우 반드시 연결 타임아웃과 소켓 타임아웃을 지정하자.
    }
}
