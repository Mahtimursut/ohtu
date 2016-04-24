package referencelibrary.ui.command;

import referencelibrary.App;
import referencelibrary.io.IO;

import java.util.HashMap;

import static referencelibrary.ui.command.CommandFactory.CommandName.*;

/**
 * Created by petri on 16.4.2016.
 */
public class CommandFactory {
    public enum CommandName {
        INVALID_COMMAND,
        HELP_COMMAND,
        ADD_BOOK_COMMAND,
        ADD_ARTICLE_COMMAND,
        ADD_INPROCEEDINGS_COMMAND,
        GENERATE_BIBTEX_COMMAND,
        REMOVE_REFERENCE_COMMAND,
        SHOW_REFERENCES_COMMAND
    }

    private HashMap<CommandName, Command> commands;

    /**
     * Creates a new CommandFactory that encapsulates the generation and accessing of Commands.
     * @param app Main business logic
     * @param io Connection to the human
     */
    public CommandFactory(App app, IO io) {
        commands = new HashMap<>();
        commands.put(INVALID_COMMAND, new InvalidCommand(app, io));
        commands.put(HELP_COMMAND, new HelpCommand(app, io));
        commands.put(ADD_BOOK_COMMAND, new AddBookCommand(app, io));
        commands.put(ADD_ARTICLE_COMMAND, new AddArticleCommand(app, io));
        commands.put(ADD_INPROCEEDINGS_COMMAND, new AddInproceedingsCommand(app, io));
        commands.put(GENERATE_BIBTEX_COMMAND, new GenerateBibTexCommand(app, io));
        commands.put(SHOW_REFERENCES_COMMAND, new ShowReferencesCommand(app, io));
        commands.put(REMOVE_REFERENCE_COMMAND, new RemoveReferenceCommand(app, io));
    }

    public Command getCommand(CommandName commandName) {
        Command command = commands.get(commandName);
        return command != null ? command : commands.get(INVALID_COMMAND);
    }
}

