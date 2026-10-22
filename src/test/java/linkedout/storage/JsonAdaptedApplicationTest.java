package linkedout.storage;

import static linkedout.logic.commands.CommandTestUtil.VALID_JOB_AMY;
import static linkedout.testutil.Assert.assertThrows;
import static linkedout.testutil.TypicalPersons.ALICE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import linkedout.commons.exceptions.IllegalValueException;
import linkedout.model.AddressBook;
import linkedout.model.application.Application;
import linkedout.model.application.Job;

public class JsonAdaptedApplicationTest {

    private static final String VALID_STATUS = Application.Status.PENDING.getValue();
    private static final AddressBook ADDRESS_BOOK = new AddressBook();

    static {
        ADDRESS_BOOK.addPerson(ALICE);
    }

    @Test
    public void toModelType_validApplicationDetails_returnsApplication() throws Exception {
        JsonAdaptedApplication application = new JsonAdaptedApplication(
                ALICE.getName().fullName, VALID_JOB_AMY, VALID_STATUS);

        assertEquals(new Application(ALICE, new Job(VALID_JOB_AMY), Application.Status.PENDING),
                application.toModelType(ADDRESS_BOOK));
    }

    @Test
    public void toModelType_nullJob_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(
                ALICE.getName().fullName, null, VALID_STATUS);

        assertThrows(IllegalValueException.class,
                String.format(JsonAdaptedApplication.MISSING_FIELD_MESSAGE_FORMAT, "job description"), () ->
                        application.toModelType(ADDRESS_BOOK));
    }

    @Test
    public void toModelType_invalidJob_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(
                ALICE.getName().fullName, "Janitor -s accept", VALID_STATUS);

        assertThrows(IllegalValueException.class, Job.MESSAGE_CONSTRAINTS, () ->
                application.toModelType(ADDRESS_BOOK));
    }
}
