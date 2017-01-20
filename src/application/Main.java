package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Main extends Application {
	
	Stage okno;
	Scene menu;
	
	@Override
	public void start(Stage oknoGlowne) {
		
		okno = oknoGlowne;
		
		TextField podajWiek = new TextField();
		Label wyswietlIntro = new Label("Mroczny œwiat pe³ny z³a, czy dziœ przertwam?");
		Label wyswietlZycie = new Label("Zycie: "+"liczbaZyc");
		Label wyswietlPunkty = new Label("Points: "+"liczbaPunktow");
		Button przyciskGry = new Button("Nowa gra");
		Button przyciskWyjscia = new Button("Exit");
		Button przyciskPotwiedzWiek = new Button("Potwierdz");
		Button przyciskWynikKoncowyWygrana = new Button("Wynik Koncowy Wygrana");
		Button przyciskWynikKoncowyPrzegrana = new Button("Wynik Koncowy Przegrana");
		przyciskGry.setOnAction(e -> okno.setScene(menu));
		przyciskWyjscia.setOnAction(e -> System.exit(0));
		przyciskPotwiedzWiek.setOnAction(e->ograniczenieWiekowe(podajWiek, podajWiek.getText()));
		przyciskWynikKoncowyWygrana.setOnAction(e ->{
			Wynik.wyswietlWynik("Gratulacje","Wygra³eœ","Menu","Wyjœcie");
			okno.setScene(menu);
		});
		przyciskWynikKoncowyPrzegrana.setOnAction(e -> {
			Wynik.wyswietlWynik("Niestety","Przegra³eœ","Menu","Wyjœcie");
			okno.setScene(menu);
		});
		
		GridPane panelGlowny = new GridPane();
		panelGlowny.setPadding(new Insets(10, 10, 10, 10));
		panelGlowny.setVgap(10);
		panelGlowny.setHgap(10);
			
		GridPane.setConstraints(przyciskGry, 7,10);
		GridPane.setConstraints(wyswietlZycie, 0,1);
		GridPane.setConstraints(wyswietlPunkty, 0,2);
		GridPane.setConstraints(przyciskWyjscia, 25,0);
		GridPane.setConstraints(przyciskWynikKoncowyWygrana, 6,4);
		GridPane.setConstraints(przyciskWynikKoncowyPrzegrana, 8,4);
		panelGlowny.getChildren().addAll(przyciskGry, wyswietlZycie,wyswietlPunkty,przyciskWyjscia,przyciskWynikKoncowyWygrana,przyciskWynikKoncowyPrzegrana);
		menu = new Scene(panelGlowny,1024,768);

		//Domyslne
		okno.getIcons().add(new Image("file:icon.jpg"));
		okno.setTitle("Alley of Death");
		okno.setResizable(false);
		panelGlowny.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
		okno.setScene(menu);
		okno.show();	
		
		//TEST
		/*
		Obiekt wrog = new Obiekt();
		wrog.adultProperty().addListener((v,oldValue,newValue)->{
			System.out.println("Name Change to"+newValue);
			System.out.println("adult: "+wrog.adultProperty());
			System.out.println("imie: "+wrog.getAdult());
		});
		Button testowy = new Button("Slij");
		testowy.setOnAction(e->wrog.setAdult("Gummy"));
		
		Button przyciskWrog = new Button("COMEON");
		GridPane.setConstraints(przyciskWrog, 9,9);
		przyciskWrog.setOnAction(e -> {setUserAgentStylesheet(STYLESHEET_CASPIAN);});
		*/
		//
	}
	
	
	private boolean ograniczenieWiekowe(TextField podajWiek, String wiadomoscPodajLiczbe) {
		try{
			int wiek = Integer.parseInt(podajWiek.getText());
			if (wiek<18)
			{	System.out.println("Jestes zbyt m³ody.");
				System.exit(0);
			}else{
				System.out.println("Mi³ej zabawy!");
			}
			System.out.println("Twoj wiek to: "+wiek);
			return true;
		}catch(NumberFormatException e){
			System.out.println("B³¹d "+ wiadomoscPodajLiczbe +" nie jest liczb¹");
			return false;
		}
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
