package protocols.overlays.biasLayerTree.timers;

import pt.unl.fct.di.novasys.babel.generic.ProtoTimer;

public class FillViewTimer extends ProtoTimer {

    public static final short TIMER_CODE = 405;

    public FillViewTimer() {
        super(TIMER_CODE);
    }

    @Override
    public ProtoTimer clone() {
        return this;
    }
}
