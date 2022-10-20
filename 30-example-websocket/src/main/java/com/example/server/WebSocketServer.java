package com.example.server;

import com.example.common.OnLineCount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author wanghanchao
 */
@ServerEndpoint("/websocket/{uid}")
@Component
@Slf4j
public class WebSocketServer {

    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    private Session session;
    private String uid = "";

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {

        log.info("收到来自窗口[" + uid + "]的信息:" + message);
        //群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, @PathParam("uid") String uid)
            throws IOException {
        http:
        log.info("推送消息到窗口[" + uid + "]，推送内容:" + message);
        for (WebSocketServer item : webSocketSet) {
            try {
                if (uid == null) {
                    item.sendMessage(message);
                } else if (item.uid.equals(uid)) {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("uid") String uid) {
        this.session = session;
        webSocketSet.add(this);
        OnLineCount.addOnlineCount();
        log.info("有新窗口开始监听:[" + uid + "],当前在线人数为" + OnLineCount.getOnlineCount());
        this.uid = uid;
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        OnLineCount.subOnlineCount();
        log.info("有一连接关闭！当前在线人数为" + OnLineCount.getOnlineCount());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

}
