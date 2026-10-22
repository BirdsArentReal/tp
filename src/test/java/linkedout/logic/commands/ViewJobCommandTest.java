package linkedout.logic.commands;

import static linkedout.logic.commands.CommandTestUtil.assertCommandSuccess;
import static linkedout.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import linkedout.model.Model;
import linkedout.model.ModelManager;
import linkedout.model.UserPrefs;
import linkedout.model.application.Job;
import linkedout.model.application.JobDescriptionContainsKeywordPredicate;

public class ViewJobCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_showsJobsWithoutChangingThem() {
        model.addJob(new Job("Software Engineer"));
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(new ViewJobCommand(), model,
                CommandResult.showJobs(ViewJobCommand.MESSAGE_SUCCESS), expectedModel);
    }

    @Test
    public void execute_keyword_listsMatchingJobs() {
        model.addJob(new Job("Janitor"));
        model.addJob(new Job("Data Analyst"));
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredJobList(new JobDescriptionContainsKeywordPredicate("jan"));

        String expectedMessage = String.format(ViewJobCommand.MESSAGE_JOBS_LISTED, 1);
        assertCommandSuccess(new ViewJobCommand("jan"), model,
                CommandResult.showJobs(expectedMessage), expectedModel);
        assertEquals(List.of(new Job("Janitor")), model.getFilteredJobList());
    }
}
