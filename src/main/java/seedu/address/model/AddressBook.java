package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level.
 * Duplicates are not allowed (by .isSameApplicant comparison).
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons = new UniquePersonList();

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the Applicant list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Applicant> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// Applicant-level operations

    /**
     * Returns true if a Applicant with the same identity as {@code Applicant} exists in the address book.
     */
    public boolean hasPerson(Applicant applicant) {
        requireNonNull(applicant);
        return persons.contains(applicant);
    }

    /**
     * Adds a Applicant to the address book.
     * The Applicant must not already exist in the address book.
     */
    public void addPerson(Applicant p) {
        persons.add(p);
    }

    /**
     * Replaces the given Applicant {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The Applicant identity of {@code editedPerson} must not be the same as another existing Applicant in the address book.
     */
    public void setPerson(Applicant target, Applicant editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Applicant key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Applicant> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook otherAddressBook)) {
            return false;
        }

        return persons.equals(otherAddressBook.persons);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
