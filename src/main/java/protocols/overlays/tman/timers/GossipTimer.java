package protocols.overlays.tman.timers;

import pt.unl.fct.di.novasys.babel.generic.ProtoTimer;

public class GossipTimer extends ProtoTimer {

    public static final short TIMER_ID = 430;

    public GossipTimer() {
        super(TIMER_ID);
    }

    @Override
    public ProtoTimer clone() {
        return this;
    }
}
