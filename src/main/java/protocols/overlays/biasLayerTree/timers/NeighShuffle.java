package protocols.overlays.biasLayerTree.timers;


import pt.unl.fct.di.novasys.babel.generic.ProtoTimer;

public class NeighShuffle extends ProtoTimer {
    public static final short TIMER_CODE = 402;

    public NeighShuffle() {
        super(TIMER_CODE);
    }

    @Override
    public NeighShuffle clone() {
        return this;
    }
}
