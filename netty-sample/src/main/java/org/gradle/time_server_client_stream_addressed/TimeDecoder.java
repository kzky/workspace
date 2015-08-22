package org.gradle.time_server_client_stream_addressed;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class TimeDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in,
            List<Object> out) throws Exception {

        if (in.readableBytes() < 4) {
            return;
        }

        out.add(in.readBytes(4));
    }

}
