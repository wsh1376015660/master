package domain;

import javafx.beans.property.SimpleStringProperty;

public class Score {
    private final SimpleStringProperty stuID;
    private final SimpleStringProperty cID;
    private final SimpleStringProperty score;
    private final SimpleStringProperty credit;

    public Score(String stuID, String cID, String score, String credit) {
        this.stuID = new SimpleStringProperty(stuID);
        this.cID = new SimpleStringProperty(cID);
        this.score = new SimpleStringProperty(score);
        this.credit = new SimpleStringProperty(credit);
    }

    public String getStuID() {
        return this.stuID.get();
    }

    public SimpleStringProperty stuIDProperty() {
        return this.stuID;
    }

    public void setStuID(String stuID) {
        this.stuID.set(stuID);
    }

    public String getcID() {
        return this.cID.get();
    }

    public SimpleStringProperty cIDProperty() {
        return this.cID;
    }

    public void setcID(String cID) {
        this.cID.set(cID);
    }

    public String getScore() {
        return this.score.get();
    }

    public SimpleStringProperty scoreProperty() {
        return this.score;
    }

    public void setScore(String score) {
        this.score.set(score);
    }

    public String getCredit() {
        return this.credit.get();
    }

    public SimpleStringProperty creditProperty() {
        return this.credit;
    }

    public void setCredit(String credit) {
        this.credit.set(credit);
    }

    public String toString() {
        return "Score{stuID=" + this.stuID + ", cID=" + this.cID + ", score=" + this.score + ", credit=" + this.credit + '}';
    }
}
