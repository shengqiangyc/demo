package com.volunteer.demo.chat;

import com.volunteer.demo.bio.Client;
import com.volunteer.demo.bio.Server;

/**
 * @author shengqiang
 * @date 2020-08-12 15:55
 */
public class ChatTest {

    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        Thread thread = new Thread(server);
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
