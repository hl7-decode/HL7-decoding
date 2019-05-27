package com.hl7.get;

import com.hl7.manage.GetMessage;
import io.netty.buffer.ByteBuf;
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
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception{
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        System.out.println(req.length);
        String body = new String(req, "UTF-8");
//        String body = (String)msg;
        this.stringBuffer.append(body);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx){
        getMessage.setText(stringBuffer.toString());
//        System.out.println(stringBuffer.toString());
        ctx.flush();
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
    
}
