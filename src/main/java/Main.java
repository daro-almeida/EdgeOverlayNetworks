import channels.ProxyChannelInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import protocols.dissemination.flood.FloodGossip;
import protocols.dissemination.plumtree.PlumTree;
import protocols.overlays.OverlayProtocol;
import protocols.overlays.biasLayerTree.BiasLayeredTree;
import protocols.overlays.cyclon.Cyclon;
import protocols.overlays.hyparview.HyparView;
import protocols.overlays.tmanWithCyclon.TmanWithCyclon;
import protocols.overlays.xbot.Xbot;
import protocols.tester.CyclonTester;
import protocols.tester.DisseminationConsumer;
import protocols.tester.OverlayConsumer;
import protocols.tester.TmanTester;
import pt.unl.fct.di.novasys.babel.core.Babel;
import pt.unl.fct.di.novasys.channel.proxy.ProxyChannel;
import pt.unl.fct.di.novasys.network.data.Host;
import utils.Translate;

import java.net.InetAddress;
import java.util.Properties;

public class Main {

    static {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
    }

    private static final String DEFAULT_CONF = "config/network_config.properties";
    public static final String PROTO_CHANNELS = ProxyChannel.NAME;

    private static final Logger logger = LogManager.getLogger(Main.class);


    public static void main(String[] args) throws Exception {

        Babel babel = Babel.getInstance();
        Properties props = Babel.loadConfig(args, DEFAULT_CONF);
        props.setProperty("nThreads", "4");

		props.setProperty(ProxyChannel.ADDRESS_KEY, props.getProperty(ProxyChannel.ADDRESS_KEY)); //The address to bind to
		props.setProperty(ProxyChannel.PORT_KEY, props.getProperty(ProxyChannel.PORT_KEY)); //The port to bind to
		props.setProperty(ProxyChannel.RELAY_ADDRESS_KEY, props.getProperty(ProxyChannel.RELAY_ADDRESS_KEY));
		props.setProperty(ProxyChannel.RELAY_PORT, props.getProperty(ProxyChannel.RELAY_PORT));
		
		String overlay = props.getProperty("overlay");
		String dissemination = props.getProperty("dissemination");

        //babel.registerChannelInitializer(PROTO_CHANNELS, new MultiChannelInitializer());
        babel.registerChannelInitializer(PROTO_CHANNELS, new ProxyChannelInitializer());
        Host myself =  new Host(InetAddress.getByName(props.getProperty("address")),
                Integer.parseInt(props.getProperty("port")));

        logger.info("Hello, I am {}", myself);

        logger.info("Loading overlay {}", overlay);
		OverlayProtocol overlayProtocol = null;
		switch (overlay) {
			case HyparView.PROTOCOL_NAME:
				Translate.addId(HyparView.PROTOCOL_ID, HyparView.PROTOCOL_NAME);
				HyparView hyparView = new HyparView(PROTO_CHANNELS, props, myself);
				overlayProtocol = hyparView;
				babel.registerProtocol(hyparView);
				hyparView.init(props);
				break;
			case Xbot.PROTOCOL_NAME:
				Translate.addId(Xbot.PROTOCOL_ID, Xbot.PROTOCOL_NAME);
				Xbot xbot = new Xbot(PROTO_CHANNELS, props, myself);
				overlayProtocol = xbot;
				babel.registerProtocol(xbot);
				xbot.init(props);
				break;
			case BiasLayeredTree.PROTOCOL_NAME:
				Translate.addId(BiasLayeredTree.PROTOCOL_ID, BiasLayeredTree.PROTOCOL_NAME);
				BiasLayeredTree calm = new BiasLayeredTree(PROTO_CHANNELS, props, myself);
				overlayProtocol = calm;
				babel.registerProtocol(calm);
				calm.init(props);
				break;
			case TmanWithCyclon.PROTOCOL_NAME:
				Translate.addId(TmanWithCyclon.PROTOCOL_ID, TmanWithCyclon.PROTOCOL_NAME);
				TmanWithCyclon tman = new TmanWithCyclon(PROTO_CHANNELS, props, myself);
				overlayProtocol = tman;
				babel.registerProtocol(tman);
				tman.init(props);

				TmanTester tmanTester = new TmanTester();
				babel.registerProtocol(tmanTester);
				tmanTester.init(props);
				break;
			case Cyclon.PROTOCOL_NAME:
				Translate.addId(Cyclon.PROTOCOL_ID, Cyclon.PROTOCOL_NAME);
				Cyclon cyclon = new Cyclon(PROTO_CHANNELS, props, myself);
				overlayProtocol = cyclon;
				babel.registerProtocol(cyclon);
				cyclon.init(props);

//				CyclonTester cyclonTester = new CyclonTester();
//				babel.registerProtocol(cyclonTester);
//				cyclonTester.init(props);
				break;
			default:
				logger.error("Overlay {} is invalid", overlay);
		}

        OverlayConsumer overlayConsumer = new OverlayConsumer(props);
        babel.registerProtocol(overlayConsumer);
        overlayConsumer.init(props);

        logger.info("Loading dissemination {}", dissemination);
		switch (dissemination) {
			case PlumTree.PROTOCOL_NAME:
				Translate.addId(PlumTree.PROTOCOL_ID, PlumTree.PROTOCOL_NAME);
				assert overlayProtocol != null;
				PlumTree plumTree = new PlumTree(overlayProtocol.getChannelId(), props, myself);
				babel.registerProtocol(plumTree);
				plumTree.init(props);
				props.setProperty("disseminationProto", String.valueOf(PlumTree.PROTOCOL_ID));
				break;
			case FloodGossip.PROTO_NAME:
				Translate.addId(FloodGossip.PROTO_ID, FloodGossip.PROTO_NAME);
				assert overlayProtocol != null;
				FloodGossip floodGossip = new FloodGossip(overlayProtocol.getChannelId(), myself);
				babel.registerProtocol(floodGossip);
				floodGossip.init(props);
				props.setProperty("disseminationProto", String.valueOf(FloodGossip.PROTO_ID));
				break;
			default:
				logger.error("Dissemination {} is invalid", overlay);
		}

        DisseminationConsumer disseminationConsumer = new DisseminationConsumer(myself, props);
        babel.registerProtocol(disseminationConsumer);
        disseminationConsumer.init(props);

        babel.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> logger.info("Goodbye")));
    }
}
