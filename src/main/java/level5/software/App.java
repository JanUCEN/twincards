package level5.software;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {
    private GameBoard board;
    private List<Button> cards = new ArrayList<>();
    Card flipped1 = null;
    Card flipped2 = null;

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        double pairs = 6;

        GridPane grid = new GridPane();
        grid.setHgap(2);
        grid.setVgap(2);
        int rows = (int) Math.sqrt(pairs);
        int cols = (int) Math.ceil(pairs / rows);
        int cardWidth = 50;
        int cardHeight = 50;
        board = new GameBoard(pairs);
        cards = new ArrayList<>();

        for (Card card : board.getCards()) {
            Button btn = new Button("?");
            btn.setMinWidth(cardWidth - 2);
            btn.setMinHeight(cardHeight - 2);
            cards.add(btn);
            
            int index = card.getIndex();
            
            btn.setId(Integer.toString(index));
            btn.setOnAction(e -> checkCard(card, btn));
            grid.add(btn, index % cols, index / cols);
        }

        scene = new Scene(grid, cols * cardWidth, rows * (cardHeight * 2));
        stage.setScene(scene);
        stage.show();
    }

    private void checkCard(Card card, Button btn) {
        if(card.isFlipped()) {
            return;
        }
        card.flip();

        if(flipped1 == null) {
            btn.setText(card.getValue());
            flipped1 = card;
            return;
        }
        if(flipped2 == null) {
            btn.setText(card.getValue());
            flipped2 = card;
        }
        System.out.println(flipped1.getValue());
        System.out.println(flipped2.getValue());
        if(flipped1.getValue().equals(flipped2.getValue())) {
            flipped1 = null;
            flipped2 = null;
            return;
        } 
        flipped1.flip();
        flipped2.flip();
        btn.setText("?");
        cards.get(flipped1.getIndex()).setText("?");
        flipped1 = null;
        flipped2 = null;
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}