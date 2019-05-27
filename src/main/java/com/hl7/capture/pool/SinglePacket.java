package com.java.capture.pool;

import java.util.Date;

import org.pcap4j.packet.Packet;

public class SinglePacket {

    private byte[] data;

    private long key;

    private long date;


    public SinglePacket(byte[] packet, long key) {
        this.key = key;
        this.data = packet;
        date = new Date().getTime();
    }

    public long getDate(){
        return this.date;
    }

    public long getKey(){
        return this.key;
    }

    public byte[] getData(){
        return this.data;
    }

    public static void main(String[] args) {
        
    }
    
}