//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package controller;

import dao.impl.CourseDaoImpl;
import dao.impl.OtherDaoImpl;
import dao.impl.ScoreDaoImpl;
import dao.impl.StudentDaoImpl;
import domain.Course;
import domain.Score;
import domain.Student;
import java.awt.Font;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Controller implements Initializable {
    @FXML
    Tab tab_stu;
    @FXML
    Tab tab_course;
    @FXML
    Tab tab_score;
    @FXML
    TableView studentTableView;
    @FXML
    TableView courseTableView;
    @FXML
    TableView scoreTableView;
    @FXML
    TableColumn stuIDCol;
    @FXML
    TableColumn stuClassCol;
    @FXML
    TableColumn stuNameCol;
    @FXML
    TableColumn stuSexCol;
    @FXML
    TableColumn stuBirCol;
    @FXML
    TableColumn stuMajorCol;
    @FXML
    TableColumn cIdCol;
    @FXML
    TableColumn cMajorCol;
    @FXML
    TableColumn cNameCol;
    @FXML
    TableColumn cTypeCol;
    @FXML
    TableColumn cStartTremCol;
    @FXML
    TableColumn cPeriodCol;
    @FXML
    TableColumn cCreditCol;
    @FXML
    TableColumn score_sIdCol;
    @FXML
    TableColumn score_cIdCol;
    @FXML
    TableColumn scoreCol;
    @FXML
    TableColumn score_creditCol;

    public Controller() {
    }

    public void addStudent() {
        Dialog<Controller.StudentResults> dialog = new Dialog();
        dialog.setTitle("添加学生");
        dialog.setHeaderText("请在下面输入要添加的学生信息：");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(new ButtonType[]{ButtonType.OK, ButtonType.CANCEL});
        GridPane grid = new GridPane();
        grid.setHgap(10.0D);
        grid.setVgap(10.0D);
        grid.setPadding(new Insets(20.0D, 60.0D, 10.0D, 10.0D));
        TextField sID = new TextField();
        TextField sClass = new TextField();
        TextField sName = new TextField();
        TextField sSex = new TextField();
        TextField sBirth = new TextField();
        TextField sMajor = new TextField();
        grid.add(new Label("学号:"), 0, 0);
        grid.add(sID, 1, 0);
        grid.add(new Label("班级:"), 0, 1);
        grid.add(sClass, 1, 1);
        grid.add(new Label("姓名:"), 0, 2);
        grid.add(sName, 1, 2);
        grid.add(new Label("性别:"), 0, 3);
        grid.add(sSex, 1, 3);
        grid.add(new Label("出生日期:"), 0, 4);
        grid.add(sBirth, 1, 4);
        grid.add(new Label("所在专业:"), 0, 5);
        grid.add(sMajor, 1, 5);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter((button) -> {
            return button == ButtonType.OK ? new Controller.StudentResults(sID.getText(), sClass.getText(), sName.getText(), sSex.getText(), sBirth.getText(), sMajor.getText()) : null;
        });
        Optional<Controller.StudentResults> optionalResult = dialog.showAndWait();
        optionalResult.ifPresent((results) -> {
            Student student = (new StudentDaoImpl()).get(Integer.parseInt(results.sID));
            if (student != null) {
                this.alert("失败提示", "学号为【" + results.sID + "】的学生数据已经存在，无法添加！", (String)null, AlertType.ERROR);
            } else {
                (new StudentDaoImpl()).save(new Student(results.sID, results.sClass, results.sName, results.sSex, results.sBirth, results.sMajor));
                this.alert("成功提示", "成功保存学号为【" + results.sID + "】的学生数据！", (String)null, AlertType.INFORMATION);
                this.refreshStuTable();
            }

        });
    }

    public void changeStu() {
        TextInputDialog d = new TextInputDialog();
        d.setTitle("修改学生信息");
        d.setHeaderText("请输入要修改信息的学生学号：");
        d.setContentText("学号:");
        Optional<String> result = d.showAndWait();
        if (result.isPresent()) {
            if (this.checkIdIllegal((String)result.get())) {
                return;
            }

            Student student = (new StudentDaoImpl()).get(Integer.parseInt((String)result.get()));
            if (null != student) {
                Dialog<Controller.StudentResults> dialog = new Dialog();
                dialog.setTitle("学生数据");
                dialog.setHeaderText((String)null);
                DialogPane dialogPane = dialog.getDialogPane();
                dialogPane.getButtonTypes().addAll(new ButtonType[]{ButtonType.OK});
                GridPane grid = new GridPane();
                grid.setHgap(10.0D);
                grid.setVgap(10.0D);
                grid.setPadding(new Insets(20.0D, 60.0D, 10.0D, 10.0D));
                TextField sID = new TextField(student.getStuID());
                TextField sClass = new TextField(student.getStuClass());
                TextField sName = new TextField(student.getStuName());
                TextField sSex = new TextField(student.getStuSex());
                TextField sBirth = new TextField(student.getStuBirth());
                TextField sMajor = new TextField(student.getStuMajor());
                grid.add(new Label("学号:"), 0, 0);
                grid.add(sID, 1, 0);
                grid.add(new Label("班级:"), 0, 1);
                grid.add(sClass, 1, 1);
                grid.add(new Label("姓名:"), 0, 2);
                grid.add(sName, 1, 2);
                grid.add(new Label("性别:"), 0, 3);
                grid.add(sSex, 1, 3);
                grid.add(new Label("出生日期:"), 0, 4);
                grid.add(sBirth, 1, 4);
                grid.add(new Label("所在专业:"), 0, 5);
                grid.add(sMajor, 1, 5);
                dialog.getDialogPane().setContent(grid);
                Optional<Controller.StudentResults> results = dialog.showAndWait();
                if (results.isPresent()) {
                    Student stu = new Student(sID.getText(), sClass.getText(), sName.getText(), sSex.getText(), sBirth.getText(), sMajor.getText());
                    (new StudentDaoImpl()).update(Integer.parseInt((String)result.get()), stu);
                    this.alert("成功提示", "成功修改学号为【" + student.getStuID() + "】的学生数据！", (String)null, AlertType.INFORMATION);
                    this.refreshStuTable();
                }
            } else {
                this.alert("错误提示", "没有该学生的记录，无法修改！", (String)null, AlertType.ERROR);
            }
        }

    }

    public void deleteStu() {
        TextInputDialog d = new TextInputDialog();
        d.setTitle("删除学生");
        d.setHeaderText("请输入要删除的学生学号：");
        d.setContentText("学号:");
        Optional<String> result = d.showAndWait();
        if (result.isPresent()) {
            if (this.checkIdIllegal((String)result.get())) {
                return;
            }

            Student student = (new StudentDaoImpl()).get(Integer.parseInt((String)result.get()));
            if (null != student) {
                System.out.println("删除学号为" + student.getStuID() + "的数据");
                (new StudentDaoImpl()).delete(Integer.parseInt(student.getStuID()));
                this.alert("成功提示", "成功删除学号为【" + student.getStuID() + "】的学生数据！", (String)null, AlertType.INFORMATION);
                this.refreshStuTable();
            } else {
                this.alert("错误提示", "没有该学生的记录，无法删除！", (String)null, AlertType.ERROR);
            }
        }

    }

    public void queryStu() {
        TextInputDialog d = new TextInputDialog();
        d.setTitle("查询学生");
        d.setHeaderText("请输入要查询的学生学号：");
        d.setContentText("学号:");
        Optional<String> result = d.showAndWait();
        if (result.isPresent()) {
            if (this.checkIdIllegal((String)result.get())) {
                return;
            }

            Student student = (new StudentDaoImpl()).get(Integer.parseInt((String)result.get()));
            if (null != student) {
                Dialog<Controller.StudentResults> dialog = new Dialog();
                dialog.setTitle("学生数据");
                dialog.setHeaderText((String)null);
                DialogPane dialogPane = dialog.getDialogPane();
                dialogPane.getButtonTypes().addAll(new ButtonType[]{ButtonType.OK});
                GridPane grid = new GridPane();
                grid.setHgap(10.0D);
                grid.setVgap(10.0D);
                grid.setPadding(new Insets(20.0D, 60.0D, 10.0D, 10.0D));
                TextField sID = new TextField(student.getStuID());
                sID.setEditable(false);
                TextField sClass = new TextField(student.getStuClass());
                sClass.setEditable(false);
                TextField sName = new TextField(student.getStuName());
                sName.setEditable(false);
                TextField sSex = new TextField(student.getStuSex());
                sSex.setEditable(false);
                TextField sBirth = new TextField(student.getStuBirth());
                sBirth.setEditable(false);
                TextField sMajor = new TextField(student.getStuMajor());
                sMajor.setEditable(false);
                grid.add(new Label("学号:"), 0, 0);
                grid.add(sID, 1, 0);
                grid.add(new Label("班级:"), 0, 1);
                grid.add(sClass, 1, 1);
                grid.add(new Label("姓名:"), 0, 2);
                grid.add(sName, 1, 2);
                grid.add(new Label("性别:"), 0, 3);
                grid.add(sSex, 1, 3);
                grid.add(new Label("出生日期:"), 0, 4);
                grid.add(sBirth, 1, 4);
                grid.add(new Label("所在专业:"), 0, 5);
                grid.add(sMajor, 1, 5);
                dialog.getDialogPane().setContent(grid);
                dialog.showAndWait();
            } else {
                this.alert("错误提示", "没有该学生的记录，无法查询！", (String)null, AlertType.ERROR);
            }
        }

    }

    public void addCourse() {
        Dialog<Controller.CourseResults> dialog = new Dialog();
        dialog.setTitle("添加课程");
        dialog.setHeaderText("请在下面输入要添加的课程信息：");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(new ButtonType[]{ButtonType.OK, ButtonType.CANCEL});
        GridPane grid = new GridPane();
        grid.setHgap(10.0D);
        grid.setVgap(10.0D);
        grid.setPadding(new Insets(20.0D, 60.0D, 10.0D, 10.0D));
        TextField cID = new TextField();
        TextField cMajor = new TextField();
        TextField cName = new TextField();
        TextField cType = new TextField();
        TextField cStartTerm = new TextField();
        TextField cPeriod = new TextField();
        TextField cCredit = new TextField();
        grid.add(new Label("课程号:"), 0, 0);
        grid.add(cID, 1, 0);
        grid.add(new Label("所属专业:"), 0, 1);
        grid.add(cMajor, 1, 1);
        grid.add(new Label("课程名称:"), 0, 2);
        grid.add(cName, 1, 2);
        grid.add(new Label("课程类型:"), 0, 3);
        grid.add(cType, 1, 3);
        grid.add(new Label("开课学期:"), 0, 4);
        grid.add(cStartTerm, 1, 4);
        grid.add(new Label("学时数:"), 0, 5);
        grid.add(cPeriod, 1, 5);
        grid.add(new Label("学分:"), 0, 6);
        grid.add(cCredit, 1, 6);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter((button) -> {
            return button == ButtonType.OK ? new Controller.CourseResults(cID.getText(), cMajor.getText(), cName.getText(), cType.getText(), cStartTerm.getText(), cPeriod.getText(), cCredit.getText()) : null;
        });
        Optional<Controller.CourseResults> optionalResult = dialog.showAndWait();
        optionalResult.ifPresent((results) -> {
            Course course = (new CourseDaoImpl()).get(Integer.parseInt(results.cID));
            if (course != null) {
                this.alert("错误提示", "课程号为【" + results.cID + "】的数据已经存在，无法添加！", (String)null, AlertType.INFORMATION);
            } else {
                (new CourseDaoImpl()).save(new Course(results.cID, results.cMajor, results.cName, results.cType, results.cStartTerm, results.cPeriod, results.cCredit));
                this.alert("成功提示", "成功保存课程号为【" + results.cID + "】的课程数据！", (String)null, AlertType.ERROR);
                this.refreshCourseTable();
            }

        });
    }

    public void changeCourse() {
        TextInputDialog d = new TextInputDialog();
        d.setTitle("修改课程信息");
        d.setHeaderText("请输入要修改信息的课程号：");
        d.setContentText("课程号:");
        Optional<String> result = d.showAndWait();
        if (result.isPresent()) {
            if (this.checkIdIllegal((String)result.get())) {
                return;
            }

            Course course = (new CourseDaoImpl()).get(Integer.parseInt((String)result.get()));
            if (null != course) {
                Dialog<Controller.CourseResults> dialog = new Dialog();
                dialog.setTitle("课程数据");
                dialog.setHeaderText((String)null);
                DialogPane dialogPane = dialog.getDialogPane();
                dialogPane.getButtonTypes().addAll(new ButtonType[]{ButtonType.OK});
                GridPane grid = new GridPane();
                grid.setHgap(10.0D);
                grid.setVgap(10.0D);
                grid.setPadding(new Insets(20.0D, 60.0D, 10.0D, 10.0D));
                TextField cID = new TextField(course.getcID());
                TextField cMajor = new TextField(course.getcMajor());
                TextField cName = new TextField(course.getcName());
                TextField cType = new TextField(course.getcType());
                TextField cStartTerm = new TextField(course.getcStartTerm());
                TextField cPeriod = new TextField(course.getcPeriod());
                TextField cCredit = new TextField(course.getcCredit());
                grid.add(new Label("课程号:"), 0, 0);
                grid.add(cID, 1, 0);
                grid.add(new Label("所属专业:"), 0, 1);
                grid.add(cMajor, 1, 1);
                grid.add(new Label("课程名称:"), 0, 2);
                grid.add(cName, 1, 2);
                grid.add(new Label("课程类型:"), 0, 3);
                grid.add(cType, 1, 3);
                grid.add(new Label("开课学期:"), 0, 4);
                grid.add(cStartTerm, 1, 4);
                grid.add(new Label("学时数:"), 0, 5);
                grid.add(cPeriod, 1, 5);
                grid.add(new Label("学分:"), 0, 6);
                grid.add(cCredit, 1, 6);
                dialog.getDialogPane().setContent(grid);
                Optional<Controller.CourseResults> results = dialog.showAndWait();
                if (results.isPresent()) {
                    course = new Course(cID.getText(), cMajor.getText(), cName.getText(), cType.getText(), cStartTerm.getText(), cPeriod.getText(), cCredit.getText());
                    (new CourseDaoImpl()).update(Integer.parseInt((String)result.get()), course);
                    this.alert("成功提示", "成功修改课程号为【" + course.getcID() + "】的课程数据！", (String)null, AlertType.INFORMATION);
                    this.refreshCourseTable();
                }
            } else {
                this.alert("错误提示", "没有该课程的记录，无法修改！", (String)null, AlertType.ERROR);
            }
        }

    }

    public void deleteCourse() {
        TextInputDialog d = new TextInputDialog();
        d.setTitle("删除课程");
        d.setHeaderText("请输入要删除的课程号：");
        d.setContentText("课程号:");
        Optional<String> result = d.showAndWait();
        if (result.isPresent()) {
            if (this.checkIdIllegal((String)result.get())) {
                return;
            }

            Course course = (new CourseDaoImpl()).get(Integer.parseInt((String)result.get()));
            if (null != course) {
                System.out.println("删除课程号为" + course.getcID() + "的数据");
                (new CourseDaoImpl()).delete(Integer.parseInt(course.getcID()));
                this.alert("成功提示", "成功删除课程号为【" + course.getcID() + "】的课程数据！", (String)null, AlertType.INFORMATION);
                this.refreshCourseTable();
            } else {
                this.alert("错误提示", "没有该课程的记录，无法删除！", (String)null, AlertType.ERROR);
            }
        }

    }

    public void addScore() {
        Dialog<Controller.ScoreResults> dialog = new Dialog();
        dialog.setTitle("添加成绩");
        dialog.setHeaderText("请输入对应学号、课程号和成绩：");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(new ButtonType[]{ButtonType.OK, ButtonType.CANCEL});
        GridPane grid = new GridPane();
        grid.setHgap(10.0D);
        grid.setVgap(10.0D);
        grid.setPadding(new Insets(20.0D, 60.0D, 10.0D, 10.0D));
        TextField sID = new TextField();
        TextField cID = new TextField();
        TextField _score = new TextField();
        grid.add(new Label("学号:"), 0, 0);
        grid.add(sID, 1, 0);
        grid.add(new Label("课程号:"), 0, 1);
        grid.add(cID, 1, 1);
        grid.add(new Label("成绩:"), 0, 2);
        grid.add(_score, 1, 2);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter((button) -> {
            return button == ButtonType.OK ? new Controller.ScoreResults(sID.getText(), cID.getText(), _score.getText()) : null;
        });
        Optional<Controller.ScoreResults> optionalResult = dialog.showAndWait();
        optionalResult.ifPresent((results) -> {
            Score score = (new ScoreDaoImpl()).get(Integer.parseInt(results.stuID), Integer.parseInt(results.cID));
            Course course = (new CourseDaoImpl()).get(Integer.parseInt(results.cID));
            if (course == null) {
                this.alert("失败提示", "该门课不存在，无法添加！", (String)null, AlertType.ERROR);
            } else {
                if (score != null) {
                    this.alert("失败提示", "该学生的这门课已有成绩，无法添加！", (String)null, AlertType.ERROR);
                } else {
                    (new ScoreDaoImpl()).save(Integer.parseInt(results.stuID), Integer.parseInt(results.cID), new Score(results.stuID, results.cID, results.score, String.format("%.3s", Double.parseDouble(results.score) / 100.0D * (double)Integer.parseInt(course.getcCredit()) + "")));
                    this.alert("成功提示", "成功保存此门成绩！", (String)null, AlertType.INFORMATION);
                    this.refreshScoreTable();
                }

            }
        });
    }

    public void deleteScore() {
        Dialog<Controller.ScoreResults> dialog = new Dialog();
        dialog.setTitle("删除成绩");
        dialog.setHeaderText("请输入要对应的学号，课程号：");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(new ButtonType[]{ButtonType.OK, ButtonType.CANCEL});
        GridPane grid = new GridPane();
        grid.setHgap(10.0D);
        grid.setVgap(10.0D);
        grid.setPadding(new Insets(20.0D, 60.0D, 10.0D, 10.0D));
        TextField sID = new TextField();
        TextField cID = new TextField();
        grid.add(new Label("学号:"), 0, 0);
        grid.add(sID, 1, 0);
        grid.add(new Label("课程号:"), 0, 1);
        grid.add(cID, 1, 1);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter((button) -> {
            return button == ButtonType.OK ? new Controller.ScoreResults(sID.getText(), cID.getText()) : null;
        });
        Optional<Controller.ScoreResults> optionalResult = dialog.showAndWait();
        optionalResult.ifPresent((results) -> {
            Score score = (new ScoreDaoImpl()).get(Integer.parseInt(results.stuID), Integer.parseInt(results.cID));
            if (score == null) {
                this.alert("失败提示", "没有这门课，无法删除！", (String)null, AlertType.ERROR);
            } else {
                (new ScoreDaoImpl()).delete(Integer.parseInt(results.stuID), Integer.parseInt(results.cID));
                this.alert("成功提示", "成功删除这门成绩！", (String)null, AlertType.INFORMATION);
                this.refreshScoreTable();
            }

        });
    }

    public void changeScore() {
        Dialog<Controller.ScoreResults> dialog = new Dialog();
        dialog.setTitle("修改成绩");
        dialog.setHeaderText("请输入对应学号、课程号和成绩：");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(new ButtonType[]{ButtonType.OK, ButtonType.CANCEL});
        GridPane grid = new GridPane();
        grid.setHgap(10.0D);
        grid.setVgap(10.0D);
        grid.setPadding(new Insets(20.0D, 60.0D, 10.0D, 10.0D));
        TextField sID = new TextField();
        TextField cID = new TextField();
        TextField _score = new TextField();
        grid.add(new Label("学号:"), 0, 0);
        grid.add(sID, 1, 0);
        grid.add(new Label("课程号:"), 0, 1);
        grid.add(cID, 1, 1);
        grid.add(new Label("成绩:"), 0, 2);
        grid.add(_score, 1, 2);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter((button) -> {
            return button == ButtonType.OK ? new Controller.ScoreResults(sID.getText(), cID.getText(), _score.getText()) : null;
        });
        Optional<Controller.ScoreResults> optionalResult = dialog.showAndWait();
        optionalResult.ifPresent((results) -> {
            Score score = (new ScoreDaoImpl()).get(Integer.parseInt(results.stuID), Integer.parseInt(results.cID));
            Course course = (new CourseDaoImpl()).get(Integer.parseInt(results.cID));
            if (score == null) {
                this.alert("失败提示", "成绩不存在，无法修改！", (String)null, AlertType.ERROR);
            } else {
                score.setCredit(String.format("%.3s", Double.parseDouble(results.score) / 100.0D * (double)Integer.parseInt(course.getcCredit()) + ""));
                (new ScoreDaoImpl()).update(Integer.parseInt(results.stuID), score);
                this.alert("成功提示", "成功修改成绩！", (String)null, AlertType.INFORMATION);
                this.refreshScoreTable();
            }

        });
    }

//    public void statistic() {
//        TextInputDialog d = new TextInputDialog();
//        d.setTitle("统计学分");
//        d.setHeaderText("请输入要统计学分的学生学号：");
//        d.setContentText("学号:");
//        Optional<String> result = d.showAndWait();
//        if (result.isPresent()) {
//            if (((String)result.get()).trim().equals("")) {
//                return;
//            }
//
//            if (this.checkIdIllegal((String)result.get())) {
//                return;
//            }
//
//            int sId = Integer.parseInt((String)result.get());
//            Student student = (new StudentDaoImpl()).get(sId);
//            if (student != null) {
//                this.alert("学分统计", (String)result.get() + student.getStuName() + "的总学分为:" + String.format("%.4s", (new OtherDaoImpl()).statistic(sId)), (String)null, AlertType.INFORMATION);
//            } else {
//                this.alert("错误提示", "没有该学生的记录，无法统计！", (String)null, AlertType.ERROR);
//            }
//        }
//
//    }

//    public void analysis() {
//        Dialog<Pair<String, String>> dialog = new Dialog();
//        dialog.setTitle("成绩分析");
//        dialog.setHeaderText("请输入要分析的班级、课程：");
//        DialogPane dialogPane = dialog.getDialogPane();
//        dialogPane.getButtonTypes().addAll(new ButtonType[]{ButtonType.OK, ButtonType.CANCEL});
//        GridPane grid = new GridPane();
//        grid.setHgap(10.0D);
//        grid.setVgap(10.0D);
//        grid.setPadding(new Insets(20.0D, 60.0D, 10.0D, 10.0D));
//        TextField sClass = new TextField();
//        TextField cID = new TextField();
//        grid.add(new Label("班级:"), 0, 0);
//        grid.add(sClass, 1, 0);
//        grid.add(new Label("课程号:"), 0, 1);
//        grid.add(cID, 1, 1);
//        dialog.getDialogPane().setContent(grid);
//        dialog.setResultConverter((button) -> {
//            return button == ButtonType.OK ? new Pair(sClass.getText(), cID.getText()) : null;
//        });
//        Optional<Pair<String, String>> optionalResult = dialog.showAndWait();
//        optionalResult.ifPresent((results) -> {
//            int _class = Integer.parseInt((String)results.getKey());
//            int _cId = Integer.parseInt((String)results.getValue());
//            Course course = (new CourseDaoImpl()).get(_cId);
//            if (course == null) {
//                this.alert("失败提示", "没有这门课，无法分析！", (String)null, AlertType.ERROR);
//            } else {
//                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//                int score_0_60 = (new OtherDaoImpl()).analysis(_class, _cId, 0, 60);
//                int score_60_75 = (new OtherDaoImpl()).analysis(_class, _cId, 60, 75);
//                int score_75_90 = (new OtherDaoImpl()).analysis(_class, _cId, 75, 90);
//                int score_90_100 = (new OtherDaoImpl()).analysis(_class, _cId, 90, 100);
//                int score_100 = (new OtherDaoImpl()).analysis(_class, _cId, 100, 101);
//                String cName = course.getcName();
//                dataset.addValue((double)score_0_60, cName, "0~60");
//                dataset.addValue((double)score_60_75, cName, "60~75");
//                dataset.addValue((double)score_75_90, cName, "75~90");
//                dataset.addValue((double)score_90_100, cName, "90~100");
//                dataset.addValue((double)score_100, cName, "100");
//                this.setChart("成绩直方图", "成绩分布", "人数", dataset);
//            }
//
//        });
//    }

    public void initialize(URL location, ResourceBundle resources) {
        this.refreshStuTable();
        this.refreshCourseTable();
        this.refreshScoreTable();
        this.setTabVisible(this.tab_stu, this.studentTableView);
        this.setTabVisible(this.tab_course, this.courseTableView);
        this.setTabVisible(this.tab_score, this.scoreTableView);
    }

    private void setChart(String title, String xName, String yName, CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(title, xName, yName, dataset);
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLabelFont(new Font("黑体", 1, 14));
        domainAxis.setTickLabelFont(new Font("宋体", 1, 12));
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setLabelFont(new Font("黑体", 1, 15));
        chart.getLegend().setItemFont(new Font("黑体", 1, 15));
        chart.getTitle().setFont(new Font("宋体", 1, 20));
        ChartPanel chartPanel = new ChartPanel(chart, true);
        JFrame frame = new JFrame("成绩分布图");
        frame.add(chartPanel);
        frame.setBounds(50, 50, 900, 600);
        frame.setVisible(true);
    }

    private CategoryDataset getChartDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int score_0_60 = (new OtherDaoImpl()).analysis(172102, 3, 0, 60);
        int score_60_75 = (new OtherDaoImpl()).analysis(172102, 3, 60, 75);
        int score_75_90 = (new OtherDaoImpl()).analysis(172102, 3, 75, 90);
        int score_90_100 = (new OtherDaoImpl()).analysis(172102, 3, 90, 100);
        int score_100 = (new OtherDaoImpl()).analysis(172102, 3, 100, 101);
        dataset.addValue((double)score_75_90, "数据结构", "数据结构");
        return dataset;
    }

    private void refreshStuTable() {
        this.stuIDCol.setCellValueFactory(new PropertyValueFactory("stuID"));
        this.stuClassCol.setCellValueFactory(new PropertyValueFactory("stuClass"));
        this.stuNameCol.setCellValueFactory(new PropertyValueFactory("stuName"));
        this.stuSexCol.setCellValueFactory(new PropertyValueFactory("stuSex"));
        this.stuBirCol.setCellValueFactory(new PropertyValueFactory("stuBirth"));
        this.stuMajorCol.setCellValueFactory(new PropertyValueFactory("stuMajor"));
        List<Student> students = (new StudentDaoImpl()).getAll();
        ObservableList<Student> data = FXCollections.observableArrayList();
        Iterator var3 = students.iterator();

        while(var3.hasNext()) {
            Student student = (Student)var3.next();
            data.add(student);
        }

        this.studentTableView.setItems(data);
    }

    private void refreshCourseTable() {
        this.cIdCol.setCellValueFactory(new PropertyValueFactory("cID"));
        this.cMajorCol.setCellValueFactory(new PropertyValueFactory("cMajor"));
        this.cNameCol.setCellValueFactory(new PropertyValueFactory("cName"));
        this.cTypeCol.setCellValueFactory(new PropertyValueFactory("cType"));
        this.cStartTremCol.setCellValueFactory(new PropertyValueFactory("cStartTerm"));
        this.cPeriodCol.setCellValueFactory(new PropertyValueFactory("cPeriod"));
        this.cCreditCol.setCellValueFactory(new PropertyValueFactory("cCredit"));
        List<Course> courses = (new CourseDaoImpl()).getAll();
        ObservableList<Course> data = FXCollections.observableArrayList();
        Iterator var3 = courses.iterator();

        while(var3.hasNext()) {
            Course course = (Course)var3.next();
            data.add(course);
        }

        this.courseTableView.setItems(data);
    }

    private void refreshScoreTable() {
        this.score_sIdCol.setCellValueFactory(new PropertyValueFactory("stuID"));
        this.score_cIdCol.setCellValueFactory(new PropertyValueFactory("cID"));
        this.score_creditCol.setCellValueFactory(new PropertyValueFactory("credit"));
        this.scoreCol.setCellValueFactory(new PropertyValueFactory("score"));
        List<Score> scores = (new ScoreDaoImpl()).getAll();
        ObservableList<Score> data = FXCollections.observableArrayList();
        Iterator var3 = scores.iterator();

        while(var3.hasNext()) {
            Score score = (Score)var3.next();
            data.add(score);
            System.out.println(score);
        }

        this.scoreTableView.setItems(data);
    }

    private boolean checkIdIllegal(String sID) {
        if (sID.length() >= 10) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText((String)null);
            alert.setContentText("输入的数据不合法！");
            alert.showAndWait();
            return true;
        } else {
            return false;
        }
    }

    private void alert(String title, String content, String header, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void setTabVisible(Tab tab, final TableView tableView) {
        this.setTabAction(tab, new Controller.Task() {
            public void execute() {
                if (tableView.equals(Controller.this.studentTableView)) {
                    Controller.this.courseTableView.setVisible(false);
                    Controller.this.studentTableView.setVisible(true);
                    Controller.this.scoreTableView.setVisible(false);
                } else if (tableView.equals(Controller.this.courseTableView)) {
                    Controller.this.studentTableView.setVisible(false);
                    Controller.this.courseTableView.setVisible(true);
                    Controller.this.scoreTableView.setVisible(false);
                } else if (tableView.equals(Controller.this.scoreTableView)) {
                    Controller.this.studentTableView.setVisible(false);
                    Controller.this.courseTableView.setVisible(false);
                    Controller.this.scoreTableView.setVisible(true);
                }

            }
        });
    }

    private void setTabAction(Tab tab, final Controller.Task task) {
        tab.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                task.execute();
            }
        });
    }

    private interface Task {
        void execute();
    }

    private static class ScoreResults {
        private String stuID;
        private String cID;
        private String score;

        public ScoreResults(String stuID, String cID) {
            this.stuID = stuID;
            this.cID = cID;
        }

        public ScoreResults(String stuID, String cID, String score) {
            this.stuID = stuID;
            this.cID = cID;
            this.score = score;
        }
    }

    private static class CourseResults {
        private String cID;
        private String cMajor;
        private String cName;
        private String cType;
        private String cStartTerm;
        private String cPeriod;
        private String cCredit;

        public CourseResults(String cID, String cMajor, String cName, String cType, String cStartTerm, String cPeriod, String cCredit) {
            this.cID = cID;
            this.cMajor = cMajor;
            this.cName = cName;
            this.cType = cType;
            this.cStartTerm = cStartTerm;
            this.cPeriod = cPeriod;
            this.cCredit = cCredit;
        }
    }

    private static class StudentResults {
        String sID;
        String sClass;
        String sName;
        String sSex;
        String sBirth;
        String sMajor;

        public StudentResults(String sID, String sClass, String sName, String sSex, String sBirth, String sMajor) {
            this.sID = sID;
            this.sClass = sClass;
            this.sName = sName;
            this.sSex = sSex;
            this.sBirth = sBirth;
            this.sMajor = sMajor;
        }
    }
}
