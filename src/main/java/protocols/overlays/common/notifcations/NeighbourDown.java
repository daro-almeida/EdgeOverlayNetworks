package protocols.overlays.common.notifcations;


import pt.unl.fct.di.novasys.babel.generic.ProtoNotification;
import pt.unl.fct.di.novasys.network.data.Host;

public class NeighbourDown extends ProtoNotification {

    public static final short NOTIFICATION_ID = 22;

    private final Host neighbour;

    public NeighbourDown(Host neighbour) {
        super(NOTIFICATION_ID);
        this.neighbour = neighbour;
    }

    public Host getNeighbour() {
        return neighbour;
    }
}
