package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginFormaController {

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton exitButton;

    @FXML
    void exitStisak(ActionEvent event) {
        System.exit(0);
    }

    @FXML void enter(KeyEvent e) throws IOException{
        loginButton.setOnKeyPressed(
                event->{
            try {
                if (username.getText().equals("admin")) {
            Parent korisnikView = FXMLLoader.load(getClass().getResource("admin.fxml"));
            Scene korisnikScena = new Scene(korisnikView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(korisnikScena);
            window.centerOnScreen();
            window.show();
        }
            } catch (IOException ex) {
                Logger.getLogger(LoginFormaController.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
        );    }
    
    @FXML
    void stisakDugmeta(ActionEvent event) throws IOException {
        if (username.getText().equals("admin")) {
            Parent korisnikView = FXMLLoader.load(getClass().getResource("admin.fxml"));
            Scene korisnikScena = new Scene(korisnikView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(korisnikScena);
            window.centerOnScreen();
            window.show();
        } else if (username.getText().equals("kinooperater")) {
            Parent korisnikView = FXMLLoader.load(getClass().getResource("kinooperater.fxml"));
            Scene korisnikScena = new Scene(korisnikView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(korisnikScena);
            window.centerOnScreen();
            window.show();
        }else if(username.getText().equals("prodavackarata")){
            Parent korisnikView = FXMLLoader.load(getClass().getResource("prodavacKarata.fxml"));
            Scene korisnikScena = new Scene(korisnikView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(korisnikScena);
            window.centerOnScreen();
            window.show();
        }else if(username.getText().equals("a")){
            Parent korisnikView = FXMLLoader.load(getClass().getResource("prodavacHraneiPica.fxml"));
            Scene korisnikScena = new Scene(korisnikView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(korisnikScena);
            window.centerOnScreen();
            window.show();
        }else if(username.getText().equals("skladistar")){
            Parent korisnikView = FXMLLoader.load(getClass().getResource("skladistar.fxml"));
            Scene korisnikScena = new Scene(korisnikView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(korisnikScena);
            window.centerOnScreen();
            window.show();
        }else if(username.getText().equals("menadzer")){
            Parent korisnikView = FXMLLoader.load(getClass().getResource("menadzer.fxml"));
            Scene korisnikScena = new Scene(korisnikView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(korisnikScena);
            window.centerOnScreen();
            window.show();
        }
        else if(username.getText().equals("racunovodja")){
            Parent korisnikView = FXMLLoader.load(getClass().getResource("racunovodja.fxml"));
            Scene korisnikScena = new Scene(korisnikView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(korisnikScena);
            window.centerOnScreen();
            window.show();
        }
        
        // System.exit(0);
    }

}
