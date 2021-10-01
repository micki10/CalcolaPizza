import javafx.beans.value.*;
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
public class DatiRicetta { // classe che contiene gli elementi grafici della form di input dati utente
    GridPane gp;
    Label npizze_label;
    Label peso_label;
    Label idro_label;
    Label sale_label;
    Label olio_label;
    Label ore_label;
    Label tamb_label;
    TextField npizze_tf;
    TextField peso_tf;
    Slider idro_slider;
    Slider sale_slider;
    Slider olio_slider;
    TextField ore_tf;
    Slider tamb_slider;
    Button Calcola;
    Button Pulisci;
    
    Label idro_valore;
    Label sale_valore;
    Label olio_valore;
    Label tamb_valore;
            
    public DatiRicetta(){ // inizializzz le label e gli altri oggetti grafici
        npizze_label = new Label("Quante pizze ?");
        peso_label = new Label("Peso [gr]");
        idro_label = new Label("% Acqua [su farina]");
        sale_label = new Label("Sale [gr su kg farina]");
        olio_label = new Label("Olio [gr su kg farina]");
        ore_label = new Label("Ore Lievitazione");
        tamb_label = new Label("T. Ambiente");
        
        npizze_tf = new TextField();
        peso_tf = new TextField();
        idro_slider = new Slider(40,100,0);
        sale_slider = new Slider(0,30,0);
        olio_slider = new Slider(0,80,0);
        ore_tf = new TextField();
        tamb_slider = new Slider(18,40,0);
        Calcola = new Button("Calcola");
        Pulisci = new Button("Pulisci");
        gp = new GridPane();
        
        idro_valore = new Label(Integer.toString((int) idro_slider.getValue())+" %");
        sale_valore = new Label(Integer.toString((int) sale_slider.getValue())+" gr");
        olio_valore = new Label(Integer.toString((int) olio_slider.getValue())+" gr");
        tamb_valore = new Label(Integer.toString((int) tamb_slider.getValue()) +" °C");
    }
    
    public void setNpizze(int v){
        npizze_tf.setText(Integer.toString(v));
    }
    public void setPesoPizze(int v){
        peso_tf.setText(Integer.toString(v));
    }
    public void setOre(int v){
        ore_tf.setText(Integer.toString(v));
    }
    public void setColoreBottoni(String colore){
        Calcola.setStyle("-fx-background-color:"+colore+";");
        Pulisci.setStyle("-fx-background-color:"+colore+";");
    }
    public void setSliderIdro(int valore){
        idro_slider.valueProperty().addListener((obs, oldval, newVal) -> idro_slider.setValue(newVal.intValue()));
        idro_slider.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            idro_valore.setText(String.format("%d%%", newValue.intValue()));
        });
        idro_slider.setValue(valore);
    }
    public void setSliderSale(int valore){
        sale_slider.valueProperty().addListener((obs, oldval, newVal) -> sale_slider.setValue(newVal.intValue())); //conversione del range da double a int
        sale_slider.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            sale_valore.setText(String.format("%d", newValue.intValue())+" gr");
        });
        sale_slider.setValue(valore);
    }
    public void setSliderOlio(int valore){
        olio_slider.valueProperty().addListener((obs, oldval, newVal) -> olio_slider.setValue(newVal.intValue()));
        olio_slider.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            olio_valore.setText(String.format("%d", newValue.intValue())+" gr");
        });
        olio_slider.setValue(valore);
    }
    
    public void setSliderTemperatura(int valore){
        tamb_slider.valueProperty().addListener((obs, oldval, newVal) -> tamb_slider.setValue(newVal.intValue()));
        tamb_slider.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            tamb_valore.setText(String.format("%d", newValue.intValue())+" °C");
        });
        tamb_slider.setValue(valore);
    }
    
    public void setBackgroundColor(String c){ // metodi che serviranno per l'applicazione dei parametri xml
        gp.setStyle("-fx-background-color: "+c+";");
    }
    
    public GridPane DisegnaDati(){ // inserisce e incolonna solo gli oggetti nel gridpane, no stile
        HBox bottoni = new HBox(Calcola,Pulisci);
        bottoni.setAlignment(Pos.CENTER);
        bottoni.setPadding(new Insets(5, 5, 5, 5)); // padding
        bottoni.setSpacing(10);
        gp.getColumnConstraints().add(new ColumnConstraints(200));
        gp.getColumnConstraints().add(new ColumnConstraints(200));
        
        gp.add(npizze_label, 0,0,1,1);  //incolonna gli oggetti nella gridpane e la restituisce
        gp.add(npizze_tf, 1,0,1,1);
        
        gp.add(peso_label,0,1,1,1);
        gp.add(peso_tf,1,1,1,1);
        
        gp.add(idro_label,0,2,1,1);
        gp.add(idro_slider,1,2,1,1);
        gp.add(idro_valore,2,2,1,1);
        
        gp.add(sale_label,0,3,1,1);
        gp.add(sale_slider,1,3,1,1);
        gp.add(sale_valore,2,3,1,1);
        
        gp.add(olio_label,0,4,1,1);
        gp.add(olio_slider,1,4,1,1); 
        gp.add(olio_valore,2,4,1,1);
        
        gp.add(ore_label,0,5,1,1);
        gp.add(ore_tf,1,5,1,1);
        
        gp.add(tamb_label,0,6,1,1);
        gp.add(tamb_slider,1,6,1,1);
        gp.add(tamb_valore,2,6,1,1);

        gp.add(bottoni, 0, 7, 2, 1);

        gp.setHgap(10);
        gp.setVgap(10);
        return gp;
    }
    
    public void setRicetta(Ricetta r){ // a partire da una ricetta setta i campi di input.
        this.setNpizze(r.getNPizze());
        this.setPesoPizze(r.getPesoPizza());
        this.setSliderIdro(r.getPercAcqua());
        this.setSliderSale(r.getGrSale());
        this.setSliderOlio(r.getGrOlio());
        this.setOre(r.getOreLievitazione());
        this.setSliderTemperatura(r.getTAmbiente());
    }
    public int getNpizze(){
        return Integer.parseInt(npizze_tf.getText());
    }
    public int getPesoPizze(){
        return Integer.parseInt(peso_tf.getText());
    }
    public int getIdro(){
        return (int) idro_slider.getValue();
    }
   
    public int getSale(){
        return (int) sale_slider.getValue();
    }
    public int getOlio(){
        return (int) olio_slider.getValue();
    }
    public int getOre(){
        return Integer.parseInt(ore_tf.getText());
    }
    public int getTamb(){
        return (int) tamb_slider.getValue();
    } 
    public Ricetta getRicetta(){ // restituisce una Ricetta a partire dai campi input attuali
        return new Ricetta("ricetta_tmp", this.getNpizze(),this.getPesoPizze(),this.getIdro(),
                            this.getSale(),this.getOlio(),this.getOre(),this.getTamb());
    }
}
