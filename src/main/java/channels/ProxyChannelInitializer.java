package channels;

import pt.unl.fct.di.novasys.babel.initializers.ChannelInitializer;
import pt.unl.fct.di.novasys.babel.internal.BabelMessage;
import pt.unl.fct.di.novasys.channel.ChannelListener;
import pt.unl.fct.di.novasys.channel.proxy.ProxyChannel;
import pt.unl.fct.di.novasys.network.ISerializer;

import java.io.IOException;
import java.util.Properties;

public class ProxyChannelInitializer implements ChannelInitializer<ProxyChannel<BabelMessage>> {

	@Override
	public ProxyChannel<BabelMessage> initialize(ISerializer<BabelMessage> serializer, ChannelListener<BabelMessage> list, Properties properties, short protoId) throws IOException {
		return new ProxyChannel<>(serializer, list, properties);
	}
}
