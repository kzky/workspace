package org.gradle.echo_server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

/**
 * Handles a server-side channel.
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter { // (1)
    Logger logger = Logger.getLogger(EchoServerHandler.class.getName());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)

        ByteBuf in = (ByteBuf) msg;
        try {
            System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));

            ctx.write(msg);
            ctx.flush(); // here, the reference-counted object released.

            // instead, you can write ctx.writeAndFlush(msg)

        } finally {
            // it is permitted to release a reference-counted object here,
            // instead of ctx.flush()
            // ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
