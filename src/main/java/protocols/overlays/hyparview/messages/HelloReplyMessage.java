package protocols.overlays.hyparview.messages;

import pt.unl.fct.di.novasys.babel.generic.ProtoMessage;
import io.netty.buffer.ByteBuf;
import pt.unl.fct.di.novasys.network.ISerializer;

import java.net.UnknownHostException;

public class HelloReplyMessage extends ProtoMessage {
    public final static short MSG_CODE = 406;

    private final boolean reply;

    public HelloReplyMessage(boolean reply) {
        super(HelloReplyMessage.MSG_CODE);
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "HelloReplyMessage{" +
                "reply=" + reply +
                '}';
    }

    public boolean isTrue() {
        return reply;
    }

    public static final ISerializer<HelloReplyMessage> serializer = new ISerializer<HelloReplyMessage>() {
        @Override
        public void serialize(HelloReplyMessage m, ByteBuf out) {
            out.writeBoolean(m.reply);
        }

        @Override
        public HelloReplyMessage deserialize(ByteBuf in) {
            boolean reply = in.readBoolean();

            return new HelloReplyMessage(reply);
        }

    };
}
