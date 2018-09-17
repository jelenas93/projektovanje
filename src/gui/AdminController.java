package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class AdminController {

    @FXML
    private TableView<?> tabela;

    @FXML
    private JFXButton dodajButton;

    @FXML
    private JFXButton IzmjeniButton;

    @FXML
    private JFXButton obrisiZaposlenog;
   
    @FXML
    private JFXTextField traziZaposlenogField;

    @FXML
    void dodajStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("unosZaposlenog.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void izmjeniStisak(ActionEvent event) {

    }

    @FXML
    void obrisiStisak(ActionEvent event) {

    }

}
