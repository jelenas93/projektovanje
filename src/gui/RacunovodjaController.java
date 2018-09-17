/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;

public class RacunovodjaController implements Initializable {

    @FXML
    private JFXButton dodajPlatuButton;

    @FXML
    private JFXComboBox<?> zaposleniComboBox;

    @FXML
    private Label zaposleniLabel;

    @FXML
    private Label stopaPorezaLabel;

    @FXML
    private Label doprinosZaZaposljavanjeLabel;

    @FXML
    private Label doprinosZaDjecijuZastituLabel;

    @FXML
    private Label doprinosZaZdravstvenoLabel;

    @FXML
    private Label doprinosZaPenzionoLabel;

    @FXML
    private Label stopaZaPenzionoLabel;

    @FXML
    private Label stopaZaZdravstvenoLAbel;

    @FXML
    private Label stopaZaDjecijuZastituLanel;

    @FXML
    private Label stopaZaZaposljavanjeLabel;

    @FXML
    private Label netoTekuciRadLabel;

    @FXML
    private Label netoMinuliRadLabel;

    @FXML
    private Label brutoLabel;

    @FXML
    private Label porezNaPlatuLabel;

    @FXML
    private Label datumOdLabel;

    @FXML
    private Label datumDoLabel;

    @FXML
    private JFXTextField doprinosZaPenzionoField;

    @FXML
    private JFXTextField doprinosZaZdavstvenoField;

    @FXML
    private JFXTextField doprinosZaDJecijuZastituField;

    @FXML
    private JFXTextField doprinosZaZaposljavanjeField;

    @FXML
    private JFXTextField stopaPorezaField;

    @FXML
    private JFXTextField stopaZaPenzionoField;

    @FXML
    private JFXTextField stopaZaZdravstvenoField;

    @FXML
    private JFXTextField stopaZaDjecijuZastituField;

    @FXML
    private JFXTextField stopaZaZaposljavanjeField;

    @FXML
    private JFXTextField netoTekuciRadField;

    @FXML
    private JFXTextField netoMinuliRadField;

    @FXML
    private JFXTextField porezNaPlatuField;

    @FXML
    private JFXTextField brutoField;
    @FXML
    private JFXDatePicker datumOdDate;

    @FXML
    private JFXDatePicker datumDoDate;

    @FXML
    private JFXButton sacuvajPlatuButtton;

    @FXML
    private JFXButton pregledPlatneListeButton;

    @FXML
    private TableView<?> tabelaPlata;

    @FXML
    private JFXComboBox<?> noviZaposleniComboBox;

    @FXML
    private JFXComboBox<?> prikaziTransakcijuComboBox;

    @FXML
    private JFXTextArea prikazTransakcijeText;

    @FXML
    private JFXButton dodajTransakcijuButton;

    @FXML
    private JFXButton dodajFakturuButton;

    @FXML
    private JFXButton azurirajFakturu;

    @FXML
    private JFXComboBox<?> prikazFaktureComboBox;

    @FXML
    private JFXTextArea fakturaText;

    @FXML
    private Label datumLabel;

    @FXML
    private Label brojRacunaLabel;

    @FXML
    private Label vrstaTransakcijeLabel;

    @FXML
    private Label kolicinaLabel;

    @FXML
    private Label jedinicaMjereLabel;

    @FXML
    private Label cijenaLabel;

    @FXML
    private Label kupaclabel;

    @FXML
    private Label robbaLabel;

    @FXML
    private JFXDatePicker datumFaktuere;

    @FXML
    private JFXTextField racunField;

    @FXML
    private JFXComboBox<?> transakcijaComboBox;

    @FXML
    private JFXTextField kolicinaRobeField;

    @FXML
    private JFXTextField cijenaField;

    @FXML
    private JFXTextField kupacField;

    @FXML
    private JFXComboBox<?> jedinicaMjereComboBox;

    @FXML
    private JFXButton sacuvajFakturuButton;

    @FXML
    private ScrollPane robaScrool;

    @FXML
    void dodajFakturuStisak(ActionEvent event) {
        otkrijPodatkeFaktura();
    }

    @FXML
    void azurirajFakturuStisak(ActionEvent event) {
        otkrijPodatkeFaktura();
    }

    @FXML
    void sacuvajFakturuStisak(ActionEvent event) {
        sakrijPodatkeFaktura();
    }

    @FXML
    void zaposleniIzbor(ActionEvent event) {

    }

    @FXML
    void dodajPlatuStisak(ActionEvent event) {
        otkrijZaDodajPlatu();
    }

    @FXML
    void izmjeniPlatuStisak(ActionEvent event) {
        otkrijZaDodajPlatu();
        zaposleniLabel.setVisible(false);
        noviZaposleniComboBox.setVisible(false);
    }

    @FXML
    void pregledPlataStisak(ActionEvent event) {
        sakrijPodatkePlata();
        tabelaPlata.setVisible(true);
    }

    @FXML
    void prikaziFakturu(ActionEvent event) {

    }

    @FXML
    void sacuvajPlatuStisak(ActionEvent event) {
        sakrijPodatkePlata();
    }

    private void sakrijPodatkeFaktura() {
        fakturaText.setVisible(false);
        datumLabel.setVisible(false);
        brojRacunaLabel.setVisible(false);
        vrstaTransakcijeLabel.setVisible(false);
        kolicinaLabel.setVisible(false);
        jedinicaMjereLabel.setVisible(false);
        cijenaLabel.setVisible(false);
        kupaclabel.setVisible(false);
        robbaLabel.setVisible(false);
        datumFaktuere.setVisible(false);
        racunField.setVisible(false);
        transakcijaComboBox.setVisible(false);
        kolicinaRobeField.setVisible(false);
        cijenaField.setVisible(false);
        kupacField.setVisible(false);
        jedinicaMjereComboBox.setVisible(false);
        sacuvajFakturuButton.setVisible(false);
        robaScrool.setVisible(false);

    }
    private void otkrijPodatkeFaktura(){
      fakturaText.setVisible(true);
        datumLabel.setVisible(true);
        brojRacunaLabel.setVisible(true);
        vrstaTransakcijeLabel.setVisible(true);
        kolicinaLabel.setVisible(true);
        jedinicaMjereLabel.setVisible(true);
        cijenaLabel.setVisible(true);
        kupaclabel.setVisible(true);
        robbaLabel.setVisible(true);
        datumFaktuere.setVisible(true);
        racunField.setVisible(true);
        transakcijaComboBox.setVisible(true);
        kolicinaRobeField.setVisible(true);
        cijenaField.setVisible(true);
        kupacField.setVisible(true);
        jedinicaMjereComboBox.setVisible(true);
        sacuvajFakturuButton.setVisible(true);
        robaScrool.setVisible(true);   
    }
    private void otkrijZaDodajPlatu() {
        zaposleniLabel.setVisible(true);
        noviZaposleniComboBox.setVisible(true);
        stopaPorezaLabel.setVisible(true);
        doprinosZaZaposljavanjeLabel.setVisible(true);
        doprinosZaDjecijuZastituLabel.setVisible(true);
        doprinosZaZdravstvenoLabel.setVisible(true);
        doprinosZaPenzionoLabel.setVisible(true);
        stopaZaPenzionoLabel.setVisible(true);
        stopaZaZdravstvenoLAbel.setVisible(true);
        stopaZaDjecijuZastituLanel.setVisible(true);
        stopaZaZaposljavanjeLabel.setVisible(true);
        netoTekuciRadLabel.setVisible(true);
        netoMinuliRadLabel.setVisible(true);
        brutoLabel.setVisible(true);
        porezNaPlatuLabel.setVisible(true);
        datumOdLabel.setVisible(true);
        datumDoLabel.setVisible(true);
        doprinosZaPenzionoField.setVisible(true);
        doprinosZaZdavstvenoField.setVisible(true);
        doprinosZaDJecijuZastituField.setVisible(true);
        doprinosZaZaposljavanjeField.setVisible(true);
        stopaPorezaField.setVisible(true);
        stopaZaPenzionoField.setVisible(true);
        stopaZaZdravstvenoField.setVisible(true);
        stopaZaDjecijuZastituField.setVisible(true);
        stopaZaZaposljavanjeField.setVisible(true);
        netoTekuciRadField.setVisible(true);
        netoMinuliRadField.setVisible(true);
        porezNaPlatuField.setVisible(true);
        datumOdDate.setVisible(true);
        datumDoDate.setVisible(true);
        sacuvajPlatuButtton.setVisible(true);
        tabelaPlata.setVisible(false);
        brutoField.setVisible(true);
    }
    private void sakrijPodatkePlata() {
        zaposleniLabel.setVisible(false);
        noviZaposleniComboBox.setVisible(false);
        stopaPorezaLabel.setVisible(false);
        doprinosZaZaposljavanjeLabel.setVisible(false);
        doprinosZaDjecijuZastituLabel.setVisible(false);
        doprinosZaZdravstvenoLabel.setVisible(false);
        doprinosZaPenzionoLabel.setVisible(false);
        stopaZaPenzionoLabel.setVisible(false);
        stopaZaZdravstvenoLAbel.setVisible(false);
        stopaZaDjecijuZastituLanel.setVisible(false);
        stopaZaZaposljavanjeLabel.setVisible(false);
        netoTekuciRadLabel.setVisible(false);
        netoMinuliRadLabel.setVisible(false);
        brutoLabel.setVisible(false);
        porezNaPlatuLabel.setVisible(false);
        datumOdLabel.setVisible(false);
        datumDoLabel.setVisible(false);
        doprinosZaPenzionoField.setVisible(false);
        doprinosZaZdavstvenoField.setVisible(false);
        doprinosZaDJecijuZastituField.setVisible(false);
        doprinosZaZaposljavanjeField.setVisible(false);
        stopaPorezaField.setVisible(false);
        stopaZaPenzionoField.setVisible(false);
        stopaZaZdravstvenoField.setVisible(false);
        stopaZaDjecijuZastituField.setVisible(false);
        stopaZaZaposljavanjeField.setVisible(false);
        netoTekuciRadField.setVisible(false);
        netoMinuliRadField.setVisible(false);
        porezNaPlatuField.setVisible(false);
        datumOdDate.setVisible(false);
        datumDoDate.setVisible(false);
        sacuvajPlatuButtton.setVisible(false);
        tabelaPlata.setVisible(false);
        brutoField.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sakrijPodatkePlata();
        sakrijPodatkeFaktura();
    }

}
