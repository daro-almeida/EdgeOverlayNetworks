package protocols.overlays.biasLayerTree.messages;

import pt.unl.fct.di.novasys.babel.generic.ProtoMessage;
import io.netty.buffer.ByteBuf;
import pt.unl.fct.di.novasys.network.ISerializer;
import protocols.overlays.biasLayerTree.utils.Node;

import java.io.IOException;

public class DisconnectMessage extends ProtoMessage {
    public static final short MSG_CODE = 407;

    private final Node node;

    public DisconnectMessage(Node node) {
        super(MSG_CODE);
        this.node = node;
    }

    @Override
    public String toString() {
        return "DisconnectMessage{" +
                "node=" + node +
                "}";
    }

    public Node getNode() {
        return node;
    }

    public static final ISerializer<DisconnectMessage> serializer = new ISerializer<DisconnectMessage>() {
        @Override
        public void serialize(DisconnectMessage disconnectMessage, ByteBuf out) throws IOException {
            Node.serializer.serialize(disconnectMessage.node, out);
        }

        @Override
        public DisconnectMessage deserialize(ByteBuf in) throws IOException {
            return new DisconnectMessage(Node.serializer.deserialize(in));
        }

    };
}
