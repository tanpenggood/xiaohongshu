package com.itplh.xhs;

import com.itplh.xhs.ui.RootBuilder;
import com.itplh.xhs.util.AlertUtil;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * @author: tanpenggood
 * @date: 2023-07-26 18:44
 */
public class XhsCrawlabUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // 创建场景
        Parent root = RootBuilder.build();
        Scene scene = new Scene(root, 600, 560);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Crawl XHS Note");
        primaryStage.show();

        AlertUtil.alert(Alert.AlertType.INFORMATION,
                "Welcome",
                "Welcome to XHS Crawlab UI",
                "Please set up your cookie and add some users, it will start working.");
    }

}
