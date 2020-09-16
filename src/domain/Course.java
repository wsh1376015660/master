package domain;

import javafx.beans.property.SimpleStringProperty;      //使用SimpleStringProperty类建立视图层与java类的双向监听

public class Course {
    private final SimpleStringProperty cID;
    private final SimpleStringProperty cMajor;
    private final SimpleStringProperty cName;
    private final SimpleStringProperty cType;
    private final SimpleStringProperty cStartTerm;
    private final SimpleStringProperty cPeriod;
    private final SimpleStringProperty cCredit;

    public Course(String cID, String cMajor, String cName, String cType, String cStartTerm, String cPeriod, String cCredit) {
        this.cID = new SimpleStringProperty(cID);
        this.cMajor = new SimpleStringProperty(cMajor);
        this.cName = new SimpleStringProperty(cName);
        this.cType = new SimpleStringProperty(cType);
        this.cStartTerm = new SimpleStringProperty(cStartTerm);
        this.cPeriod = new SimpleStringProperty(cPeriod);
        this.cCredit = new SimpleStringProperty(cCredit);
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

    public String getcMajor() {
        return this.cMajor.get();
    }

    public SimpleStringProperty cMajorProperty() {
        return this.cMajor;
    }

    public void setcMajor(String cMajor) {
        this.cMajor.set(cMajor);
    }

    public String getcName() {
        return this.cName.get();
    }

    public SimpleStringProperty cNameProperty() {
        return this.cName;
    }

    public void setcName(String cName) {
        this.cName.set(cName);
    }

    public String getcType() {
        return this.cType.get();
    }

    public SimpleStringProperty cTypeProperty() {
        return this.cType;
    }

    public void setcType(String cType) {
        this.cType.set(cType);
    }

    public String getcStartTerm() {
        return this.cStartTerm.get();
    }

    public SimpleStringProperty cStartTermProperty() {
        return this.cStartTerm;
    }

    public void setcStartTerm(String cStartTerm) {
        this.cStartTerm.set(cStartTerm);
    }

    public String getcPeriod() {
        return this.cPeriod.get();
    }

    public SimpleStringProperty cPeriodProperty() {
        return this.cPeriod;
    }

    public void setcPeriod(String cPeriod) {
        this.cPeriod.set(cPeriod);
    }

    public String getcCredit() {
        return this.cCredit.get();
    }

    public SimpleStringProperty cCreditProperty() {
        return this.cCredit;
    }

    public void setcCredit(String cCredit) {
        this.cCredit.set(cCredit);
    }
}
