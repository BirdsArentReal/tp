package linkedout.testutil;

import static linkedout.logic.parser.CliSyntax.OPTION_EMAIL_REQ;
import static linkedout.logic.parser.CliSyntax.OPTION_NAME_REQ;
import static linkedout.logic.parser.CliSyntax.OPTION_PHONE_REQ;

import linkedout.logic.commands.AddCommand;
import linkedout.logic.commands.EditCommand.EditPersonDescriptor;
import linkedout.model.person.Person;

/**
 * Provides helper methods for constructing person commands.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns add-command options for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append("-n ").append(person.getName().fullName).append(" ");
        sb.append("-p ").append(person.getPhone().value).append(" ");
        sb.append("-e ").append(person.getEmail().value).append(" ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(OPTION_NAME_REQ).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(OPTION_PHONE_REQ).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(OPTION_EMAIL_REQ).append(email.value).append(" "));
        return sb.toString();
    }
}
