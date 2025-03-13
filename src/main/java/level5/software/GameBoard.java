package level5.software;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameBoard {
    private final List<Card> cards;

    public GameBoard(double numberOfPairs) {
        cards = new ArrayList<>();
        generateCards(numberOfPairs);
    }

    private void generateCards(double numberOfPairs) {
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < numberOfPairs; i++) {
            temp.add("C " + (i+1));
            temp.add("C " + (i+1)); //put two of each card in. 
        }

        Collections.shuffle(temp);

        for(int i = 0; i< temp.size(); i++) {
            cards.add(new Card(i, temp.get(i)));
        }

    }

    public List<Card> getCards() {
        return cards;
    }
}
