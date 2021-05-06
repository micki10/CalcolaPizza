/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcolapizza;

import javafx.application.*; 
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.image.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.event.*; 
import java.io.*;
/**
 *
 * @author Michele
 */
public class CalcolaPizza extends Application {

    private Label titolo;
    
    private Label npizze_label;
    private TextField npizze;
    
    private Label acqua_perc;
    private Slider slider_acqua;
    
    private Label sale_perc;
    private Slider slider_sale;
    
    private Label olio_perc;
    private Slider slider_olio;
    
    public void start(Stage stage){
        
        titolo = new Label("Calcolatore Impasto Pizza");
        npizze_label = new Label("Quante pizze ?");
        npizze = new TextField();
        acqua_perc = new Label("% Acqua");
        slider_acqua = new Slider(0.0, 100.0, 60.0);
        sale_perc = new Label("Sale (gr/kg di Farina)");
        slider_sale = new Slider(0.0, 60.0, 20.0);
        olio_perc = new Label("Olio (gr/kg di Farina)");
        slider_olio = new Slider(0.0, 120.0, 40.0);
        
        /*styling objects*/
            titolo.setLayoutX(20);
            titolo.setStyle("-fx-font-size: 40; -fx-text-fill: blue; -fx-font-weigth: bold;");
        Group parameters = 
            new Group(titolo, npizze_label,npizze,acqua_perc,slider_acqua,sale_perc,slider_sale,olio_perc,slider_olio);
        Scene s1 = new Scene(parameters,800,600);
        stage.setTitle("Calcola Pizza");
        stage.setScene(s1);
        stage.show();
    }
    
    //public static void main (String [] args){}
}
