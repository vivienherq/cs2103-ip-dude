package dude.command;

import dude.NoteList;
import dude.Storage;
import dude.TaskList;
import dude.Ui;

/**
 * Abstract parents class of Commands that can be created by users.
 */
public abstract class Command {

    protected boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public abstract String execute(TaskList taskList, NoteList noteList, Ui ui, Storage storage);

    public boolean isExit() {
        return this.isExit;
    }
}
