package com.hl7.capture;

import com.hl7.capture.pool.PacketPool;

import org.pcap4j.core.PacketListener;
import org.pcap4j.packet.Packet;

public class MyPacketListener implements PacketListener {

    private PacketPool pool;

    public MyPacketListener(PacketPool pool){
        this.pool = pool;
    }

    @Override
    public void gotPacket(Packet packet) {
        this.pool.setPacketData(packet);
        // IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
        // Inet4Address srcAddress = ipV4Packet.getHeader().getSrcAddr();
        // System.out.println(srcAddress.toString());

        // TcpPacket tcpPacket = packet.get(TcpPacket.class);
        
        // System.out.println(srcAddress + "    " + tcpPacket.getHeader().getSequenceNumberAsLong());

    
        // byte[] rawData = tcpPacket.getPayload().getRawData();
        // try {
            // String content = new String(rawData, "UTF-8");
            // System.out.println(content + "   " + content.contains("\r") + "   " + (tcpPacket.getHeader().getSequenceNumberAsLong() + rawData.length));
        // } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        // }

        // byte[] rawData = packet.getRawData();
        // if (rawData.length < 14) {
        //     return;
        // }

        // IpV4Packet ipV4Packet = null;
        // TcpPacket tcpPacket = null;
        // int ipV4HeaderLength = Integer.parseInt(Integer.toHexString(rawData[14]).charAt(1) + "") * 4;
        // try {
        //     ipV4Packet = IpV4Packet.newPacket(rawData, 14, ipV4HeaderLength);
        //     int tcpOffset = 14 + ipV4HeaderLength;
        //     tcpPacket = TcpPacket.newPacket(rawData, tcpOffset, rawData.length - tcpOffset);
        // } catch (IllegalRawDataException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
    }
    
}
