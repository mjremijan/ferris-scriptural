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


    @Produces
    protected Alert produceAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ferris Scriptural");
        alert.initModality(Modality.APPLICATION_MODAL);
        ((Stage)alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
        return alert;
    }
}
