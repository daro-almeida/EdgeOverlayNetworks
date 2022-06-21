package protocols.overlays.xbot.messages;

import pt.unl.fct.di.novasys.babel.generic.ProtoMessage;
import io.netty.buffer.ByteBuf;
import pt.unl.fct.di.novasys.network.ISerializer;
import pt.unl.fct.di.novasys.network.data.Host;

import java.io.IOException;

public class SwitchMessage extends ProtoMessage {
    public static final short MSG_ID = 414;

    private final Host peer;
    private final Host candidate;

    @Override
    public String toString() {
        return "SwitchMessage{" +
                "peer=" + peer +
                ", candidate=" + candidate +
                '}';
    }

    public SwitchMessage(Host peer, Host candidate) {
        super(MSG_ID);
        this.peer = peer;
        this.candidate = candidate;
    }

    public Host getPeer() {
        return peer;
    }

    public Host getCandidate() {
        return candidate;
    }

    public static final ISerializer<SwitchMessage> serializer = new ISerializer<SwitchMessage>() {
        @Override
        public void serialize(SwitchMessage optimizationMessage, ByteBuf out) throws IOException {
            Host.serializer.serialize(optimizationMessage.peer, out);
            Host.serializer.serialize(optimizationMessage.candidate, out);
        }

        @Override
        public SwitchMessage deserialize(ByteBuf in) throws IOException {
            return new SwitchMessage(Host.serializer.deserialize(in),
                    Host.serializer.deserialize(in));
        }
    };
}
