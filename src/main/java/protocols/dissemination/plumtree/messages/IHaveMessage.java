package protocols.dissemination.plumtree.messages;

import io.netty.buffer.ByteBuf;
import pt.unl.fct.di.novasys.babel.generic.ProtoMessage;
import pt.unl.fct.di.novasys.network.ISerializer;

import java.io.IOException;

public class IHaveMessage extends ProtoMessage {

    public static final short MSG_ID = 304;

    private final int mid;
    private final int round;

    @Override
    public String toString() {
        return "IHaveMessage{" +
                "mid=" + mid +
                ", round=" + round +
                '}';
    }

    public IHaveMessage(int mid, int round) {
        super(MSG_ID);
        this.mid = mid;
        this.round = round;
    }

    public int getMid() {
        return mid;
    }

    public int getRound() {
        return round;
    }

    public static final ISerializer<IHaveMessage> serializer = new ISerializer<IHaveMessage>() {
        @Override
        public void serialize(IHaveMessage iHaveMessage, ByteBuf out) {
            out.writeInt(iHaveMessage.mid);
            out.writeInt(iHaveMessage.round);
        }

        @Override
        public IHaveMessage deserialize(ByteBuf in) {
            return new IHaveMessage(in.readInt(), in.readInt());
        }
    };
}
