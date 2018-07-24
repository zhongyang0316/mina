package com.zy.mina.message.client;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaClientHandler implements IoHandler {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	// 有异常
	@Override
	public void exceptionCaught(IoSession paramIoSession, Throwable paramThrowable) throws Exception {
		this.logger.info("客户端session连接ID:[{}],出现异常:{}", paramIoSession.getId(), paramThrowable);
	}

	@Override
	public void inputClosed(IoSession paramIoSession) throws Exception {
		paramIoSession.closeNow();
	}

	@Override
	public void messageReceived(IoSession paramIoSession, Object paramObject) throws Exception {
		String messageStr = paramObject.toString();
		this.logger.info("客户端session连接ID:[{}],收到消息:{}", paramIoSession.getId(), messageStr);
	}

	@Override
	public void messageSent(IoSession paramIoSession, Object paramObject) throws Exception {
		String messageStr = paramObject.toString();
		this.logger.info("客户端session连接ID:[{}],发送消息:{}", paramIoSession.getId(), messageStr);
	}

	@Override
	public void sessionClosed(IoSession paramIoSession) throws Exception {
		this.logger.info("客户端关闭session连接ID:[{}]", paramIoSession.getId());
	}

	@Override
	public void sessionCreated(IoSession paramIoSession) throws Exception {
		this.logger.info("客户端创建session连接ID:[{}]", paramIoSession.getId());
	}

	@Override
	public void sessionIdle(IoSession paramIoSession, IdleStatus paramIdleStatus) throws Exception {
		this.logger.info("客户端session连接,ID:[{}],状态:{}", paramIoSession.getId(), paramIdleStatus);
	}

	@Override
	public void sessionOpened(IoSession paramIoSession) throws Exception {
		this.logger.info("客户端打开session连接!,ID:[{}]", paramIoSession.getId());
	}

}
