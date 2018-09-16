package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class MenadzerController implements Initializable {

    @FXML
    private Label nazivFilmaLabel;

    @FXML
    private Label opisFilmaLabel;

    @FXML
    private Label tipFilmaLabel;

    @FXML
    private Label trajanjeFilmaLabel;

    @FXML
    private Label slikaFilmaLabel;

    @FXML
    private Label trejlerFilmaLabel;

    @FXML
    private JFXTextField nazivFilmaField;

    @FXML
    private JFXTextField trajanjeFilmaFilma;

    @FXML
    private ImageView slikaFilmaView;

    @FXML
    private JFXTextField trejlerFilmaField;

    @FXML
    private JFXTextArea opisFilmaArea;

    @FXML
    private JFXComboBox<String> tipFilma;

    @FXML
    private JFXButton sacuvajFilmButton;

    @FXML
    private JFXButton otkaziFilmButton;

    @FXML
    private JFXButton obrisiFilmButton;

    @FXML
    private Label nazivSaleLabel;

    @FXML
    private Label brojredovaLabel;

    @FXML
    private Label brojKolonaLabel;

    @FXML
    private JFXButton sacuvajSaluButton;

    @FXML
    private JFXButton otkaziSaluButton;

    @FXML
    private JFXButton obrisiSaluButton;

    @FXML
    private JFXTextField nazivSaleField;

    @FXML
    private JFXTextField brojRedovaField;

    @FXML
    private JFXTextField brojKolonaField;

    @FXML
    private Label ponudaFilmLabel;

    @FXML
    private Label datumPonudaLabel;

    @FXML
    private JFXButton sacuvajPonuduButton;

    @FXML
    private JFXButton otkaziPonuduButton;

    @FXML
    private JFXButton obrisiPonuduButton;

    @FXML
    private JFXTextField ponudaFilmField;

    @FXML
    private JFXDatePicker datumPonudedate;

    @FXML
    private TableView<?> tabelaPonude;

    @FXML
    private JFXButton dodajFilmButton;

    @FXML
    private JFXComboBox<String> izmjeniFilmComboBox;

    @FXML
    private JFXComboBox<String> obrisiFilmComboBox;

    @FXML
    private JFXButton dodajPonudu;

    @FXML
    private JFXComboBox<String> ponudaComboBox;

    @FXML
    private JFXButton prikazPonudaButton;

    @FXML
    private JFXButton dodajSaluButton;

    @FXML
    private JFXComboBox<String> izmjeniSaluComboBox;

    @FXML
    private JFXComboBox<String> obrisiSaluComboBox;

    @FXML
    void sacuvajFilmStisak(ActionEvent event) {

    }

    @FXML
    void dodajSaluStisak(ActionEvent event) {

    }

    @FXML
    void obrisiFilmStisak(ActionEvent event) {

    }

    @FXML
    void otkaziFilmStisak(ActionEvent event) {
        //sve setujes u text fiels na prazno
    }

    @FXML
    void otkaziPonuduStisak(ActionEvent event) {
        //sve setujes u text fiels na prazno
    }

    @FXML
    void otkaziSaluStisak(ActionEvent event) {
        //sve setujes u text fiels na prazno
    }

    @FXML
    void obrisiPonuduStisak(ActionEvent event) {
        //sve setujes u text fiels na prazno
    }

    @FXML
    void obrisiSaluStisak(ActionEvent event) {
        //sve setujes u text fiels na prazn
    }

    @FXML
    void sacuvajPonuduStisak(ActionEvent event) {

    }

    @FXML
    void sacuvajSaluStisak(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        obrisiFilmButton.setVisible(false);
        obrisiSaluButton.setVisible(false);
        obrisiPonuduButton.setVisible(false);
    }

}
