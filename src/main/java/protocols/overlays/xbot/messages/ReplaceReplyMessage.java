package protocols.overlays.xbot.messages;

import pt.unl.fct.di.novasys.babel.generic.ProtoMessage;
import io.netty.buffer.ByteBuf;
import pt.unl.fct.di.novasys.network.ISerializer;
import pt.unl.fct.di.novasys.network.data.Host;

import java.io.IOException;

public class ReplaceReplyMessage extends ProtoMessage {
    public static final short MSG_ID = 413;

    private final Host old;
    private final Host peer;
    private final boolean answer;

    @Override
    public String toString() {
        return "ReplaceReplyMessage{" +
                "old=" + old +
                ", peer=" + peer +
                ", answer=" + answer +
                '}';
    }

    public ReplaceReplyMessage(Host old, Host peer, boolean answer) {
        super(MSG_ID);
        this.old = old;
        this.peer = peer;
        this.answer = answer;
    }

    public Host getOld() {
        return old;
    }

    public Host getPeer() {
        return peer;
    }

    public boolean getAnswer() {
        return answer;
    }

    public static final ISerializer<ReplaceReplyMessage> serializer = new ISerializer<ReplaceReplyMessage>() {
        @Override
        public void serialize(ReplaceReplyMessage optimizationMessage, ByteBuf out) throws IOException {
            Host.serializer.serialize(optimizationMessage.old, out);
            Host.serializer.serialize(optimizationMessage.peer, out);
            out.writeBoolean(optimizationMessage.answer);
        }

        @Override
        public ReplaceReplyMessage deserialize(ByteBuf in) throws IOException {
            return new ReplaceReplyMessage(Host.serializer.deserialize(in),
                    Host.serializer.deserialize(in),
                    in.readBoolean());
        }
    };
}
