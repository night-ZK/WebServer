package cn.zk.nio.core;

import java.io.IOException;
import java.nio.channels.Selector;

public class ClientHandler implements Runnable{
	private Selector selector;
	public ClientHandler() {
		try {
			this.selector = Selector.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Selector getSelector() {
		return this.selector;
	}
	public void run() {
		
	}
}
