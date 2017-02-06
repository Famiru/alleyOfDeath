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
	
	public static void wyswietlWynik(int liczbaPunktow){
		
		Stage okno = new Stage();
		okno.initModality(Modality.APPLICATION_MODAL);
		okno.setTitle("Gratulacje");
		okno.setMinWidth(300);
		Label wiadomoscWynikowa = new Label("Osiągnąłeś: " + liczbaPunktow + " punktów");

		Button zamknijOkno = new Button("Zamknij");
		Button idzDoMenu = new Button("Menu");
		
		zamknijOkno.setOnAction(e ->{
			okno.close();
			System.exit(0);
		});
		idzDoMenu.setOnAction(e ->{
			okno.close();
		});
		
		VBox wyswietlRezultat = new VBox();
		wyswietlRezultat.getChildren().addAll(wiadomoscWynikowa,idzDoMenu,zamknijOkno);
		wyswietlRezultat.setAlignment(Pos.CENTER);
		Scene wyswietlanie = new Scene(wyswietlRezultat);
		okno.setScene(wyswietlanie);
		okno.showAndWait();
	}
}
