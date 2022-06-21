package protocols.dissemination.plumtree.utils;

import protocols.dissemination.plumtree.messages.IHaveMessage;
import pt.unl.fct.di.novasys.network.data.Host;

public class AddressedIHaveMessage {
    public final IHaveMessage msg;
    public final Host to;

    public AddressedIHaveMessage(IHaveMessage msg, Host to) {
        this.msg = msg;
        this.to = to;
    }
}
