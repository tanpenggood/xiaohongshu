package com.itplh.xhs;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: tanpenggood
 * @date: 2023-07-26 18:44
 */
public class XhsCrawlabUI extends Application {

    // 创建表格视图
    private static TableView<AppUser> tableView = new TableView<>();

    // 初始化列表数据
    private static ObservableList<AppUser> userList = FXCollections.observableArrayList();
    private static List<AppUser> defaultUsers = new ArrayList();

    // 创建输入框和操作按钮
    private static TextField nicknameTextField = new TextField();
    private static TextField urlTextField = new TextField();

    static {
        defaultUsers.add(new AppUser("栖梧起名", "https://www.xiaohongshu.com/user/profile/64672d6500000000290173c7"));
        defaultUsers.add(new AppUser("昱安起名", "https://www.xiaohongshu.com/user/profile/645de545000000002a00bdd7"));
        defaultUsers.add(new AppUser("懿安国学", "https://www.xiaohongshu.com/user/profile/62bc2c1c000000001b025a19"));
        userList.addAll(defaultUsers);
        tableView.setItems(userList);

        tableView.setMaxHeight(400.0);
        nicknameTextField.setPromptText("Please enter nickname");
        urlTextField.setPromptText("Please enter user home url");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // 创建表格列-nickname
        TableColumn<AppUser, String> nicknameColumn = new TableColumn<>("Nickname");
        nicknameColumn.setMinWidth(100.0);
        tableView.getColumns().add(nicknameColumn);
        nicknameColumn.setCellValueFactory(new PropertyValueFactory<AppUser, String>("nickname"));
        // 创建表格列-homeUrl
        TableColumn<AppUser, String> urlColumn = new TableColumn<>("Home URL");
        urlColumn.setMinWidth(400.0);
        tableView.getColumns().add(urlColumn);
        urlColumn.setCellValueFactory(new PropertyValueFactory<AppUser, String>("homeUrl"));

        // 按钮-add
        Button addButton = new Button("Add");
        addButton.setOnAction(event -> add(nicknameTextField.getText(), urlTextField.getText()));
        // 按钮-delete
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> delete());
        // 按钮-reset
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event -> reset());
        // 按钮-crawl
        Button crawlButton = new Button("Crawl All User Note to Excel");
        crawlButton.setOnAction(event -> crawl());

        // 创建布局容器
        AnchorPane pane = new AnchorPane();
        // line 1
        pane.getChildren().add(nicknameTextField);
        AnchorPane.setTopAnchor(nicknameTextField, 10.0);
        AnchorPane.setLeftAnchor(nicknameTextField, 10.0);
        pane.getChildren().add(urlTextField);
        AnchorPane.setTopAnchor(urlTextField, 10.0);
        AnchorPane.setLeftAnchor(urlTextField, 180.0);
        pane.getChildren().add(addButton);
        AnchorPane.setTopAnchor(addButton, 10.0);
        AnchorPane.setLeftAnchor(addButton, 350.0);
        pane.getChildren().add(resetButton);
        AnchorPane.setTopAnchor(resetButton, 10.0);
        AnchorPane.setLeftAnchor(resetButton, 400.0);
        pane.getChildren().add(deleteButton);
        AnchorPane.setTopAnchor(deleteButton, 10.0);
        AnchorPane.setLeftAnchor(deleteButton, 458.0);
        // line 2
        pane.getChildren().add(crawlButton);
        AnchorPane.setTopAnchor(crawlButton, 50.0);
        AnchorPane.setLeftAnchor(crawlButton, 220.0);
        // line 3
        pane.getChildren().add(tableView);
        AnchorPane.setTopAnchor(tableView, 90.0);
        AnchorPane.setLeftAnchor(tableView, 50.0);

        // 创建场景
        Scene scene = new Scene(pane, 600, 520);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Crawl XHS Note");
        primaryStage.show();
    }

    private void add(String nickname, String url) {
        if (Objects.isNull(nickname)
                || Objects.isNull(url)
                || Objects.equals("", nickname.trim())
                || Objects.equals("", url.trim())) {
            return;
        }
        // Append element to the end of this list
        userList.add(new AppUser(nickname.trim(), url.trim()));
        // 选中最后一行数据
        tableView.getSelectionModel().selectLast();
        // clear
        nicknameTextField.clear();
        urlTextField.clear();
    }

    private void delete() {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            userList.remove(selectedIndex);
        }
    }

    private void reset() {
        userList.clear();
        userList.addAll(defaultUsers);
        tableView.getSelectionModel().selectFirst();
    }

    private void crawl() {
        if (userList == null || userList.isEmpty()) {
            return;
        }
        ButtonType result = confirm();
        if (Objects.equals(ButtonType.OK, result)) {
            for (AppUser user : userList) {
                XhsCrawlab.crawlHome(user.getHomeUrl());
            }
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Success");
            success.setHeaderText("Operation Successfully!");
            success.setContentText("Please view Excel in the current directory.");
            success.showAndWait();
        }
    }

    private ButtonType confirm() {
        // 创建一个确认对话框
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认");

        alert.setHeaderText(String.format("确定要爬取这%s位用户的笔记吗？", userList.size()));
        alert.setContentText("点击确定继续，点击取消关闭。");
        // 显示对话框并等待用户响应
        return alert.showAndWait().orElse(ButtonType.CANCEL);
    }

}
