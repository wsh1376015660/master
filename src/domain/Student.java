package domain;

import javafx.beans.property.SimpleStringProperty;

public class Student {
    private final SimpleStringProperty stuID;
    private final SimpleStringProperty stuClass;
    private final SimpleStringProperty stuName;
    private final SimpleStringProperty stuSex;
    private final SimpleStringProperty stuBirth;
    private final SimpleStringProperty stuMajor;

    public Student(String stuID, String studClass, String stuName, String stuSex, String stuBirth, String stuMajor) {
        this.stuID = new SimpleStringProperty(stuID);
        this.stuClass = new SimpleStringProperty(studClass);
        this.stuName = new SimpleStringProperty(stuName);
        this.stuSex = new SimpleStringProperty(stuSex);
        this.stuBirth = new SimpleStringProperty(stuBirth);
        this.stuMajor = new SimpleStringProperty(stuMajor);
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

    public String getStuClass() {
        return this.stuClass.get();
    }

    public SimpleStringProperty stuClassProperty() {
        return this.stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass.set(stuClass);
    }

    public String getStuName() {
        return this.stuName.get();
    }

    public SimpleStringProperty stuNameProperty() {
        return this.stuName;
    }

    public void setStuName(String stuName) {
        this.stuName.set(stuName);
    }

    public String getStuSex() {
        return this.stuSex.get();
    }

    public SimpleStringProperty stuSexProperty() {
        return this.stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex.set(stuSex);
    }

    public String getStuBirth() {
        return this.stuBirth.get();
    }

    public SimpleStringProperty stuBirthProperty() {
        return this.stuBirth;
    }

    public void setStuBirth(String stuBirth) {
        this.stuBirth.set(stuBirth);
    }

    public String getStuMajor() {
        return this.stuMajor.get();
    }

    public SimpleStringProperty stuMajorProperty() {
        return this.stuMajor;
    }

    public void setStuMajor(String stuMajor) {
        this.stuMajor.set(stuMajor);
    }

    public String toString() {
        return "Student{stuID=" + this.stuID + ", stuClass=" + this.stuClass + ", stuName=" + this.stuName + ", stuSex=" + this.stuSex + ", stuBirth=" + this.stuBirth + ", stuMajor=" + this.stuMajor + '}';
    }
}
