package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Applicant;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluates to true */
    Predicate<Applicant> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a Applicant with the same identity as {@code Applicant} exists in the address book.
     */
    boolean hasPerson(Applicant applicant);

    /**
     * Deletes the given Applicant.
     * The Applicant must exist in the address book.
     */
    void deletePerson(Applicant target);

    /**
     * Adds the given Applicant.
     * {@code Applicant} must not already exist in the address book.
     */
    void addPerson(Applicant applicant);

    /**
     * Replaces the given Applicant {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The Applicant identity of {@code editedPerson} must not be the same as another existing Applicant in the address book.
     */
    void setPerson(Applicant target, Applicant editedPerson);

    /** Returns an unmodifiable view of the filtered Applicant list */
    ObservableList<Applicant> getFilteredPersonList();

    /**
     * Updates the filter of the filtered Applicant list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Applicant> predicate);
}
