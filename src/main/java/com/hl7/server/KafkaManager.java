package com.hl7.server;

import com.hl7.server.consumer.KafkaConsume;

/**
 * KafkaManager
 */
public class KafkaManager {

    private String topic;
    public KafkaConsume consume;

    public KafkaManager(){
        consume = new KafkaConsume("tes");
        consume.start();
    }

    public KafkaManager(String topic){
        this.topic = topic;
        consume = new KafkaConsume(topic);
        consume.start();
    }

   public void close(){
       consume.destroy();
   }

    public static void main(String[] args) {
        // KafkaManager manager;
        // if(args.length > 0){
        //     manager = new KafkaManager(args[0]);
        // } else {
            // manager = new KafkaManager();
        // }
        new KafkaManager();
    }
}
