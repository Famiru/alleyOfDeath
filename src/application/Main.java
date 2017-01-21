package application;
	
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.image.*;
import javafx.scene.ImageCursor;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.time.Duration;
import java.util.Random;

public class Main extends Application {

	Stage okno;
	Scene menu;
    private Timeline timeline;
    private Button przyciskWrog;




	@Override
	public void start(Stage oknoGlowne) {

		okno = oknoGlowne;

		GridPane panelGlowny = new GridPane();
		panelGlowny.setHgap(5);
		panelGlowny.setVgap(10);
		GridPane panelMenu = new GridPane();
		panelMenu.setHgap(1);
		panelMenu.setVgap(2);
		Pane panelGry = new Pane();

		Image wskaznik = new Image("file:celownik.gif");
		panelGlowny.setCursor(new ImageCursor(wskaznik, wskaznik.getWidth() / 2, wskaznik.getHeight() / 2));

        timeline = new Timeline();
        timeline.setCycleCount( Timeline.INDEFINITE );
        timeline.setAutoReverse( true );

        //Functionality And Display poops
		Button przyciskGry = new Button("Nowa Gra");

		przyciskWrog = new Button("");
		przyciskWrog.setId("obrazekWrog");
		przyciskWrog.setOnAction((ActionEvent e) -> {
			nacisnieciePrzyciskuWrog(przyciskWrog);
		});
		przyciskGry.setOnAction(e -> {
			zmienNaLosowaPozycje(przyciskWrog);
		});

		Label wyswietlZycie = new Label("Zycie: " + "liczbaZyc");
		Label wyswietlPunkty = new Label("Points: " + "liczbaPunktow");
		wyswietlZycie.setId("hp");
		wyswietlPunkty.setId("points");
		panelMenu.add(wyswietlZycie,0,0);
		panelMenu.add(wyswietlPunkty,5,0);
		panelMenu.getChildren().addAll(wyswietlZycie,wyswietlPunkty);
		panelGry.getChildren().add(przyciskWrog);
		panelGlowny.add(panelGry,0,2);
		panelGlowny.getChildren().addAll(panelMenu, panelGry);

		//



		menu = new Scene(panelGlowny, 1024, 758);
		okno.getIcons().add(new Image("file:icon.jpg"));
		okno.setTitle("Alley of Death");
		okno.setResizable(false);
		panelGlowny.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
		okno.setScene(menu);
		okno.show();

	}


	public void nacisnieciePrzyciskuWrog(Button przyciskWrog) {
		przyciskWrog.setId("obrazekWrogWybuch");
		Timeline timeline = new Timeline(new KeyFrame(
				javafx.util.Duration.seconds(0.25),new EventHandler() {
			@Override
			public void handle(Event event) {
				zmienNaLosowaPozycje(przyciskWrog);
				przyciskWrog.setId("obrazekWrog");
			}
		}));
		timeline.play();
	}
	private void zmienNaLosowaPozycje(Button przyciskWrog) {
	    Pozycja nastepnaPozycja = pobierzLosowaPozycje();
        KeyValue kx = new KeyValue( przyciskWrog.layoutXProperty(), nastepnaPozycja.x );
        KeyValue ky = new KeyValue( przyciskWrog.layoutYProperty(), nastepnaPozycja.y );
        timeline.setDelay(javafx.util.Duration.seconds(0.7));
        timeline.stop();
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add( new KeyFrame(javafx.util.Duration.seconds(0.01),
                ( e ) -> zmienNaLosowaPozycje(przyciskWrog), kx, ky) );
        timeline.play();
    }
	private Pozycja pobierzLosowaPozycje()
    {
        Random rand = new Random();
        int xWartoscLosowa = rand.nextInt(724) + 150;
        int yWartoscLosowa = rand.nextInt(554) + 102;

        Pozycja pozycja = new Pozycja(xWartoscLosowa,yWartoscLosowa);
        return pozycja;
    }
    private class Pozycja
    {
        int x;
        int y;
        public Pozycja(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

	public static void main(String[] args) {
		launch(args);

	}
}

//		Button przyciskWyjscia = new Button("Exit");
//		Button przyciskWynikKoncowyWygrana = new Button("Wynik Koncowy Wygrana");
//		Button przyciskWynikKoncowyPrzegrana = new Button("Wynik Koncowy Przegrana");
//		przyciskWyjscia.setOnAction(e -> System.exit(0));
//		przyciskWynikKoncowyWygrana.setOnAction(e -> {
//			Wynik.wyswietlWynik("Gratulacje", "Wygrałeś", "Menu", "Wyjście");
//		});
//		przyciskWynikKoncowyPrzegrana.setOnAction(e -> {
//			Wynik.wyswietlWynik("Niestety", "Przegrałeś", "Menu", "Wyjście");
//		});