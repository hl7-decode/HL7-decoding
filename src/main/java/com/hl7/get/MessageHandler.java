package com.hl7.get;

import com.hl7.manage.GetMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MessageHandler extends ChannelInboundHandlerAdapter{

    private StringBuffer stringBuffer = new StringBuffer();


    public MessageHandler(GetMessage getMessage){
        this.getMessage = getMessage;
    }

    private GetMessage getMessage;

    public void setGetMessage(GetMessage getMessage) {
        this.getMessage = getMessage;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        if(msg instanceof  String)
            stringBuffer.append((String) msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx){
        getMessage.setText(stringBuffer.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
    
}
