package com.itplh.xhs.ui.settings;

import com.itplh.absengine.util.StringUtils;
import com.itplh.xhs.XhsCrawlab;
import com.itplh.xhs.util.AlertUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class EventExecutor {

    public void setCookie(ControlContext context) {
        if (Objects.isNull(context)
                || Objects.isNull(context.getCookieTextField())
                || StringUtils.isBlank(context.getCookieTextField().getText())) {
            return;
        }
        XhsCrawlab.getInstance().setCookie(context.getCookieTextField().getText().trim());
        refreshCookie2Control(context);
        AlertUtil.success("Cookie has been set successfully!");
    }

    public void clearCookie(ControlContext context) {
        context.getCookieTextField().setText("");
        XhsCrawlab.getInstance().setCookie("");
        refreshCookie2Control(context);
        AlertUtil.success("Cookie has been clear successfully!");
    }

    private static void refreshCookie2Control(ControlContext context) {
        context.getRealtimeCookieTextField().setText(XhsCrawlab.getInstance().getCookie());
    }

}
