package com.itplh.xhs.ui.home;

import com.itplh.absengine.util.CollectionUtils;
import com.itplh.absengine.util.StringUtils;
import com.itplh.xhs.XhsCrawlab;
import com.itplh.xhs.domain.UserInfo;
import com.itplh.xhs.excel.ExcelGenerator;
import com.itplh.xhs.util.AlertUtil;
import javafx.collections.ObservableList;
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
            AlertUtil.failed("Please add some users first in the tab.");
            return;
        }
        if (StringUtils.isBlank(XhsCrawlab.getInstance().getCookie())) {
            AlertUtil.warning("Please set up your cookie first in Settings tab, otherwise only crawl a little note as guest.");
        }
        ButtonType result = AlertUtil.confirm(String.format("确定要爬取这%s位用户的笔记吗？", context.getUserList().size()));
        if (Objects.equals(ButtonType.CANCEL, result)) {
            return;
        }
        try {
            for (User user : userList) {
                UserInfo userInfo = XhsCrawlab.getInstance().crawlHome(user.getHomeUrl());
                // write notes data to excel
                ExcelGenerator.writeNotes2Excel(userInfo);
            }
            AlertUtil.success("Please view Excel in the current directory.");
        } catch (Exception e) {
            AlertUtil.failed("Please contact developers, some errors have occurred.");
            log.error(e.getMessage(), e);
        }
    }

}
