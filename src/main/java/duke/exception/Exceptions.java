package duke.exception;

import duke.ui.TextUi;
/**
 * Exceptions
 */
public class Exceptions extends Exception {
    private static TextUi ui = new TextUi();
    public Exceptions (String message) {
        super(message);
    }

    public void printException() {
        ui.showCustomError(this.getMessage());
    }
}
