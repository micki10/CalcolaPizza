import javafx.beans.property.*;
import javafx.collections.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
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
public class TabellaRicetta {
    private final Label Titolo;
    TextField NomeRicetta;
    TableView<Ingrediente> TabellaIngredienti;  //tabella ricetta è un insieme di elementi grafici.
    
    public static class Ingrediente{ //classe per l'appoggio della observableList di visualizzazione ingredienti
        private final SimpleStringProperty nome;        //  arbitrari
        private final SimpleDoubleProperty quantita;    //  ricavato da Ricetta
        Ingrediente(String nome,double quantita){
            this.nome = new SimpleStringProperty(nome);
            this.quantita = new SimpleDoubleProperty(quantita);
        }
        public String getNome(){ return nome.get();}
        public double getQuantita() { return quantita.doubleValue();}
        public void setNome(String s){ nome.set(s); }
        public void setQuantita(double d){ quantita.set(d); }
    }
    
    public TabellaRicetta(){
        Titolo = new Label("Ricetta");
        NomeRicetta = new TextField();
        TabellaIngredienti = new TableView();
        TabellaIngredienti.setEditable(false);   
        
        TableColumn ingrediente_col = new TableColumn("Ingrediente");
        TableColumn peso_col = new TableColumn("Peso [gr]");
      
        ingrediente_col.setCellValueFactory(new PropertyValueFactory<>("nome")); //associa la colonna al campo di observableList<Ingrediente>
        peso_col.setCellValueFactory(new PropertyValueFactory<>("quantita"));
        
        ingrediente_col.setMinWidth(150); //larghezza minima per consentire la visualizzazione completa del nome della colonna
        peso_col.setMinWidth(100);      //  come sopra
        TabellaIngredienti.getColumns().addAll(ingrediente_col, peso_col);
    }

    public VBox disegnaTabella(){           //restituisce il container vbox che definisce il layout finale
        VBox elem = new VBox(Titolo, NomeRicetta, TabellaIngredienti);
        elem.setAlignment(Pos.CENTER);
        return elem;
    }
    
    public void riempiTabella(Ricetta r){   // genera le righe della tabella a partire da un oggetto Ricetta
        ObservableList<Ingrediente> ingredienti = FXCollections.observableArrayList(
                new Ingrediente("Farina", r.getFarina()),
                new Ingrediente("Acqua", r.getAcqua()),
                new Ingrediente("Sale", r.getSale()),
                new Ingrediente("Olio", r.getOlio()),
                new Ingrediente("Lievito", r.getLievito())
        );
        TabellaIngredienti.setItems(ingredienti);
    }
}
