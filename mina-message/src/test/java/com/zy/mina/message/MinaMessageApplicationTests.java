package com.zy.mina.message;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zy.mina.message.client.MinaClientHandler;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MinaMessageApplicationTests {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IoAcceptor ioAcceptor;
	
	@Test
	public void contextLoads() {
		this.logger.info("只是测试。。。。");
		client("嗨   服务器   我是客户端1");
		client("嗨   服务器   我是客户端2");
        Map<Long, IoSession> severSessionMap2 = this.ioAcceptor.getManagedSessions();
        for (Entry<Long, IoSession> entry : severSessionMap2.entrySet()) {
        	this.logger.info("服务端2管理的session:[{}]", entry.getKey());
        }
        this.ioAcceptor.dispose();
	}
	
	private void client(String msgStr){
		// 创建连接 
		SocketConnector connector = new NioSocketConnector();
		// 设置过滤器,选用Mina自带的过滤器一行一行读取代码 
		connector.getFilterChain().addLast("myChain", 
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		// 设置连接超时时间
		connector.setConnectTimeoutMillis(30 * 1000);
		// 绑定逻辑处理器
		connector.setHandler(new MinaClientHandler());
		// 连接到服务器
		String host = "127.0.0.1";
		int port = 9090;
		ConnectFuture future = connector.connect(new InetSocketAddress(host, port)); 
		// 等待连接创建完成  
        future.awaitUninterruptibly();
        // 获得session
        IoSession session = future.getSession();
        // 发送消息
        session.write(msgStr);
        
        // 等待连接断开
        session.getCloseFuture().awaitUninterruptibly();
        connector.dispose();
	}

}
