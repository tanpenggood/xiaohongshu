package com.itplh.xhs.ui.settings;

import com.itplh.xhs.XhsCrawlab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControlContext {

    private TextArea cookieTextField = new TextArea();

    TextField realtimeCookieTextField = new TextField();

    {
        cookieTextField.setMinWidth(400);
        cookieTextField.setMaxWidth(400);
        cookieTextField.setMinHeight(100);
        cookieTextField.setMaxHeight(100);
        cookieTextField.setPromptText("Please input your xiaohongshu cookie");

        realtimeCookieTextField.setMinWidth(400);
        realtimeCookieTextField.setMaxWidth(400);
        realtimeCookieTextField.setPromptText("This is the realtime cookie, you can copy it.");
        realtimeCookieTextField.setEditable(false);
        realtimeCookieTextField.setText(XhsCrawlab.getInstance().getCookie());
    }

    public TextArea getCookieTextField() {
        return cookieTextField;
    }

    public TextField getRealtimeCookieTextField() {
        return realtimeCookieTextField;
    }

}
