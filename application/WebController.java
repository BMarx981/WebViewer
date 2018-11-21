package application;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class WebController implements Initializable {

	@FXML WebView webView = new WebView();
	@FXML TextField searchField = new TextField();
	@FXML Button search = new Button("Search");
	@FXML Button reload = new Button();
	String editor;
	WebEngine engine;
	boolean fieldsSet;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		engine = webView.getEngine();

		
	}

	public void searchFieldEntered() {
		String text = searchField.getText();
		engine.load(text);
	}
	
	public void searchButtonPressed() {
		engine.getLoadWorker().stateProperty().addListener(
	            new ChangeListener<State>() {
	                public void changed(ObservableValue ov, State oldState, State newState) {
	                    if (newState == Worker.State.SUCCEEDED) {
	                    	String html = (String) engine.executeScript("document.forms");
	                    	System.out.println(html);
//	                        Document doc = engine.getDocument();
//	                        try {
//	                            Transformer transformer = TransformerFactory.newInstance().newTransformer();
//	                            System.out.println(transformer.getOutputProperties());
//	                            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
//	                            transformer.setOutputProperty(OutputKeys.METHOD, "html");
//	                            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//	                            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//	                            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
//
//	                            transformer.transform(new DOMSource(doc),
//	                                    new StreamResult(new OutputStreamWriter(System.out, "UTF-8")));
//	                        } catch (Exception ex) {
//	                            ex.printStackTrace();
//	                        }
	                    }
	                }
	            });
		engine.load("http://localhost:8080/");
		
	}
	
	public void reloadPressed() {
		engine.reload();
	}

}
//
//final WebView webView = new WebView();
//final WebEngine engine = webView.getEngine();
//engine.documentProperty().addListener(new ChangeListener<Document>() {
//  @Override public void changed(ObservableValue<? extends Document> ov, Document oldDoc, Document doc) {
//    if (doc != null && !loginAttempted.get()) {
//      if (doc.getElementsByTagName("form").getLength() > 0) {
//        HTMLFormElement form = (HTMLFormElement) doc.getElementsByTagName("form").item(0);
//        if ("/oam/server/sso/auth_cred_submit".equals(form.getAttribute("action"))) {
//          HTMLInputElement username = null;
//          HTMLInputElement password = null;
//          NodeList nodes = form.getElementsByTagName("input");
//          for (int i = 0; i < nodes.getLength(); i++) {
//            HTMLInputElement input = (HTMLInputElement) nodes.item(i);
//            switch (input.getName()) {
//              case "ssousername":
//                username = input;
//                break;
//              case "password":
//                password = input;
//                break;
//            }
//          }
