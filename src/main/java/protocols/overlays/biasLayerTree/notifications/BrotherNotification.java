package protocols.overlays.biasLayerTree.notifications;


import pt.unl.fct.di.novasys.babel.generic.ProtoNotification;
import pt.unl.fct.di.novasys.network.data.Host;

public class BrotherNotification extends ProtoNotification {

    public static final short NOTIFICATION_ID = 404;
    public static final String NOTIFICATION_NAME = "Brother Change";

    private final Host oldBrother;
    private final Host newBrother;

    public BrotherNotification(Host oldBrother, Host newBrother) {
        super(NOTIFICATION_ID);
        this.newBrother = newBrother;
        this.oldBrother = oldBrother;
    }

    public Host getNewBrother() {
        return newBrother;
    }

    public Host getOldBrother() {
        return oldBrother;
    }
}
