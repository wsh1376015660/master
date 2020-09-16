package stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("登陆页面");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        primaryStage.setResizable(false);
        root.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
    }

    public static void main(String[] args) {
        Application.setUserAgentStylesheet(STYLESHEET_MODENA);
        launch(args);
    }

}
