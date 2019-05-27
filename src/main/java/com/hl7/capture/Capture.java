package com.hl7.get;

import java.util.List;

import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;

public class Capture {

    public Capture() throws PcapNativeException {
        List<PcapNetworkInterface> alldev = Pcaps.findAllDevs();
        
        for (PcapNetworkInterface one : alldev){
            new Thread(new CaptureRunable(one)).start();
        }
    }

    public static void main(String[] args) throws PcapNativeException {
        new Capture();
    }

}
