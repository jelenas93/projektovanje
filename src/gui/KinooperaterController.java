/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author jelen
 */
public class KinooperaterController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private TableView<?> tabelaOpreme;

    @FXML
    private JFXTextField traziOpremu;

    @FXML
    private JFXButton dodajOpremuButton;

    @FXML
    private JFXButton izmjeniOpremu;

    @FXML
    private JFXButton obrisiOpremu;

    @FXML
    void dodajStisak(ActionEvent event) {

    }

    @FXML
    void izmjeniStisak(ActionEvent event) {

    }

    @FXML
    void obrisiStisak(ActionEvent event) {

    }

}
