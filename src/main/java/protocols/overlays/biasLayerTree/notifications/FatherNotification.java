package protocols.overlays.biasLayerTree.notifications;

import pt.unl.fct.di.novasys.babel.generic.ProtoNotification;
import pt.unl.fct.di.novasys.network.data.Host;

public class FatherNotification extends ProtoNotification {

    public static final short NOTIFICATION_ID = 401;
    public static final String NOTIFICATION_NAME = "Father Change";

    private final Host oldFather;
    private final Host newFather;

    private final short oldFatherLayer;
    private final short newFatherLayer;

    public FatherNotification(Host oldFather, short oldFatherLayer, Host newFather, short newFatherLayer) {
        super(NOTIFICATION_ID);
        this.oldFather = oldFather;
        this.newFather = newFather;
        this.oldFatherLayer = oldFatherLayer;
        this.newFatherLayer = newFatherLayer;
    }

    @Override
    public String toString() {
        return "FatherNotification{" +
                "oldFather=" + oldFather +
                ", newFather=" + newFather +
                ", oldFatherLayer=" + oldFatherLayer +
                ", newFatherLayer=" + newFatherLayer +
                '}';
    }

    public Host getOldFather() {
        return oldFather;
    }

    public Host getNewFather() {
        return newFather;
    }

    public short getNewFatherLayer() {
        return newFatherLayer;
    }

    public short getOldFatherLayer() {
        return oldFatherLayer;
    }
}
