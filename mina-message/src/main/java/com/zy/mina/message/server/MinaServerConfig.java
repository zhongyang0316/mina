package com.zy.mina.message.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinaServerConfig {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Bean(destroyMethod = "unbind")
	public IoAcceptor ioAcceptor(){
		// 创建一个非阻塞的server端的socket
		IoAcceptor acceptor = new NioSocketAcceptor();
		// 设置过滤器,选用Mina自带的过滤器一行一行读取代码 
		acceptor.getFilterChain().addLast("myChain", 
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8")))); 
		// 设置读取数据的缓冲区大小
		acceptor.getSessionConfig().setReadBufferSize(2048);
		// 读写通道10秒内无操作进入空闲状态
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);
		// 绑定逻辑处理器
		acceptor.setHandler(new MinaServerHandler());
		// 绑定端口,启动服务器
		int port = 9090;
		try {  
            acceptor.bind(new InetSocketAddress(port));  
        } catch (IOException e) {  
            this.logger.error("MinaServer start error:{}", e);  
        }
		this.logger.info("MinaServer服务已启动，端口是:{}", port); 
		return acceptor;
	}

}
