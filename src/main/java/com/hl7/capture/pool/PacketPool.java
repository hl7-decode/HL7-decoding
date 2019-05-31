package com.hl7.capture.pool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import com.hl7.manage.GetMessage;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;

/**
 * PacketPool
 */
public class PacketPool {

    public static GetMessage getMessage = null;

    private ConcurrentHashMap<Long, SinglePacket> seq;

    private boolean con = true;

    public PacketPool() {
        seq = new ConcurrentHashMap();
        new Thread(() -> {
            while(con){
                long now = new Date().getTime();
                Collection<SinglePacket> set = seq.values();
                for(SinglePacket single : set){
                    if(now - single.getDate() > 2000){
                        if(this.seq.containsKey(single.getKey()))
                            this.seq.remove(single.getKey());
                    }
                    if(!con) 
                        break;
                }

                // try{
                //     Thread.sleep(1000);
                // }catch (Exception e){
                //     e.printStackTrace();
                // }
            }
        }).start();
    }

    public void destory(){
        this.con = false;
    }

     @Override
    // @Deprecated(since = "9")
     protected void finalize() throws Throwable {
         super.finalize();
         this.con = false;
     }

    public void setPacketData(TcpPacket tcpPacket) {
        if(tcpPacket == null){
            System.out.println("为空");
        }
        System.out.println(tcpPacket.getRawData().length + "  " + (tcpPacket.getPayload() == null));
//        TcpPacket tcpPacket = packet.get(TcpPacket.class);
//        System.out.println("error : " + tcpPacket.getPayload() + "  header : " + tcpPacket.getHeader());
        long seqNum = tcpPacket.getHeader().getSequenceNumberAsLong();
        byte[] newData = null;
        if(tcpPacket.getPayload() != null)
            newData = tcpPacket.getPayload().getRawData();
        if (seq.containsKey(seqNum)) {
            byte[] mergeData = this.mergeArray(seq.get(seqNum).getData(), newData);
            seq.remove(seqNum);
            seqNum += (long) (newData == null ? 0 : newData.length);
            seqNum = this.setData(seqNum, mergeData);
        } else if(seq.containsKey(seqNum + (newData == null? 0 : newData.length))){
            
        } else {
            if(tcpPacket.getHeader().getSyn())
                seqNum += 1;
            else
                seqNum += (long) (newData == null ? 0 : newData.length);;
            seq.put(seqNum, new SinglePacket(tcpPacket.getPayload() == null ? null : tcpPacket.getPayload().getRawData(), seqNum));
        }
        if (tcpPacket.getHeader().getPsh()) {
            this.getStringData(seqNum);
        }
        if(tcpPacket.getHeader().getFin()){
            seq.remove(seqNum);
        }
    }

    private void getStringData(long seqNum) {
        byte[] data = seq.get(seqNum).getData();
        try {
            System.out.println(data == null);
            if(data == null) return;
            String result = new String(data, "UTF-8");
            System.out.println(result.replaceAll("\r", "\n"));
            // System.out.println(result + "    " + data.length + "    " + result.length());
//            getMessage.setText(result);
//            System.out.println(this.seq.size());
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        seq.remove(seqNum);
    }

    private long setData(long seqNum, byte[] data){
        if(seq.containsKey(seqNum)){
            byte[] after = seq.get(seqNum).getData();
            seq.remove(seqNum);
            seqNum += (long) after.length;
            return seqNum + setData(seqNum, this.mergeArray(data, after));
        } else {
            seq.put(seqNum, new SinglePacket(data, seqNum));
            return seqNum;
        }
    }

    public static byte[] mergeArray(byte[] array1, byte[] array2){
        byte[] array3 = null;
        ByteArrayOutputStream os;
        try {
            os = new ByteArrayOutputStream();
            os.write(array1);
            os.write(array2);
            array3 = os.toByteArray();
            os.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            return array3;
        }
    }
}
