package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	Stage okno;
	Scene menu;
	Scene historia;
	Scene gra;
	
	@Override
	public void start(Stage oknoGlowne) {
		
		okno = oknoGlowne;
		
		Label intro = new Label("Mroczny �wiat pe�ny z�a, czy dzi� przertwam?");
		Button przyciskWstep = new Button("Nowa gra");
		Button przyciskGry = new Button("OGIE�!");
		Button przyciskMenu = new Button("Powr�t");
		Button przyciskPoziomu = new Button("LvL");
		Button przyciskWyjscia = new Button("Exit");
		Button przyciskWynikKoncowyWygrana = new Button("Wynik Koncowy Wygrana");
		Button przyciskWynikKoncowyPrzegrana = new Button("Wynik Koncowy Przegrana");
		przyciskWstep.setOnAction(e -> okno.setScene(historia));
		przyciskGry.setOnAction(e -> okno.setScene(gra));
		przyciskMenu.setOnAction(e -> okno.setScene(menu));
		przyciskPoziomu.setOnAction(e -> System.exit(0));
		przyciskWyjscia.setOnAction(e -> System.exit(0));
		przyciskWynikKoncowyWygrana.setOnAction(e ->{
			Wynik.wyswietlWynik("Gratulacje","Wygra�e�","Menu","Wyj�cie");
			okno.setScene(menu);
		});
		przyciskWynikKoncowyPrzegrana.setOnAction(e -> {
			Wynik.wyswietlWynik("Niestety","Przegra�e�","Menu","Wyj�cie");
			okno.setScene(menu);
		});
		
		//Wyglad Menu
		VBox panelGlowny = new VBox();
		panelGlowny.getChildren().addAll(intro, przyciskWstep, przyciskGry, przyciskMenu);
		menu = new Scene(panelGlowny,1024,768);
		
		//Wygl�d Historii
		StackPane panelHistorii = new StackPane();
		panelHistorii.getChildren().add(przyciskGry);
		panelHistorii.getChildren().add(przyciskMenu);
		historia = new Scene(panelHistorii,1024,768);
		
		//Wyglad Gry
		VBox panelGry = new VBox();
		panelGry.getChildren().add(przyciskWyjscia);
		panelGry.getChildren().add(przyciskMenu);
		panelGry.getChildren().add(przyciskWynikKoncowyWygrana);
		panelGry.getChildren().add(przyciskWynikKoncowyPrzegrana);
		gra = new Scene(panelGry,1024,768);
		
		//Domyslne
		okno.getIcons().add(new Image("file:icon.jpg"));
		okno.setTitle("Alley of Death");
		okno.setResizable(false);
		okno.setScene(menu);
		okno.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
