package com.hl7.capture.view;

import com.hl7.capture.Capture;
import com.hl7.capture.pool.PacketPool;
import com.hl7.eventdecode.MessageMain;
import com.hl7.manage.GetMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.Date;

public class Controller implements GetMessage {

    @FXML
    TextField dstIP;
    @FXML
    TextField dstPort;
    @FXML
    Button StartCapture;
    @FXML
    TextArea HL7Data;

    @Override
    public void setText(String text) {
        // System.out.println("text   " + text);
        HL7Data.setText(text.replaceAll("\r", "\n"));
        new Thread(() -> {
            // System.out.println(new Date().getTime());
            new MessageMain(text).createADT();
            // System.out.println(new Date().getTime());
        }).start();
    }

    private Capture capture = new Capture();
    boolean listening = false;

    public void setStartCapture(){
        if(!listening){
            listening = true;
            startCapture();
        }
    }

    public void startCapture(){
        PacketPool.getMessage = this;
        String ip = dstIP.getText();
        String port = dstPort.getText();
        try {
            int dst_port = Integer.valueOf(port);
            this.capture.startCapture(ip, dst_port);
            StartCapture.setText("正在捕获............");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
