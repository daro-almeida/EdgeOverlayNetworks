package utils;

import pt.unl.fct.di.novasys.network.data.Host;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Contacts {

	public static List<Host> parseContacts(Properties props) throws UnknownHostException {
		String[] allContacts = props.getProperty("contacts").split(",");

		List<Host> contacts = null;
		try {
			contacts = new ArrayList<>(allContacts.length);
			for (String contact : allContacts) {
				String[] hostElems = contact.split(":");
				contacts.add(new Host(InetAddress.getByName(hostElems[0]), Short.parseShort(hostElems[1])));
			}
		} catch (Exception e) {
			System.err.println("Invalid contact on configuration: '" + props.getProperty("contacts"));
			e.printStackTrace();
			System.exit(-1);
		}

		return contacts;
	}
}
