package com.phh.test.netty;

/**
 * <p> TODO
 *
 * @author phh
 * @version V1.0
 * @project: spring
 * @package com.phh.test.netty
 * @date 2019/4/23
 */
public class ServerClientTest {

    public static void main(String[] args) throws InterruptedException {

        //启动服务端
        Server server = new Server();
        server.start();

        //启动客户端
        //客户端连接channelActive，会发送一条消息到服务端
        Client client = new Client();
        client.start();
    }

}
