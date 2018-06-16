package org.ferris.scriptural.window.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class MainApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Platform.setImplicitExit(false);

        SeContainer container
            = SeContainerInitializer.newInstance().initialize();

        Runnable main
            = container.select(Main.class).get();

        main.run();
    }
}
