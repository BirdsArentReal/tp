package linkedout.logic;

import javafx.collections.ObservableList;
import linkedout.commons.core.GuiSettings;
import linkedout.logic.commands.CommandResult;
import linkedout.logic.commands.exceptions.CommandException;
import linkedout.logic.parser.exceptions.ParseException;
import linkedout.model.application.Application;
import linkedout.model.application.Job;
import linkedout.model.person.Person;

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

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the recorded applications */
    ObservableList<Application> getApplicationList();

    /**
     * Returns an unmodifiable view of the jobs.
     * @return every stored job
     */
    ObservableList<Job> getJobList();

    /**
     * Returns an unmodifiable view of the filtered job list.
     * @return jobs that match the current filter
     */
    ObservableList<Job> getFilteredJobList();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
