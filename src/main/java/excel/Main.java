package excel;

import excel.Util.AppProperty;
import excel.Util.ResourceBundleControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author Alexander Diachenko.
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.getIcons().add(new Image("/img/logo.png"));
        Properties properties = AppProperty.getProperty();
        String style = (String) properties.get("style");
        String language = (String) properties.get("language");
        load(primaryStage, new Locale(language), style);
    }

    public static void load(Stage primaryStage, Locale locale, String style) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.main", locale, new ResourceBundleControl());
        Parent root = FXMLLoader.load(Main.class.getResource("/view/main.fxml"), bundle);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/" + style + "/main.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Excel util");
        primaryStage.show();
    }
}
