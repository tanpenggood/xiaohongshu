package com.itplh.xhs.ui.home;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class ControlContext {

    // 表格视图
    private TableView<User> tableView = new TableView<>();

    // 表格数据
    private ObservableList<User> userList = FXCollections.observableArrayList();

    // 创建输入框和操作按钮
    private TextField nicknameTextField = new TextField();
    private TextField urlTextField = new TextField();

    // 默认用户列表
    private static List<User> defaultUsers = new ArrayList();

    static {
        defaultUsers.add(new User("栖梧起名", "https://www.xiaohongshu.com/user/profile/64672d6500000000290173c7"));
        defaultUsers.add(new User("昱安起名", "https://www.xiaohongshu.com/user/profile/645de545000000002a00bdd7"));
        defaultUsers.add(new User("懿安国学", "https://www.xiaohongshu.com/user/profile/62bc2c1c000000001b025a19"));
    }

    public TableView<User> getTableView() {
        return tableView;
    }

    public ObservableList<User> getUserList() {
        return userList;
    }

    public TextField getNicknameTextField() {
        return nicknameTextField;
    }

    public TextField getUrlTextField() {
        return urlTextField;
    }

    public static List<User> getDefaultUsers() {
        return defaultUsers;
    }

}
