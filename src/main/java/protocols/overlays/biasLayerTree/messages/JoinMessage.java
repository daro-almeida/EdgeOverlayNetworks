package protocols.overlays.biasLayerTree.messages;

import pt.unl.fct.di.novasys.babel.generic.ProtoMessage;
import io.netty.buffer.ByteBuf;
import pt.unl.fct.di.novasys.network.ISerializer;
import protocols.overlays.biasLayerTree.utils.Node;

import java.io.IOException;


public class JoinMessage extends ProtoMessage {

    public final static short MSG_CODE = 401;

    private final Node node;

    public JoinMessage(Node node) {
        super(JoinMessage.MSG_CODE);
        this.node = node;
    }

    @Override
    public String toString() {
        return "JoinMessage{" +
                "node=" + node +
                "}";
    }

    public Node getNode() {
        return node;
    }

    public static final ISerializer<JoinMessage> serializer = new ISerializer<JoinMessage>() {
        @Override
        public void serialize(JoinMessage joinMessage, ByteBuf byteBuf) throws IOException {
            Node.serializer.serialize(joinMessage.node, byteBuf);
        }

        @Override
        public JoinMessage deserialize(ByteBuf byteBuf) throws IOException {
            return new JoinMessage(Node.serializer.deserialize(byteBuf));
        }

    };

}
