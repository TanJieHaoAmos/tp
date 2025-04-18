package seedu.finclient.logic;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.finclient.commons.core.GuiSettings;
import seedu.finclient.logic.commands.CommandResult;
import seedu.finclient.logic.commands.exceptions.CommandException;
import seedu.finclient.logic.parser.exceptions.ParseException;
import seedu.finclient.model.ReadOnlyFinClient;
import seedu.finclient.model.person.Person;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the FinClient.
     *
     * @see seedu.finclient.model.Model#getFinClient()
     */
    ReadOnlyFinClient getFinClient();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getFinClientFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the clearing price based on current orders.
     */
    Optional<Double> getClearingPrice();
    List<Person> getUpcomingPersons(int count);
}
