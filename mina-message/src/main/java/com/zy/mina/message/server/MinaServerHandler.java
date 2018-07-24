package com.zy.mina.message.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaServerHandler extends IoHandlerAdapter {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	//创建连接
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		this.logger.info("服务器创建session连接!,ID:[{}]", session.getId());
		super.sessionCreated(session);
	}
	
	//打开一个连接
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		this.logger.info("服务器打开session连接!,ID:[{}]", session.getId());
		super.sessionOpened(session);
	}
	
	//关闭连接
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		this.logger.info("服务器关闭session连接!,ID:[{}]", session.getId());
		super.sessionClosed(session);
	}
	
	//连接空闲
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		this.logger.info("服务器session连接,ID:[{}],状态:{}", session.getId(), status);
		super.sessionIdle(session, status);
	}
	
	//有异常时
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		this.logger.info("服务器session连接,ID:[{}],有异常:{}", session.getId(), cause);
		super.exceptionCaught(session, cause);
	}
	
	//接收到消息
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		String messageStr = message.toString();
		this.logger.info("服务器接收到消息,session连接ID:[{}],消息内容:{}", session.getId(), messageStr);
		super.messageReceived(session, message);
	}
	
	//将要发送消息
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		String messageStr = message.toString();
		this.logger.info("服务器发送消息,session连接ID:[{}],消息内容:{}", session.getId(), messageStr);
		super.messageSent(session, message);
	}

}
