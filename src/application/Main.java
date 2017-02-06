package application;
	
import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.ImageCursor;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

public class Main extends Application {

	Stage okno;
	Scene scena;

    private Button przyciskWrog;
    public int punkty=0;
	private int czas=0;
    Label wyswietlPunkty;

	private static final Integer STARTTIME = 5;
	private Timeline timeliner;
	private Label wyswietlCzas = new Label("Czas: ");
	private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

	@Override
	public void start(Stage oknoGlowne) {

		okno = oknoGlowne;

		GridPane panelGlowny = new GridPane();
		panelGlowny.setPadding(new Insets(10,10,10,10));
		panelGlowny.setHgap(5);
		panelGlowny.setVgap(10);
		Pane panelGry = new Pane();
		Image wskaznik = new Image("file:celownik.gif");
		panelGlowny.setCursor(new ImageCursor(wskaznik, wskaznik.getWidth() / 2, wskaznik.getHeight() / 2));

        timeliner = new Timeline();
        timeliner.setAutoReverse( false );

		przyciskWrog = new Button("");
		przyciskWrog.setId("obrazekWrog");
		przyciskWrog.setVisible(false);
		przyciskWrog.setOnAction((ActionEvent e) -> {
			//przyciskWrog.setVisible(true);
			zaktualizujPunkt();
			nacisnieciePrzyciskuWrog(przyciskWrog);
		});

		Button przyciskWynikowy = new Button("WYNIK");
		przyciskWynikowy.setOnAction(e -> {
		boolean result = Wynik.wyswietlWynik("Gratulacje","Osiąglnąłeś", punkty, "Powrót", "Zamknij");
		System.out.print(result);
		});
		przyciskWynikowy.setVisible(false);

		Button przyciskGry = new Button("Nowa Gra");
		przyciskGry.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (timeliner != null) {
					timeliner.stop();
				}
				timeSeconds.set(STARTTIME);

				timeliner.getKeyFrames().add(
						new KeyFrame(Duration.seconds(STARTTIME+1),
								new KeyValue(timeSeconds, 0)));
				timeliner.playFromStart();
				zmienNaLosowaPozycje(przyciskWrog);
				przyciskWrog.setVisible(true);
				przyciskGry.setVisible(false);
				przyciskWynikowy.setVisible(false);
			}
		});

		timeliner.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				przyciskWrog.setVisible(false);
				przyciskGry.setVisible(true);
				przyciskWynikowy.setVisible(true);
			}

		});

		wyswietlCzas.setId("time");
		wyswietlCzas.textProperty().bind(timeSeconds.asString());
		wyswietlCzas.setTextFill(Color.RED);

		wyswietlPunkty = new Label("Punkty: " + punkty);
		wyswietlPunkty.setId("points");


		GridPane panelMenu = new GridPane();
		panelMenu.setPadding(new Insets(2,10,10,10));
		panelMenu.setHgap(10);
		panelMenu.setVgap(10);
		GridPane.setConstraints(wyswietlPunkty,0,0);
		GridPane.setConstraints(przyciskGry,35,0);
		GridPane.setConstraints(wyswietlCzas,75,0);
		GridPane.setConstraints(przyciskWynikowy,35,5);
		GridPane.setConstraints(panelGry,0,2);
		panelMenu.getChildren().addAll(wyswietlPunkty, przyciskGry, przyciskWynikowy, wyswietlCzas);
		panelGry.getChildren().addAll(przyciskWrog);
		panelGlowny.getChildren().addAll(panelMenu,panelGry);

		scena = new Scene(panelGlowny, 1024, 758);
		okno.getIcons().add(new Image("file:icon.jpg"));
		okno.setTitle("Alley of Death");
		okno.setResizable(false);
		panelGlowny.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
		okno.setScene(scena);
		okno.show();
	}

	public void nacisnieciePrzyciskuWrog(Button przyciskWrog) {
		przyciskWrog.setId("obrazekWrogWybuch");
		Timeline timeline = new Timeline(new KeyFrame(
				javafx.util.Duration.seconds(0.25),new EventHandler() {
			@Override
			public void handle(Event event) {
				przyciskWrog.setId("obrazekWrog");
			}
		}));
		timeline.play();
	}

	private void zmienNaLosowaPozycje(Button przyciskWrog) {

		if (STARTTIME==61){
		} else {
	    Pozycja nastepnaPozycja = pobierzLosowaPozycje();
        KeyValue kx = new KeyValue( przyciskWrog.layoutXProperty(), nastepnaPozycja.x );
        KeyValue ky = new KeyValue( przyciskWrog.layoutYProperty(), nastepnaPozycja.y );
		Timeline timeline = new Timeline();
        timeline.setDelay(javafx.util.Duration.seconds(0.7));
        timeline.stop();
        timeline.getKeyFrames().clear();
        timeline.getKeyFrames().add( new KeyFrame(javafx.util.Duration.seconds(0.01),
                ( e ) -> zmienNaLosowaPozycje(przyciskWrog), kx, ky) );
        timeline.play();
		}
    }

	private Pozycja pobierzLosowaPozycje()
    {
        Random rand = new Random();
        int xWartoscLosowa = rand.nextInt(724) + 150;
        int yWartoscLosowa = rand.nextInt(404) + 102;

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
	private void zaktualizujPunkt() {
		punkty = punkty+1;
		wyswietlPunkty.setText("Punkty: " + punkty);
	}
	public static void main(String[] args) {
		launch(args);

	}
}