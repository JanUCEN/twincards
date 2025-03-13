package level5.software;

public class Card {
    private final int index;
    private final String value;
    private boolean showing = false;
    
    public Card(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }

    public void flip() {
        showing = !showing;
    }

    public boolean isFlipped() {
        return showing;
    }
}
