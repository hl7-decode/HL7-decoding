package com.hl7.server.consumer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.factory.PacketFactories;
import org.pcap4j.packet.namednumber.DataLinkType;

public class PacketDeserializer implements Deserializer<Packet> {

    @Override
    public Packet deserialize(String topic, byte[] data) {
        // 如果数据为空，那么直接返回null即可
        if (data == null)
            return null;
        else
            // 否则将byte[]反序列化，即转为String即可
            return PacketFactories.getFactory(Packet.class, DataLinkType.class).newInstance(data, 0,data.length);
        
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public void close() {

    }


}