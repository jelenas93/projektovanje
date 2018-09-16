package gui;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UnosZaposlenogController {

    @FXML
    private JFXButton nazadDugme;

    @FXML
    private JFXTextField imeField;

    @FXML
    private JFXTextField prezimeField;

    @FXML
    private JFXTextField maticniField;

    @FXML
    private JFXComboBox<?> pozicijaComboBox;

    @FXML
    private JFXButton sacuvajDugme;

    @FXML
    void nazadStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("admin.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void sacuvajStisak(ActionEvent event) {
        
    }

}
