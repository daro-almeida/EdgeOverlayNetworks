package protocols.overlays.tman.timers;

import pt.unl.fct.di.novasys.babel.generic.ProtoTimer;

public class GetPeersTimer extends ProtoTimer {

    public static final short TIMER_ID = 421;

    public GetPeersTimer() {
        super(TIMER_ID);
    }

    @Override
    public ProtoTimer clone() {
        return this;
    }
}
