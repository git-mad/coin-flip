package coinFlipV1.gitmad.app.models;

public class Streak {
    private boolean type;
    private int length;

    public Streak() {
        this.type = false;
        this.length = 1;
    }

    public Streak(boolean type) {
        this.type = type;
    }

    public Streak(boolean type, int length) {
        this.type = type;
        this.length = length;
    }

    public void incrementLength() {
        length++;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
