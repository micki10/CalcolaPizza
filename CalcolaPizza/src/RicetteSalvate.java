import java.util.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Michele
 */
public class RicetteSalvate {   // classe che contiene un insieme di elementi grafici per la visualizzazione del menu a tendina
    Button Salva;
    Button Elimina;
    Label MessaggioStato;
    Label RicetteSalvate;
    ComboBox MenuRicette;
    
    public RicetteSalvate(){
        Salva = new Button("Salva");
        Elimina = new Button("Elimina");
        MessaggioStato = new Label("nessuna operazione in sospeso");
        RicetteSalvate = new Label("Ricette Salvate");
        MenuRicette = new ComboBox();
        MenuRicette.setValue("Seleziona ricetta");
        MenuRicette.setMaxWidth(220);   
    }
    
    public void setMaxRicette(int v){ // max visible row, da parametro xml
        MenuRicette.setVisibleRowCount(v); 
    }
    
    public void setMenuRicette(){ //metodo di prova
        ObservableList<String> opzioni = 
        FXCollections.observableArrayList(
                 "Ricetta 1",
                 "Ricetta 2",
                 "Ricetta di nonna",
                 "Ricetta esperimento 23"
         );
        MenuRicette.setItems(opzioni);
    }
    
    public void setColoreBottoni(String colore){    //per parametri xml
        Salva.setStyle("-fx-background-color:"+colore+";");
        Elimina.setStyle("-fx-background-color:"+colore+";");
    }
    
    public VBox disegnaLayout(){    //definisce layout finale elementi, no stile
        HBox bottoni = new HBox(Salva,Elimina);
        bottoni.setAlignment(Pos.CENTER);
        bottoni.setPadding(new Insets(5, 5, 5, 5));
        bottoni.setSpacing(10);
        VBox altro = new VBox(MessaggioStato, RicetteSalvate, MenuRicette);
        altro.setAlignment(Pos.CENTER);
        altro.setPadding(new Insets(10, 10, 10, 10));
        altro.setSpacing(10);
        return new VBox(bottoni,altro);
    }
    
    public void riempiMenu(List<Ricetta> lista){    //recupera da db la List e popola la combobox
        ObservableList<String> nomiRicette = FXCollections.observableArrayList();
        ListIterator<Ricetta> scorri = lista.listIterator();
        while(scorri.hasNext()){
            nomiRicette.add(scorri.next().getNomeRicetta());
        }
        MenuRicette.getItems().removeAll();
        MenuRicette.setItems(nomiRicette);
    }
}
