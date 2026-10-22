package linkedout.logic.commands;

import static linkedout.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import linkedout.model.Model;
import linkedout.model.ModelManager;

public class ByeCommandTest {
    @Test
    public void execute_bye_exitsWithoutFeedbackOrModelChanges() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        assertCommandSuccess(new ByeCommand(), model,
                new CommandResult("", false, true), expectedModel);
    }
}
