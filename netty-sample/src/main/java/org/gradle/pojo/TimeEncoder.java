package org.gradle.pojo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

//public class TimeEncoder extends ChannelOutboundHandlerAdapter {
//    @Override
//    public void write(ChannelHandlerContext ctx, Object msg,
//            ChannelPromise promise) throws Exception {
//        UnixTime utime = (UnixTime) msg;
//        ByteBuf encoded = ctx.alloc().buffer(4);
//        encoded.writeInt((int) utime.value());
//        ctx.write(encoded, promise);
//    }
// }

public class TimeEncoder extends MessageToByteEncoder<UnixTime> {

    @Override
    protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out)
            throws Exception {
        System.out.println("TimeEncoder called");
        out.writeInt((int) msg.value());
    }

}