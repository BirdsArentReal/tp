package linkedout.model;

import javafx.collections.ObservableList;
import linkedout.model.application.Application;
import linkedout.model.application.Job;
import linkedout.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the applications list.
     * This list contains at most one application for each person.
     */
    ObservableList<Application> getApplicationList();

    /**
     * Returns an unmodifiable view of the job list.
     * This list will not contain duplicate jobs.
     * @return every stored job
     */
    ObservableList<Job> getJobList();

}
