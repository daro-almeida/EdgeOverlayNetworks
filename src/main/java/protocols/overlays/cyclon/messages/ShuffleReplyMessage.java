package protocols.overlays.cyclon.messages;

import pt.unl.fct.di.novasys.babel.generic.ProtoMessage;
import io.netty.buffer.ByteBuf;
import pt.unl.fct.di.novasys.network.ISerializer;
import pt.unl.fct.di.novasys.network.data.Host;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShuffleReplyMessage extends ProtoMessage {
    public final static short MSG_ID = 421;
    private final Map<Host, Integer> subset;

    public ShuffleReplyMessage(Map<Host, Integer> subset) {
        super(MSG_ID);
        this.subset = subset;
    }

    public Map<Host, Integer> getSubset() {
        return subset;
    }

    @Override
    public String toString() {
        return "ShuffleReplyMessage{" +
                "subset=" + subset +
                '}';
    }

    public static final ISerializer<ShuffleReplyMessage> serializer = new ISerializer<ShuffleReplyMessage>() {
        @Override
        public void serialize(ShuffleReplyMessage shuffleMessage, ByteBuf out) {
            out.writeInt(shuffleMessage.subset.size());
            shuffleMessage.subset.forEach((h,a)-> {
                try {
                    Host.serializer.serialize(h, out);
                    out.writeInt(a);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        @Override
        public ShuffleReplyMessage deserialize(ByteBuf in) throws IOException {
            int size = in.readInt();
            Map<Host, Integer> subset = new HashMap<>(size, 1);
            for(int i = 0; i < size; i ++) {
                Host h = Host.serializer.deserialize(in);
                int age = in.readInt();
                subset.put(h, age);
            }
            return new ShuffleReplyMessage(subset);
        }
    };
}
