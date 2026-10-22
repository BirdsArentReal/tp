package linkedout.logic.parser;

import static linkedout.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static linkedout.logic.parser.CommandParserTestUtil.assertParseFailure;
import static linkedout.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static linkedout.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import linkedout.logic.commands.DeleteJobCommand;

public class DeleteJobCommandParserTest {

    private final DeleteJobCommandParser parser = new DeleteJobCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteJobCommand() {
        assertParseSuccess(parser, "1", new DeleteJobCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteJobCommand.MESSAGE_USAGE));
    }
}
