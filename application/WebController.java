package application;

import java.net.URL;
import java.util.ResourceBundle;

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
	WebEngine engine;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		engine = webView.getEngine();
		engine.setJavaScriptEnabled(true);
	}
	
	public void searchFieldEntered() {
		String text = searchField.getText();
		engine.load(text);
	}
	
	public void searchButtonPressed() {
		engine.load("http://localhost:8080/");
	}

}
