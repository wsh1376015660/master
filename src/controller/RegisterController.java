package controller;

import dao.impl.TeacherDaoImpl;
import domain.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import stage.LoginMain;
import stage.Register;
import util.PasswordChecker;
import util.RegisterChecker;

public class RegisterController {
    @FXML
    public Button clearButton;
    public Label loginlabel;
    public TextField usernameTextfield;
    public PasswordField passwordField;
    TeacherDaoImpl teacherDaoimpl=new TeacherDaoImpl();
    PasswordChecker passwordChecker = new PasswordChecker();
    RegisterChecker registerChecker = new RegisterChecker();

    public RegisterController(){}


    @FXML
    public void register() throws Exception {
        String username=usernameTextfield.getText();
        String password=passwordField.getText();
        Teacher teacher= new Teacher(username,password,null);
        if(IsValid_register(teacher))
        {
            teacherDaoimpl.register(teacher);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("注册成功");
            alert.headerTextProperty().set("账号:  "+username+"\n密码:  "+password +"\n已注册成功");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("错误提示");
            alert.headerTextProperty().set("用户名或密码不符合规范"+"" +
                    "\n用户名必须包含字母和数字(长度6-20)" +
                    "\n密码必须包含大小写字母、数字、特殊字符（长度8-20");
            alert.showAndWait();
        }
    }

    public void clear(ActionEvent actionEvent) {
        usernameTextfield.setText("");
        passwordField.setText("");
    }

    public boolean IsValid_register(Teacher teacher) {
        return (registerChecker.check(teacher.getUsername()) && passwordChecker.check(teacher.getPassword()));
    }
}
