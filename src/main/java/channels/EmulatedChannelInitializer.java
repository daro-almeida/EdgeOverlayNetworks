package channels;

import pt.unl.fct.di.novasys.babel.initializers.ChannelInitializer;
import pt.unl.fct.di.novasys.babel.internal.BabelMessage;
import pt.unl.fct.di.novasys.channel.ChannelListener;
import pt.unl.fct.di.novasys.channel.emulation.EmulatedChannel;
import pt.unl.fct.di.novasys.network.ISerializer;

import java.io.IOException;
import java.util.Properties;

public class EmulatedChannelInitializer implements ChannelInitializer<EmulatedChannel<BabelMessage>> {

	@Override
	public EmulatedChannel<BabelMessage> initialize(ISerializer<BabelMessage> serializer, ChannelListener<BabelMessage> list, Properties properties, short protoId) throws IOException {
		return new EmulatedChannel<>(serializer, list, properties);
	}
}
