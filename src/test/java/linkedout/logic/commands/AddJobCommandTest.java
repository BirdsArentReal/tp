package linkedout.logic.commands;

import static linkedout.logic.commands.CommandTestUtil.assertCommandFailure;
import static linkedout.logic.commands.CommandTestUtil.assertCommandSuccess;
import static linkedout.testutil.Assert.assertThrows;
import static linkedout.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import linkedout.model.Model;
import linkedout.model.ModelManager;
import linkedout.model.UserPrefs;
import linkedout.model.application.Job;

public class AddJobCommandTest {

    private static final Job ENGINEER = new Job("Software Engineer");

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddJobCommand(null));
    }

    @Test
    public void execute_newJob_success() {
        AddJobCommand command = new AddJobCommand(ENGINEER);
        String expectedMessage = String.format(AddJobCommand.MESSAGE_SUCCESS, ENGINEER);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addJob(ENGINEER);

        assertCommandSuccess(command, model, new CommandResult(expectedMessage, ENGINEER), expectedModel);
        assertTrue(model.hasJob(ENGINEER));
    }

    @Test
    public void execute_duplicateJob_throwsCommandException() {
        model.addJob(ENGINEER);
        AddJobCommand command = new AddJobCommand(new Job("Software Engineer"));

        assertCommandFailure(command, model, AddJobCommand.MESSAGE_DUPLICATE_JOB);
    }

    @Test
    public void equals() {
        AddJobCommand addEngineer = new AddJobCommand(ENGINEER);
        AddJobCommand addAnalyst = new AddJobCommand(new Job("Data Analyst"));

        assertTrue(addEngineer.equals(addEngineer));
        assertTrue(addEngineer.equals(new AddJobCommand(new Job("Software Engineer"))));
        assertFalse(addEngineer.equals(addAnalyst));
        assertFalse(addEngineer.equals(null));
        assertFalse(addEngineer.equals(1));
    }

    @Test
    public void toStringMethod() {
        AddJobCommand command = new AddJobCommand(ENGINEER);
        String expected = AddJobCommand.class.getCanonicalName() + "{job=" + ENGINEER + "}";
        assertEquals(expected, command.toString());
    }
}
