package com.itplh.xhs.ui;

import com.itplh.absengine.util.CollectionUtils;
import com.itplh.xhs.XhsCrawlab;
import com.itplh.xhs.domain.UserInfo;
import com.itplh.xhs.excel.ExcelGenerator;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class EventExecutor {

    public void add(ControlContext context) {
        if (Objects.isNull(context)
                || Objects.isNull(context.getNicknameTextField())
                || Objects.isNull(context.getUrlTextField())
                || Objects.equals("", context.getNicknameTextField().getText().trim())
                || Objects.equals("", context.getUrlTextField().getText().trim())) {
            return;
        }
        // Append element to the end of this list
        context.getUserList().add(new User(context.getNicknameTextField().getText().trim(), context.getUrlTextField().getText().trim()));
        // 选中最后一行数据
        context.getTableView().getSelectionModel().selectLast();
        // clear
        context.getNicknameTextField().clear();
        context.getUrlTextField().clear();
    }

    public void delete(ControlContext context) {
        int selectedIndex = context.getTableView().getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            context.getUserList().remove(selectedIndex);
        }
    }

    public void reset(ControlContext context) {
        context.getUserList().clear();
        context.getUserList().addAll(ControlContext.getDefaultUsers());
        context.getTableView().getSelectionModel().selectFirst();
    }

    public void crawl(ControlContext context) {
        ObservableList<User> userList = context.getUserList();
        if (CollectionUtils.isEmpty(userList)) {
            return;
        }
        ButtonType result = confirm(context);
        if (Objects.equals(ButtonType.OK, result)) {
            try {
                for (User user : userList) {
                    UserInfo userInfo = XhsCrawlab.crawlHome(user.getHomeUrl());
                    // write notes data to excel
                    ExcelGenerator.writeNotes2Excel(userInfo);
                }
                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setTitle("Success");
                success.setHeaderText("Operation Successfully!");
                success.setContentText("Please view Excel in the current directory.");
                success.showAndWait();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("Operation Failed!");
                error.setContentText("Please contact developers, some errors have occurred.");
                error.showAndWait();
            }
        }
    }

    public ButtonType confirm(ControlContext context) {
        // 创建一个确认对话框
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认");

        alert.setHeaderText(String.format("确定要爬取这%s位用户的笔记吗？", context.getUserList().size()));
        alert.setContentText("点击确定继续，点击取消关闭。");
        // 显示对话框并等待用户响应
        return alert.showAndWait().orElse(ButtonType.CANCEL);
    }

}
