import javafx.collections.*;
import javafx.geometry.Side;
import javafx.scene.chart.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Michele
 */

public class GraficoRicetta extends PieChart{ // estende perchè il costruttore restituisce un piechart e sono aggiunti metodi propri
    PieChart graficoIngredienti;
    ObservableList<PieChart.Data> ingredientiGrafico;
    public GraficoRicetta(){
        super();
        this.setLegendSide(Side.LEFT);
        this.setLabelsVisible(false);
        this.setLabelLineLength(10);
        this.setStartAngle(180);
       ingredientiGrafico = FXCollections.observableArrayList(  //percentuali di default
               new PieChart.Data("Farina", 60),
               new PieChart.Data("Acqua", 35),
               new PieChart.Data("Sale", 2),
               new PieChart.Data("Olio", 2),
               new PieChart.Data("Lievito",1)
       );
        this.setData(ingredientiGrafico);
    }

    public void riempiGrafico(Ricetta r){ //aggiorna le percentuali dalla Ricetta data
        double totale=r.getAcqua()+r.getFarina()+r.getOlio()+r.getSale()+r.getLievito();
                aggiungiIngrediente("Farina", ((r.getFarina()/totale)*100));
                aggiungiIngrediente("Acqua", ((r.getAcqua()/totale)*100));
                aggiungiIngrediente("Sale", ((r.getSale()/totale)*100));
                aggiungiIngrediente("Olio",((r.getOlio()/totale)*100));
                aggiungiIngrediente("Lievito", ((r.getLievito()/totale)*100));
    }
    
    public void aggiungiIngrediente(String nome, double valore){ //in base all'indice dell Ob.List determina di che ingrediente si tratta
        int index=0;
        switch (nome){
            case "Farina":
                index = 0;
                break;
            case "Acqua":
                index = 1;
                break;
            case "Sale":
                index = 2;
                break;
            case "Olio":
                index = 3;
                break;
            case "Lievito":
                index = 4;
                break;
        }
        PieChart.Data p = ingredientiGrafico.get(index);
        p.setPieValue(valore);
    }
}
