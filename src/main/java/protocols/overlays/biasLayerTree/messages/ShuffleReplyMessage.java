package protocols.overlays.biasLayerTree.messages;

import pt.unl.fct.di.novasys.babel.generic.ProtoMessage;
import io.netty.buffer.ByteBuf;
import pt.unl.fct.di.novasys.network.ISerializer;
import protocols.overlays.biasLayerTree.utils.LayeredView;

import java.io.IOException;

public class ShuffleReplyMessage extends ProtoMessage {
    public static final short MSG_CODE = 410;

    private final LayeredView sample;

    public ShuffleReplyMessage(LayeredView sample) {
        super(MSG_CODE);
        this.sample = sample;
    }

    @Override
    public String toString() {
        return "ShuffleReplyMessage={" +
                "sample="+sample+
                "}";
    }

    public LayeredView getSample() {
        return sample;
    }

    public static final ISerializer<ShuffleReplyMessage> serializer = new ISerializer<ShuffleReplyMessage>() {
        @Override
        public void serialize(ShuffleReplyMessage shuffleMessage, ByteBuf out) throws IOException {
            LayeredView.serializer.serialize(shuffleMessage.sample, out);
        }

        @Override
        public ShuffleReplyMessage deserialize(ByteBuf in) throws IOException {
            LayeredView sample = LayeredView.serializer.deserialize(in);
            return new ShuffleReplyMessage(sample);
        }

    };
}
