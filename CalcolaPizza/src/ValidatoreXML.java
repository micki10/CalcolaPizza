import javax.xml.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;
import org.xml.sax.*;
import javax.xml.validation.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Michele
 */
/**classe di utilità che viene utilizzata in diverse occasioni per la validazione di un 
    file XML contro un file XML Schema Definition */

public class ValidatoreXML {    
    public static boolean ValidaXML(String path_xml, String path_xsd){ //i parametri sono i path dei file
        boolean valido = true;
        try {  
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Document d = db.parse(new File(path_xml));
            Schema s = sf.newSchema(new StreamSource(new File(path_xsd)));
            s.newValidator().validate(new DOMSource(d));
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            if (e instanceof SAXException){ 
                System.out.println("Errore di validazione: " + e.getMessage());
                valido=false;
            }
            else
                System.out.println(e.getMessage());    
        }
        return valido;
    }
}