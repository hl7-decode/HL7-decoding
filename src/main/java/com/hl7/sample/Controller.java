package com.hl7.sample;

import ca.uhn.hl7v2.util.Hl7InputStreamMessageIterator;
import com.hl7.eventdecode.MessageEvent;
import com.hl7.eventdecode.MessageMain;
import com.hl7.get.MessageGet;
import com.hl7.in_mysql.util.ReadFile;
import com.hl7.manage.GetMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.File;


public class Controller implements GetMessage{
    @FXML
    TextArea HL7Data;

    @FXML
    TextArea ParserResult;

    @FXML
    Button ImportFile;

    @FXML
    Button Parse;

    @FXML
    Button connect;


    MessageGet messageGet;

    boolean listening = false;

    public void setImportFile(){
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        File selectFile = fileChooser.showOpenDialog(stage);
        HL7Data.setText(ReadFile.readFile(selectFile.getPath()).substring(1));
    }

    @Override
    public void setText(String text) {
        HL7Data.setText(text);
    }

    public void setParse() {
        String target = HL7Data.getText().replaceAll("\n", "\r");
//        target = target.substring(1);
        String result = new MessageMain(target).createADT();
        ParserResult.setText(result.replaceAll(",","\n"));
    }

    public void startListen(){
        if(!listening){
            messageGet = new MessageGet(this);
            HL7Data.setText("");
            connect.setText("停止捕获");
        } else {
            messageGet.close();
            connect.setText("开始捕获");
        }
    }

}
