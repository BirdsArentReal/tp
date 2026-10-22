package linkedout.model.util;

import linkedout.model.AddressBook;
import linkedout.model.ReadOnlyAddressBook;
import linkedout.model.application.Job;
import linkedout.model.person.Email;
import linkedout.model.person.Name;
import linkedout.model.person.Person;
import linkedout.model.person.Phone;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"))
        };
    }

    public static Job[] getSampleJobs() {
        return new Job[] {
            new Job("Software Engineer Intern"),
            new Job("Data Analyst"),
            new Job("Product Designer")
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Job sampleJob : getSampleJobs()) {
            sampleAb.addJob(sampleJob);
        }
        return sampleAb;
    }

}
