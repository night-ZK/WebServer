package cn.zk.nio.core;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {
	private ServerSocketChannel ssc;
	private ExecutorService threadPool;
	private ClientHandler handler;
	public WebServer() {
		try {
			handler = new ClientHandler();
			// 开启一个server channel来监听 	学校开门	新建NIO通道
			this.ssc = ServerSocketChannel.open();
			//设置通道为NIO模式 	非阻塞模式
			ssc.configureBlocking(false);
			//将通道对应的ServerSocket绑定到端口号	找老师
			ServerSocket ss = ssc.socket();
			ss.bind(new InetSocketAddress("localhost", 9999));
			//注册监听事件,只监听ACCEPT事件		监听新生报到
			ssc.register(handler.getSelector(), SelectionKey.OP_ACCEPT);
			this.threadPool = Executors.newFixedThreadPool(6);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void work() {
		threadPool.execute(handler);
	}
	public static void main(String[] args) {
		WebServer ws = new WebServer();
		ws.work();
	}
}
