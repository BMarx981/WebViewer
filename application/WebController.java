package application;

import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
	WebEngine engine;
	boolean fieldsSet;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		engine = webView.getEngine();
		engine.setJavaScriptEnabled(true);
		engine.getLoadWorker().progressProperty().addListener((o, old, progress) -> updateFields());
		fieldsSet = false;
	}
	
	private void updateFields() {
		Document doc = engine.getDocument();
		if (doc != null && !fieldsSet) {
			try { 
				Element inputField = (Element) XPathFactory.newInstance().newXPath().evaluate("//*[@tag='input']", doc, XPathConstants.NODE);
				if (inputField != null) {
					NodeList list = engine.getDocument().getElementsByTagName("input");
					for (int i = 0; i < list.getLength(); i++) {
						System.out.println(list.item(i).toString());
					}
					inputField.setAttribute("value", "NEW TEXT");
					fieldsSet = true;
				}
			} catch (Exception e) {
				System.out.println("FAIL!!!");
			}
		}
	}
	
	public void searchFieldEntered() {
		String text = searchField.getText();
		engine.load(text);
	}
	
	public void searchButtonPressed() {
		engine.load("http://localhost:8080/");
		
	}
	
	public void reloadPressed() {
		engine.reload();
	}

}
