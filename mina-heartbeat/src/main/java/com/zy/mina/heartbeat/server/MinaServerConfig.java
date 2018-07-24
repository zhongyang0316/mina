package com.zy.mina.heartbeat.server;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinaServerConfig {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final int IDELTIMEOUT = 30;
	
	@Bean(destroyMethod = "unbind")
	public IoAcceptor ioAcceptor(){
		IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,
                IDELTIMEOUT);
        
		return null;
	}

}
