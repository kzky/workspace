import org.jnetpcap.Pcap
import org.jnetpcap.PcapIf

// Interface 情報取得のサンプル．

List<PcapIf> alldevs = new ArrayList<PcapIf>(); // Will be filled with NICs
StringBuilder errbuf = new StringBuilder(); // For any error msgs

/***************************************************************************
 * First get a list of devices on this system
 **************************************************************************/
int r = Pcap.findAllDevs(alldevs, errbuf);
if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
	System.err.printf("Can't read list of devices, error is %s", errbuf
			.toString());
	return;
}

/***************************************************************************
 * Second iterate through all the interface and get the HW addresses
 **************************************************************************/
for (final PcapIf i : alldevs) {
	final byte[] mac = i.getHardwareAddress();
	if (mac == null) {
		continue; // Interface doesn't have a hardware address
	}
	printf("%s = %s\n", i.getName(), asString(mac));
}

// conver byte[] mac to String 
private static String asString(final byte[] mac) {
	final StringBuilder buf = new StringBuilder();
	for (byte b : mac) {
		if (buf.length() != 0) {
			buf.append(':');
		}
		if (b >= 0 && b < 16) {
			buf.append('0');
		}
		buf.append(Integer.toHexString((b < 0) ? b + 256 : b).toUpperCase());
	}

	return buf.toString();
}
