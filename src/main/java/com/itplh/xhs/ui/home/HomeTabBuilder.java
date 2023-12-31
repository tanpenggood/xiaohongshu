package com.itplh.xhs.ui.home;

import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class HomeTabBuilder {

    public static Tab buildTab() {
        Tab tab = new Tab("Home");
        tab.setContent(getContentNode());
        // 禁用关闭按钮
        tab.setClosable(false);
        return tab;
    }

    private static AnchorPane getContentNode() {
        ControlContext context = new ControlContext();
        context.getNicknameTextField().setPromptText("Please enter nickname");
        context.getUrlTextField().setPromptText("Please enter user home url");

        // 创建表格列-nickname
        TableColumn<User, String> nicknameColumn = new TableColumn<>("Nickname");
        nicknameColumn.setMinWidth(100.0);
        context.getTableView().getColumns().add(nicknameColumn);
        nicknameColumn.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        // 创建表格列-homeUrl
        TableColumn<User, String> urlColumn = new TableColumn<>("Home URL");
        urlColumn.setMinWidth(400.0);
        context.getTableView().getColumns().add(urlColumn);
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("homeUrl"));
        // 初始化表格数据
        context.getUserList().addAll(ControlContext.getDefaultUsers());
        context.getTableView().setItems(context.getUserList());
        context.getTableView().setMaxHeight(400.0);

        EventExecutor eventExecutor = new EventExecutor();
        // 按钮-add
        Button addButton = new Button("Add");
        addButton.setOnAction(event -> eventExecutor.add(context));
        // 按钮-delete
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> eventExecutor.delete(context));
        // 按钮-reset
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event -> eventExecutor.reset(context));
        // 按钮-crawl
        Button crawlButton = new Button("Crawl All User Note to Excel");
        crawlButton.setOnAction(event -> eventExecutor.crawl(context));

        // 创建布局容器
        AnchorPane anchorPane = new AnchorPane();
        // line 1
        anchorPane.getChildren().add(context.getNicknameTextField());
        AnchorPane.setTopAnchor(context.getNicknameTextField(), 10.0);
        AnchorPane.setLeftAnchor(context.getNicknameTextField(), 10.0);
        anchorPane.getChildren().add(context.getUrlTextField());
        AnchorPane.setTopAnchor(context.getUrlTextField(), 10.0);
        AnchorPane.setLeftAnchor(context.getUrlTextField(), 180.0);
        anchorPane.getChildren().add(addButton);
        AnchorPane.setTopAnchor(addButton, 10.0);
        AnchorPane.setLeftAnchor(addButton, 350.0);
        anchorPane.getChildren().add(resetButton);
        AnchorPane.setTopAnchor(resetButton, 10.0);
        AnchorPane.setLeftAnchor(resetButton, 400.0);
        anchorPane.getChildren().add(deleteButton);
        AnchorPane.setTopAnchor(deleteButton, 10.0);
        AnchorPane.setLeftAnchor(deleteButton, 458.0);
        // line 2
        anchorPane.getChildren().add(crawlButton);
        AnchorPane.setTopAnchor(crawlButton, 50.0);
        AnchorPane.setLeftAnchor(crawlButton, 220.0);
        // line 3
        anchorPane.getChildren().add(context.getTableView());
        AnchorPane.setTopAnchor(context.getTableView(), 90.0);
        AnchorPane.setLeftAnchor(context.getTableView(), 50.0);
        return anchorPane;
    }

}
