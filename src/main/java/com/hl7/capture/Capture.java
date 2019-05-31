package com.hl7.capture;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;

public class Capture {

    private ExecutorService threadPool;

//    String filter = "ip and tcp and (dst host 127.0.0.1 and dst port 8080)";
    public Capture(){

    }


    public Capture(String ip, int port) throws PcapNativeException {
        this.startCapture(ip, port);
    }

    public void startCapture(String ip, int port) throws PcapNativeException {
        threadPool = Executors.newCachedThreadPool();
        System.out.println("started");
        List<PcapNetworkInterface> alldev = Pcaps.findAllDevs();
        String filter = "ip and tcp and (dst host " + ip + " and dst port " + port + ")";
        for (PcapNetworkInterface one : alldev) {
            threadPool.execute(new CaptureRunable(one, filter));
        }
    }

    public void close(){
        System.out.println("closed!");
        threadPool.shutdownNow();
//        threadPool.shutdown();
    }



    public static void main(String[] args) throws PcapNativeException {
        Capture c = new Capture("127.0.0.1", 8081);
//        c.close();
        // c.startCapture("127.0.0.1", 8080);
    }
}
