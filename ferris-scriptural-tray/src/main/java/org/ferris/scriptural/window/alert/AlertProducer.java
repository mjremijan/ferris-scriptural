package org.ferris.scriptural.window.alert;

import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@ApplicationScoped
public class AlertProducer {

    private Alert alert;

    @Produces
    protected Alert produceAlert() {
        if (alert == null) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ferris Scriptural");
            alert.initModality(Modality.APPLICATION_MODAL);
            ((Stage)alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
        }
        return alert;
    }
}
