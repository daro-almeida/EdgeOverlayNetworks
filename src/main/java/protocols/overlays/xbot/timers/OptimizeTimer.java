package protocols.overlays.xbot.timers;

import pt.unl.fct.di.novasys.babel.generic.ProtoTimer;

public class OptimizeTimer extends ProtoTimer {

    public static final short TIMER_ID = 410;

    public OptimizeTimer() {
        super(TIMER_ID);
    }

    @Override
    public ProtoTimer clone() {
        return this;
    }
}
