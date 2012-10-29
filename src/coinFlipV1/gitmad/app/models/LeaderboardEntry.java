package coinFlipV1.gitmad.app.models;

import java.util.Date;

/**
 * 
 */
public class LeaderboardEntry {
    private int streakLength;
    private int id;
    private String name;
    private Date date;

    public LeaderboardEntry(int streakLength, String name, Date date) {
        this.streakLength = streakLength;
        this.name = name;
        this.date = date;
    }
    public int getStreakLength() {
        return streakLength;
    }

    public void setStreakLength(int streakLength) {
        this.streakLength = streakLength;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
