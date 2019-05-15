package com.hl7.sample;

import com.hl7.eventdecode.MessageEvent;
import com.hl7.in_mysql.util.ReadFile;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.File;


public class Controller {
    @FXML
    TextArea HL7Data;

    @FXML
    TextArea ParserResult;

    @FXML
    Button ImportFile;

    @FXML
    Button Parse;

    public void setImportFile(){
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        File selectFile = fileChooser.showOpenDialog(stage);
        HL7Data.setText(ReadFile.readFile(selectFile.getPath()));
    }

    public void setParse() {
        String target = HL7Data.getText().replaceAll("\n", "\r");
        String result = new MessageEvent(target).createADT();
        ParserResult.setText(result);
    }
}
