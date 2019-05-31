package com.hl7.server.consumer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.hl7.capture.pool.PacketPool;
import com.hl7.eventdecode.MessageMain;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.streams.KafkaStreams;
import org.pcap4j.packet.IllegalRawDataException;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.factory.PacketFactories;
import org.pcap4j.packet.namednumber.DataLinkType;

public class KafkaConsume extends Thread {

    private final KafkaConsumer<String, String> consumer;
    private final String topic;
    private PacketPool packetPool;

    public KafkaConsume(String topic) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.139.129:9092");
        props.put("group.id", "test2");

        // 如果value合法，则自动提交偏移量
        props.put("enable.auto.commit", "true");
        // 设置多久一次更新被消费消息的偏移量
        props.put("auto.commit.interval.ms", "1000");
        // 设置会话响应的时间，超过这个时间kafka可以选择放弃消费或者消费下一条消息
        props.put("session.timeout.ms", "30000");
        // 自动重置offset
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<String, String>(props);
        this.topic = topic;
        packetPool = new PacketPool();
    }

    @Override
    public void run() {

        consumer.subscribe(Arrays.asList(topic));

        while (true) {
            // System.out.println("sfadsaf");
            ConsumerRecords<String, String> records = consumer.poll(100);

            for (ConsumerRecord<String, String> record : records) {
                // byte[] data = record.value().getBytes();
                // System.out.println("sfadsaf");
                // try {
                //     packetPool.setPacketData(TcpPacket.newPacket(data, 0, data.length));
                // } catch (IllegalRawDataException e) {
                //     // TODO Auto-generated catch block
                //     e.printStackTrace();
                // }
                System.out.println("key :   " + record.key() + "   offset : " + record.offset() + "   " + record.value());
                new MessageMain(record.value()).createADT();
                // packetPool.setPacketData(
                //         PacketFactories.getFactory(Packet.class, 
                //         DataLinkType.class).newInstance(data, 0, data.length));

            }

        }

        // consumer.subscribe(Arrays.asList(topic));

        // while(true){
        //     System.out.println("sfadsaf");
            // System.out.println(consumer.poll(100).count());

            // ConsumerRecords<String, String> records = consumer.poll(1000);
            // for(ConsumerRecord<String, String> record : records){
                // packetPool.setPacketData(record.value());

                // System.out.println(record.key() + "   " + record.value());
            // }

            // Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
            // topicCountMap.put(topic, new Integer(a_numThreads));
            // Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
            // List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
            
            // // now launch all the threads
            // //
            // executor = Executors.newFixedThreadPool(a_numThreads);
            
            // // now create an object to consume the messages
            // //
            // int threadNumber = 0;
            // for (final KafkaStream stream : streams) {
            //     executor.submit(new ConsumerTest(stream, threadNumber));
            //     threadNumber++;
            // }

            
        
            // ConsumerRecords<Long, Packet> records = consumer.poll(100);
            // for(ConsumerRecord<Long, Packet> record : records){
            //     packetPool.setPacketData(record.value());
            //     System.out.println(record.key());
            // }
        // }
    }
}