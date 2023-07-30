package com.itplh.xhs;

import com.itplh.xhs.ui.ControlContext;
import com.itplh.xhs.ui.LayoutBuilder;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        Parent root = LayoutBuilder.buildRoot(new ControlContext());
        Scene scene = new Scene(root, 600, 520);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Crawl XHS Note");
        primaryStage.show();
    }

}
