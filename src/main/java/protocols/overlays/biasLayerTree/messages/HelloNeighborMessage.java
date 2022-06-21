package protocols.overlays.biasLayerTree.messages;


import pt.unl.fct.di.novasys.babel.generic.ProtoMessage;
import io.netty.buffer.ByteBuf;
import pt.unl.fct.di.novasys.network.ISerializer;
import protocols.overlays.biasLayerTree.utils.Node;

import java.io.IOException;


public class HelloNeighborMessage extends ProtoMessage {

    public static final short MSG_CODE = 404;

    private final Node neighbour;

    public HelloNeighborMessage(Node neighbour) {
        super(MSG_CODE);
        this.neighbour = neighbour;
    }

    @Override
    public String toString() {
        return "HelloNeighborMessage{" +
                "neigh=" + neighbour +
                "}";
    }

    public Node getNeighbour() {
        return neighbour;
    }

    public static final ISerializer<HelloNeighborMessage> serializer = new ISerializer<HelloNeighborMessage>() {

        @Override
        public void serialize(HelloNeighborMessage m, ByteBuf out) throws IOException {
            Node.serializer.serialize(m.neighbour, out);
        }

        @Override
        public HelloNeighborMessage deserialize(ByteBuf in) throws IOException {
            return new HelloNeighborMessage(Node.serializer.deserialize(in));
        }

    };

}
