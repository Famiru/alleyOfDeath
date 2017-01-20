package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Wynik{
	
	static boolean przelaczDoMenu;
	
	public static boolean wyswietlWynik(String tytul, String opisWyniku, String przyciskMenu, String przyciskZamkniecia){
		
		Stage okno = new Stage();
		okno.initModality(Modality.APPLICATION_MODAL);
		okno.setTitle(tytul);
		okno.setMinWidth(200);
		Label wiadomoscWynikowa = new Label();
		wiadomoscWynikowa.setText(opisWyniku);
		
		Button zamknijOkno = new Button(przyciskZamkniecia);
		Button idzDoMenu = new Button(przyciskMenu);
		
		zamknijOkno.setOnAction(e ->{
			przelaczDoMenu = false;
			okno.close();
			System.exit(0);
		});
		idzDoMenu.setOnAction(e ->{
			przelaczDoMenu = true;
			okno.close();
		});
		
		VBox wyswietlRezultat = new VBox();
		wyswietlRezultat.getChildren().addAll(wiadomoscWynikowa,idzDoMenu,zamknijOkno);
		wyswietlRezultat.setAlignment(Pos.CENTER);
		Scene wyswietlanie = new Scene(wyswietlRezultat);
		okno.setScene(wyswietlanie);
		okno.showAndWait();
		
		return przelaczDoMenu;
	}
}
