package linkedout.logic.commands;

import static linkedout.logic.commands.CommandTestUtil.assertCommandFailure;
import static linkedout.logic.commands.CommandTestUtil.assertCommandSuccess;
import static linkedout.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static linkedout.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static linkedout.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import linkedout.commons.core.index.Index;
import linkedout.model.Model;
import linkedout.model.ModelManager;
import linkedout.model.UserPrefs;
import linkedout.model.application.Job;

public class DeleteJobCommandTest {

    private static final Job ENGINEER = new Job("Software Engineer");
    private static final Job ANALYST = new Job("Data Analyst");

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addJob(ENGINEER);
        model.addJob(ANALYST);
    }

    @Test
    public void execute_validIndex_success() {
        DeleteJobCommand command = new DeleteJobCommand(INDEX_FIRST_PERSON);
        String expectedMessage = String.format(DeleteJobCommand.MESSAGE_DELETE_JOB_SUCCESS, ENGINEER);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteJob(ENGINEER);

        assertCommandSuccess(command, model, CommandResult.showJobs(expectedMessage), expectedModel);
        assertFalse(model.hasJob(ENGINEER));
        assertTrue(model.hasJob(ANALYST));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getJobList().size() + 1);
        DeleteJobCommand command = new DeleteJobCommand(outOfBoundIndex);

        assertCommandFailure(command, model,
                String.format(DeleteJobCommand.MESSAGE_INVALID_INDEX, model.getJobList().size()));
    }

    @Test
    public void execute_filteredList_deletesDisplayedJob() {
        model.updateFilteredJobList(job -> job.toString().contains("Data"));
        DeleteJobCommand command = new DeleteJobCommand(INDEX_FIRST_PERSON);
        String expectedMessage = String.format(DeleteJobCommand.MESSAGE_DELETE_JOB_SUCCESS, ANALYST);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredJobList(job -> job.toString().contains("Data"));
        expectedModel.deleteJob(ANALYST);

        assertCommandSuccess(command, model, CommandResult.showJobs(expectedMessage), expectedModel);
        assertTrue(model.hasJob(ENGINEER));
        assertFalse(model.hasJob(ANALYST));
    }

    @Test
    public void execute_emptyList_throwsCommandException() {
        Model emptyModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        DeleteJobCommand command = new DeleteJobCommand(INDEX_FIRST_PERSON);

        assertCommandFailure(command, emptyModel, DeleteJobCommand.MESSAGE_EMPTY_LIST);
    }

    @Test
    public void equals() {
        DeleteJobCommand deleteFirst = new DeleteJobCommand(INDEX_FIRST_PERSON);
        DeleteJobCommand deleteSecond = new DeleteJobCommand(INDEX_SECOND_PERSON);

        assertTrue(deleteFirst.equals(deleteFirst));
        assertTrue(deleteFirst.equals(new DeleteJobCommand(INDEX_FIRST_PERSON)));
        assertFalse(deleteFirst.equals(deleteSecond));
        assertFalse(deleteFirst.equals(null));
        assertFalse(deleteFirst.equals(1));
    }
}
