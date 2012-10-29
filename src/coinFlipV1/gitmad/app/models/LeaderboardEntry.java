package coinFlipV1.gitmad.app.models;

import java.util.Date;

/**
 * 
 */
public class LeaderboardEntry implements Comparable {
    private int length;
    private long id;
    private String name;
    private Date date;

    public LeaderboardEntry() {}

    public LeaderboardEntry(int length, String name, Date date) {
        this.length = length;
        this.name = name;
        this.date = date;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return name + ": " + length;
    }

    @Override
    public int compareTo(Object o) {
        if ((o != null) && !(o instanceof LeaderboardEntry)) {
            return -1;
        }

        LeaderboardEntry temp = (LeaderboardEntry) o;

        if (this.length > temp.getLength()) {
            return 1;
        } else if (this.length > temp.getLength()) {
            return -1;
        }

        if (this.date.compareTo(temp.getDate()) == 1) {
            return 1;
        } else if (this.date.compareTo(temp.getDate()) == -1) {
            return -1;
        }

        return 0;
    }
}
