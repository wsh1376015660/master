package controller;

import dao.impl.TeacherDaoImpl;
import domain.Teacher;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import stage.LoginMain;
import stage.Main;
import stage.Register;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    public Button loginButton;
    @FXML
    public Button clearButton;
    public Label loginlabel;
    public TextField usernameTextfield;
    public PasswordField passwordField;
    TeacherDaoImpl teacherDaoimpl=new TeacherDaoImpl();

    public LoginController(){}


    @FXML
    public void login() throws Exception {
        String username=usernameTextfield.getText();
        String password=passwordField.getText();
        Teacher teacher= teacherDaoimpl.get(username,password);
        if(teacher!=null){
            Stage primaryStage=(Stage)loginButton.getScene().getWindow();
            primaryStage.close();
            LoginMain we=new LoginMain();
            Stage stage=new Stage();
            we.start(stage);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("错误提示");
            alert.headerTextProperty().set("用户名或密码不正确");
            alert.showAndWait();
        }
    }

    public void register(ActionEvent actionEvent) throws Exception {
        Register register=new Register();
        Stage stage=new Stage();
        register.start(stage);
    }


    public void clear(ActionEvent actionEvent) {
        usernameTextfield.setText("");
        passwordField.setText("");
    }
}
