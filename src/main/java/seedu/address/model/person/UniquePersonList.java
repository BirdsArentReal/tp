package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A Applicant is considered unique by comparing using {@code Applicant#isSameApplicant(applicant)}. As such, adding and updating of
 * persons uses Applicant#isSameApplicant(applicant) for equality so as to ensure that the Applicant being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a Applicant uses Applicant#equals(Object) so
 * as to ensure that the Applicant with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Applicant#isSameApplicant(applicant)
 */
public class UniquePersonList implements Iterable<Applicant> {

    private final ObservableList<Applicant> internalList = FXCollections.observableArrayList();
    private final ObservableList<Applicant> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Applicant as the given argument.
     */
    public boolean contains(Applicant toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameApplicant);
    }

    /**
     * Adds a Applicant to the list.
     * The Applicant must not already exist in the list.
     */
    public void add(Applicant toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Applicant {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The Applicant identity of {@code editedPerson} must not be the same as another existing Applicant in the list.
     */
    public void setPerson(Applicant target, Applicant editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameApplicant(editedPerson) && contains(editedPerson)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedPerson);
    }

    /**
     * Removes the equivalent Applicant from the list.
     * The Applicant must exist in the list.
     */
    public void remove(Applicant toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Applicant> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(persons)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(persons);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Applicant> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Applicant> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniquePersonList otherUniquePersonList)) {
            return false;
        }

        return internalList.equals(otherUniquePersonList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean personsAreUnique(List<Applicant> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSameApplicant(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
