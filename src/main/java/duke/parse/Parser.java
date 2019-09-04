package duke.parse;

import duke.command.ExitCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.AddCommand;
import duke.command.DoneCommand;
import duke.command.SearchCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.exception.EmptyToDoDescriptionException;
import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Parser deals with the logic of parsing user inputs.
 */
public class Parser {

    /**
     * Parses user input and returns the relevant command to be executed in the main message.
     * @param command user command
     * @return Command to be executed in main message
     * @throws DukeException when the user input is not understood
     */
    public static Command parse(String command) throws DukeException {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("done")) {
            int taskNumber = Integer.parseInt(command.substring(5)) - 1;
            return new DoneCommand(taskNumber);
        } else if (command.startsWith("delete")) {
            int taskNumber = Integer.parseInt(command.substring(7)) - 1;
            System.out.println(taskNumber);
            return new DeleteCommand(taskNumber);
        } else if (command.startsWith("find")) {
            String searchDescription = command.replaceFirst("find ", "");
            return new SearchCommand(searchDescription);
        } else {
            Task userTask;

            if (command.startsWith("todo")) {
                String task = command.replaceFirst("todo", "");
                if (task.isEmpty()) {
                    throw new EmptyToDoDescriptionException("The description of a todo cannot be empty. ");
                } else {
                    //removes space in the beginning
                    task = task.replaceFirst(" ", "");
                    userTask = new ToDo(task);
                }
            } else if (command.startsWith("deadline")) {
                String task = command.replaceFirst("deadline ", "");
                String[] taskInformation = task.split(" /by ");
                userTask = new Deadline(taskInformation[0], taskInformation[1]);
            } else if (command.startsWith("event")) {
                String task = command.replaceFirst("event ", "");
                String[] taskInformation = task.split(" /at ");
                userTask = new Event(taskInformation[0], taskInformation[1]);
            } else {
                throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
            }
            return new AddCommand(userTask);
        }
    }
}