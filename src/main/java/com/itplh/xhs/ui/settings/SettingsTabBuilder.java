package com.itplh.xhs.ui.settings;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;

public class SettingsTabBuilder {

    public static Tab buildTab() {
        Tab tab = new Tab("Settings");
        tab.setContent(getContentNode());
        // 禁用关闭按钮
        tab.setClosable(false);
        return tab;
    }

    private static StackPane getContentNode() {
        ControlContext context = new ControlContext();
        EventExecutor eventExecutor = new EventExecutor();
        // 按钮-Set Cookie
        Button setCookieButton = new Button("Set Cookie");
        setCookieButton.setOnAction(event -> eventExecutor.setCookie(context));
        // 按钮-Clear Cookie
        Button clearCookieButton = new Button("Clear Cookie");
        clearCookieButton.setOnAction(event -> eventExecutor.clearCookie(context));
        // 创建布局容器
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.TOP_CENTER);
        // line 1
        stackPane.getChildren().add(context.getCookieTextField());
        stackPane.setMargin(context.getCookieTextField(), new Insets(20, 0, 0, 0));
        // line 2
        stackPane.getChildren().add(setCookieButton);
        stackPane.setMargin(setCookieButton, new Insets(140, 0, 0, 0));
        stackPane.getChildren().add(clearCookieButton);
        stackPane.setMargin(clearCookieButton, new Insets(140, 0, 0, 200));
        // line 3
        stackPane.getChildren().add(context.getRealtimeCookieTextField());
        stackPane.setMargin(context.getRealtimeCookieTextField(), new Insets(180, 0, 0, 0));
        return stackPane;
    }

}
