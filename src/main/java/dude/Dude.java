package dude;

import java.io.FileNotFoundException;

import dude.command.Command;

/**
 * Dude is a programme that allows users to manage their tasks.
 */

public class Dude {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for Dude that takes in a file path to storage file.
     * @param filePath Path to storage file.
     */

    public Dude(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.loadTasksFromDisk());
        } catch (FileNotFoundException e) { // DudeException
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Method that runs the Dude programme.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method for Dude. Start the programme here.
     * @param args
     */

    public static void main(String[] args) {
        new Dude("data/dude.txt").run();
    }
}
