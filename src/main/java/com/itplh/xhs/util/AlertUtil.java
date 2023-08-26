package com.itplh.xhs.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertUtil {

    public static ButtonType success(String contentText) {
        return alert(Alert.AlertType.INFORMATION, "Success", "Operation Successfully!", contentText);
    }

    public static ButtonType failed(String contentText) {
        return alert(Alert.AlertType.ERROR, "Error", "Operation Failed!", contentText);
    }

    public static ButtonType warning(String contentText) {
        return alert(Alert.AlertType.WARNING, "Warning", "Operation Warning!", contentText);
    }

    public static ButtonType confirm(String contentText) {
        return alert(Alert.AlertType.CONFIRMATION, "Confirm", "Operation Confirm!", contentText);
    }

    public static ButtonType alert(Alert.AlertType alertType,
                                   String title,
                                   String headerText,
                                   String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert.showAndWait().orElse(ButtonType.CANCEL);
    }

}
