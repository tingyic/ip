package duke;

import java.io.IOException;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.UI.Parser;
import duke.UI.UI;

/**
 * An administrative bot that handles a user's tasks.
 */
public class Duke {
    private TaskList nameOfTask;
    private Storage storage;
    private Parser parser;
    private UI ui;
    private boolean isExit;

    /**
     * The constructor of Duke.
     */
    public Duke() {
        storage = new Storage();
        parser = new Parser();
        ui = new UI();
        isExit = false;
    }

    private void runProgramme() {
        ui.welcomeResponse();
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                nameOfTask = storage.listFile();
                Command command = parser.parseInput(userInput);
                command.runCommand(nameOfTask, ui, storage);
                isExit = command.isExit();
            } catch (DukeException error1) {
                ui.showError(error1);
            } catch (IOException error2) {
                ui.showError(error2);
            } finally {
                ui.printEmptyLine();
            }
        }
    }

    private void runProgramme(String input) {
        if (isExit) {
            ui.printResponse("I'll be back soon!");
            return;
        }
        try {
            nameOfTask = storage.listFile();
            Command command = parser.parseInput(input);
            command.runCommand(nameOfTask, ui, storage);
            isExit = command.isExit();
        } catch (DukeException error1) {
            ui.showError(error1);
        } catch (IOException error2) {
            ui.showError(error2);
        }
    }

    /**
     * Generates corresponding response after taking a command from the user.
     * @param input The command entered by the user.
     * @return A corresponding response to the user's command.
     */
    public String getResponse(String input) {
        runProgramme(input);
        String response = ui.show();
        ui.clearResponse();
        return response;
    }

    public static void main(String[] args) {
        new Duke().runProgramme();
    }
}
