package com.itplh.xhs.ui;

import com.itplh.xhs.ui.home.HomeTabBuilder;
import com.itplh.xhs.ui.settings.SettingsTabBuilder;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;

public class RootBuilder {

    public static Parent build() {
        // 创建选项卡面板并添加选项卡
        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(
                HomeTabBuilder.buildTab(),
                SettingsTabBuilder.buildTab()
        );
        return tabPane;
    }

}
