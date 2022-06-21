package protocols.overlays.xbot.messages;

import pt.unl.fct.di.novasys.babel.generic.ProtoMessage;
import io.netty.buffer.ByteBuf;
import pt.unl.fct.di.novasys.network.ISerializer;

import java.io.IOException;

public class DisconnectWaitMessage extends ProtoMessage {
    public static final short MSG_ID = 416;


    public DisconnectWaitMessage() {
        super(MSG_ID);
    }

    public static final ISerializer<DisconnectWaitMessage> serializer = new ISerializer<DisconnectWaitMessage>() {
        @Override
        public void serialize(DisconnectWaitMessage optimizationMessage, ByteBuf out) {
        }

        @Override
        public DisconnectWaitMessage deserialize(ByteBuf in) {
            return new DisconnectWaitMessage();
        }
    };
}
