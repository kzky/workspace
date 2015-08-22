import org.jnetpcap.packet.JMemoryPacket
import org.jnetpcap.packet.JPacket
import org.jnetpcap.protocol.JProtocol
import org.jnetpcap.protocol.lan.Ethernet
import org.jnetpcap.protocol.network.Ip4
import org.jnetpcap.protocol.tcpip.Tcp

// jMemoryPacketのサンプル
// packetを作るt機の基本

JPacket packet =  
  new JMemoryPacket(JProtocol.ETHERNET_ID,  
      " 001801bf 6adc0025 4bb7afec 08004500 "  
    + " 0041a983 40004006 d69ac0a8 00342f8c "  
    + " ca30c3ef 008f2e80 11f52ea8 4b578018 "  
    + " ffffa6ea 00000101 080a152e ef03002a "  
    + " 2c943538 322e3430 204e4f4f 500d0a");

Ip4 ip = packet.getHeader(new Ip4());  
Tcp tcp = packet.getHeader(new Tcp());  
Ethernet eth = packet.getHeader(new Ethernet())

tcp.source(50000)
tcp.destination(80);  
  
ip.checksum(ip.calculateChecksum());  
tcp.checksum(tcp.calculateChecksum());  
	eth.checksum(eth.calculateChecksum());
  
packet.scan(Ethernet.ID);

println packet 
