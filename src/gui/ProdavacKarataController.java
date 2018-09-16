package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ProdavacKarataController implements Initializable{

    
    @FXML
    private JFXComboBox<String> filmComboBox;

    @FXML
    private JFXComboBox<String> terminComboBox;  //mozda kao date

    @FXML
    private JFXButton rezervisiButton;
    
    @FXML
    private JFXButton kupiButton;

    @FXML
    void kupiStisak(ActionEvent event) {

    }

    @FXML
    void rezervisiStisak(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       /* filmComboBox=new JFXComboBox<Label>();
        for(int i=0;i<5;i++){
            filmComboBox.getItems().add(new Label(""+i));
            
        }*/
        
    }

}
