package duke;

import duke.ui.Main;
import javafx.application.Application;

public class Launcher {
    /**
     * A launcher class to workaround classpath issues.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

