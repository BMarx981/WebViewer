package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
	WebEngine engine = new WebEngine();
	Server server = new Server();
	
	ArrayList<String> elements = new ArrayList<String>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		engine = webView.getEngine();
		engine.setJavaScriptEnabled(true);
	}
	
	public void searchFieldEntered() {
		String text = searchField.getText();
		engine.load(text);
//		System.out.println(text);
		engine.getLoadWorker().stateProperty().addListener(
				new ChangeListener<State>() {
					public void changed(ObservableValue ov, State oldState, State newState) {
						if (newState == Worker.State.SUCCEEDED) {
							System.out.println("It succeeded");
						}
					}
				});
	}
	
	public void searchButtonPressed() {
//		server.connectToServer(9991);
//		engine.load("http://localhost:8080/");
		engine.load("http://www.google.com/");
//		findElementsInDoc();
	}
	
	private void findElementsInDoc() {
		 Document doc = engine.getDocument();
		 NodeList nodeList = doc.getElementsByTagName("form");
		 for (int i = 0; i < nodeList.getLength(); i++) {
			 Node item = nodeList.item(i);
			 String name = item.getNodeName();
			 String value = item.getLocalName();
			 item.getAttributes();
			 System.out.println("Name = " + name + "\nValue = " + value);
		 }
		 System.out.println("There are " + nodeList.getLength() + " nodes");
	}

}
