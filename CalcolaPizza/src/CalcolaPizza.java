/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.*;
import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.util.*;

/**
 *
 * @author Michele
 */

public class CalcolaPizza extends Application { //controller applicativo principale per applicazione CalcolaPizza
    BorderPane container;   //contenitore di tutti gli elementi grafici
    Label TitoloGUI;        
    DatiRicetta dr;         //sinistra
    GraficoRicetta gr;      //sinsitra
    RicetteSalvate rs;      //destra
    TabellaRicetta tr;      //destra
    VBox elementi_sinistra; //layout
    VBox elementi_destra;   //layout
    HBox intestazione;      //layout
    EventoNavigazioneUI enui;
    ParametriCalcolaPizza param;
    CacheUltimaRicetta cache;
    /**
     *
     * @param p
     */
    public void applicaParametriXML(ParametriCalcolaPizza p){ 
        /*parametri DatiRicetta valori di default dei campi input*/
        dr.setNpizze(p.getDefaultNPizze());
        dr.setPesoPizze(p.getDefaultPeso());
        dr.setOre(p.getDefaultOre());
        dr.setSliderIdro(p.getDefaultIdro()); // imposta i listener per lo slider idratazione
        dr.setSliderSale(p.getDefaultSale());
        dr.setSliderOlio(p.getDefaultOlio());
        dr.setSliderTemperatura(p.getDefaultTAmb());
        dr.setColoreBottoni(p.getColoreBottoni());
        rs.setColoreBottoni(p.getColoreBottoni());
        rs.setMaxRicette(p.getMaxRicette());
        container.setStyle("-fx-font-family:"+p.getFont()+"; -fx-font-size:"+p.getDimensioniFont()+"px;"+
                           "-fx-background-color:"+p.getColoreBackground()+";"); // applica i parametri direttamente al contenitore
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {    //metodo per la creazione della grafica
        Scene scene;
        Insets ins;
        container = new BorderPane(); // container di tutti gli elementi grafici
        param = new ParametriCalcolaPizza();
        param = param.LeggiParametriXML();
        TitoloGUI = new Label("Calcolatore Impasto Pizza"); 
        TitoloGUI.setStyle("-fx-font-weight:bold;");
        intestazione = new HBox(TitoloGUI);
        intestazione.setAlignment(Pos.CENTER);
        intestazione.setPadding(new Insets(20,10,20,10)); // top - right - bottom - left
        container.setTop(intestazione); // titolo aggiunto al  container
        dr = new DatiRicetta();
        gr = new GraficoRicetta();
        tr = new TabellaRicetta();
        rs = new RicetteSalvate();
        OperazioniRicettario op = new OperazioniRicettario();
        rs.riempiMenu(op.OttieniRicette());
        elementi_sinistra = new VBox(dr.DisegnaDati(),gr);
        elementi_destra = new VBox(tr.disegnaTabella(),rs.disegnaLayout());
        container.setLeft(elementi_sinistra);   // imposta elementi della colonna di sinistra
        container.setRight(elementi_destra);    //imposta elementi della colonna di destra
        ins = new Insets(10);
        BorderPane.setMargin(elementi_destra,ins);
        BorderPane.setMargin(elementi_sinistra,ins);
        this.applicaParametriXML(param);    //imposta parametri da file xml
        this.ApplicaHandler(param);
        scene = new Scene(container, param.getLarghezza(), param.getAltezza());
        this.NuovoEventoStage(primaryStage, param);
        primaryStage.setTitle("Calcolatore Impasto Pizza");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public void ApplicaHandler(ParametriCalcolaPizza p){ // frammentazione codice: chiama i metodi di controllo
        this.GestisciCalcola();
        this.GestisciPulisci(p);
        this.GestisciMenuRicetta();
        this.GestisciSalva();
        this.GestisciElimina();

    }
    
    public void GestisciCalcola(){  //definisce la logica alla la pressione del pulsante "Calcola"
        dr.Calcola.setOnAction((ActionEvent e) -> {
            Ricetta r;
            r = dr.getRicetta();
            r.CalcolaRicetta();
            System.out.println(r.toString());
            tr.riempiTabella(r);
            tr.NomeRicetta.setText("");
            gr.riempiGrafico(r);
            this.NuovoEventoPulsanti(TipoEvento.calcola,param);
        });
    }    
    public void GestisciPulisci(ParametriCalcolaPizza p){ // definisce la logica alla pressione del pulsante "Pulisci"
        dr.Pulisci.setOnAction((ActionEvent e)->{
            dr.setNpizze(p.getDefaultNPizze());
            dr.setPesoPizze(p.getDefaultPeso());
            dr.setSliderIdro(p.getDefaultIdro());
            dr.setSliderSale(p.getDefaultSale());
            dr.setSliderOlio(p.getDefaultOlio());
            dr.setOre(p.getDefaultOre());
            dr.setSliderTemperatura(p.getDefaultTAmb());
            this.NuovoEventoPulsanti(TipoEvento.pulisci,param);
        });
    }

    public void GestisciSalva(){        //definisce la logica alla pressione del pulsante "Salva"
        rs.Salva.setOnAction((ActionEvent e) -> {
            String n = tr.NomeRicetta.getText();
            if("".equals(n)){
                rs.MessaggioStato.setText("Inserire un nome per la Ricetta !");
                rs.MessaggioStato.setStyle("-fx-text-fill: red;");
            }
            else{
                OperazioniRicettario or = new OperazioniRicettario();
                Ricetta dainserire ;
                dainserire = dr.getRicetta();
                dainserire.CalcolaRicetta();
                dainserire.setNome(n);
                if(or.InserisciRicetta(dainserire)){
                    rs.MessaggioStato.setText("Ricetta Inserita !");
                    rs.MessaggioStato.setStyle("-fx-text-fill: green");
                    rs.riempiMenu(or.OttieniRicette()); // aggiorna elenco ricette 
                }
                else{
                    rs.MessaggioStato.setText("Errore Inserimento !");
                    rs.MessaggioStato.setStyle("-fx-text-fill: red");
                }
            }
            this.NuovoEventoPulsanti(TipoEvento.salva,param); // invia log
        });
    }
    public void GestisciElimina(){
        rs.Elimina.setOnAction((ActionEvent e) -> { // alla pressione
            String n = rs.MenuRicette.getSelectionModel().getSelectedItem().toString(); //recupera il nome della ricetta dalla cbox
            OperazioniRicettario or = new OperazioniRicettario();   //istanzia il controller db
            if(or.EliminaRicetta(n)){   // restituisce true se ricetta eliminata correttamente
                rs.MessaggioStato.setText("Ricetta eliminata !");
                rs.MessaggioStato.setStyle("-fx-text-fill: green");
                rs.riempiMenu(or.OttieniRicette());
                rs.MenuRicette.getSelectionModel().selectFirst();
            }
            else{
                rs.MessaggioStato.setText("Errore di eliminazione !");
                rs.MessaggioStato.setStyle("-fx-text-fill: red");
            }
            this.NuovoEventoPulsanti(TipoEvento.elimina,param); // invia log
        }
        );
    }
    
    public void GestisciMenuRicetta(){  // definisce la logica alla pressione di un elemento del menu MenuRicette in RicetteSalvate
        rs.MenuRicette.setOnAction((e) -> {
            if(rs.MenuRicette.getSelectionModel().getSelectedItem() == null)
                rs.MenuRicette.getSelectionModel().selectFirst();
            String ricetta_selezionata = rs.MenuRicette.getSelectionModel().getSelectedItem().toString();
            Ricetta r = new OperazioniRicettario().ottieniRicetta(ricetta_selezionata);
            tr.riempiTabella(r);
            tr.NomeRicetta.setText(r.getNomeRicetta());
            dr.setRicetta(r);
            gr.riempiGrafico(r);
        });
    }
    
    public void  NuovoEventoPulsanti(TipoEvento tipo, ParametriCalcolaPizza p){
        EventoNavigazioneUI evento = new EventoNavigazioneUI("calcolapizza",p.getIpClient(),getDataOraCorrenti(), tipo);
        evento.InviaLog(p.getIpServer(), p.getPortaServer());
    }
    
    public void NuovoEventoStage(Stage stage, ParametriCalcolaPizza p){
        stage.setOnCloseRequest((WindowEvent we) -> {
            EventoNavigazioneUI  enui;
            enui = new EventoNavigazioneUI("calcolapizza",p.getIpClient(),
            getDataOraCorrenti(), TipoEvento.chiusura);
            System.out.println(enui.toString());
            enui.InviaLog(p.getIpServer(), p.getPortaServer());
            Ricetta prepara = dr.getRicetta();
            prepara.CalcolaRicetta();
            cache = new CacheUltimaRicetta(dr.getNpizze(),dr.getPesoPizze(),dr.getIdro(),
                    dr.getSale(),dr.getOlio(),dr.getOre(),dr.getTamb(),prepara);
            System.out.println("chiusura : "+cache.toString());
            cache.scriviCache("cache.bin");
        });
        stage.setOnShown((WindowEvent we) -> {
            EventoNavigazioneUI  enui;
            enui = new EventoNavigazioneUI("calcolapizza",p.getIpClient(),
            getDataOraCorrenti(), TipoEvento.avvio);
            System.out.println(enui.toString());
            enui.InviaLog(p.getIpServer(), p.getPortaServer());
            CacheUltimaRicetta cache = CacheUltimaRicetta.ripristinaCache("cache.bin");
            System.out.println("apertura : "+cache.toString());
            Ricetta daCache = new Ricetta(cache.getRicettaCache());
            //ripristino cache 
            dr.setRicetta(daCache);
            gr.riempiGrafico(cache.getRicettaCache());
            tr.riempiTabella(daCache);
        });
    }
    
    public String getDataOraCorrenti(){ // funzione utilita per data ora correnti
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(new Date());
    }

}


