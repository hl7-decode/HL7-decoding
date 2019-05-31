package com.hl7.server.consumer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.pcap4j.packet.IllegalRawDataException;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.factory.PacketFactories;
import org.pcap4j.packet.namednumber.DataLinkType;

public class PacketDeserializer implements Deserializer<TcpPacket> {

    @Override
    public TcpPacket deserialize(String topic, byte[] data) {
        // 如果数据为空，那么直接返回null即可
        System.out.println(data.length);
        if (data == null)
            return null;
        else{
            TcpPacket tcpPacket = null;
            try {
                tcpPacket = TcpPacket.newPacket(data,0,data.length);
            } catch (IllegalRawDataException e) {
                e.printStackTrace();
            }
            // 否则将byte[]反序列化，即转为String即可
            return tcpPacket;
        }

    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public void close() {

    }


}
