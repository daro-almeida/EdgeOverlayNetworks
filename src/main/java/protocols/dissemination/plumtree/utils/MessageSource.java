package protocols.dissemination.plumtree.utils;

import pt.unl.fct.di.novasys.network.data.Host;

public class MessageSource {

    public final Host peer;
    public final int round;

    public MessageSource(Host peer, int round) {
        this.peer = peer;
        this.round = round;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageSource that = (MessageSource) o;
        return peer.equals(that.peer);
    }

    @Override
    public int hashCode() {
        return peer.hashCode();
    }
}
