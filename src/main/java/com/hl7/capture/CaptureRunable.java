package com.hl7.get;

import com.java.capture.pool.PacketPool;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.core.BpfProgram.BpfCompileMode;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;

public class CaptureRunable implements Runnable {

    PcapNetworkInterface pcapNetworkInterface;

    public CaptureRunable(PcapNetworkInterface pcapNetworkInetFace) {
        this.pcapNetworkInterface = pcapNetworkInetFace;
    }

    @Override
    public void run() {
        PcapNetworkInterface nif;
        try {
            nif = Pcaps.getDevByName(this.pcapNetworkInterface.getName());
            int snaplen = 64 * 1024;
            int timeout = 50;
            PcapHandle.Builder pBuilder = new PcapHandle.Builder(nif.getName()).snaplen(snaplen)
                    .promiscuousMode(PromiscuousMode.PROMISCUOUS).timeoutMillis(timeout).bufferSize(1 * 1024 * 1024);
            PcapHandle handle = pBuilder.build();
            
            String filter = "ip and tcp and (dst host 127.0.0.1 and dst port 8080)";
            handle.setFilter(filter, BpfCompileMode.OPTIMIZE);
            PacketPool pool = new PacketPool();
            while (true) {
                handle.loop(10, new MyPacketListener(pool));
            }
        } catch (PcapNativeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(NotOpenException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
