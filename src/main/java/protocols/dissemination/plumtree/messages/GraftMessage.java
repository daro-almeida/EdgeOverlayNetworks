package protocols.dissemination.plumtree.messages;

import io.netty.buffer.ByteBuf;
import pt.unl.fct.di.novasys.babel.generic.ProtoMessage;
import pt.unl.fct.di.novasys.network.ISerializer;

import java.io.IOException;

public class GraftMessage extends ProtoMessage {

    public static final short MSG_ID = 303;

    private final int mid;
    private final int round;

    @Override
    public String toString() {
        return "GraftMessage{" +
                "mid=" + mid +
                ", round=" + round +
                '}';
    }

    public GraftMessage(int mid, int round) {
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

    public static final ISerializer<GraftMessage> serializer = new ISerializer<GraftMessage>() {
        @Override
        public void serialize(GraftMessage graftMessage, ByteBuf out) {
            out.writeInt(graftMessage.mid);
            out.writeInt(graftMessage.round);
        }

        @Override
        public GraftMessage deserialize(ByteBuf in) {
            return new GraftMessage(in.readInt(), in.readInt());
        }
    };
}
