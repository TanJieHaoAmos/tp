package seedu.finclient.logic.parser;

import static seedu.finclient.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.finclient.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.finclient.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.finclient.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.finclient.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.finclient.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.finclient.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.finclient.logic.commands.AddCommand;
import seedu.finclient.logic.parser.exceptions.ParseException;
import seedu.finclient.model.person.Address;
import seedu.finclient.model.person.Email;
import seedu.finclient.model.person.Name;
import seedu.finclient.model.person.Person;
import seedu.finclient.model.person.PhoneList;
import seedu.finclient.model.person.Remark;
import seedu.finclient.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_REMARK, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_EMAIL, PREFIX_ADDRESS);

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        PhoneList phoneList = ParserUtil.parsePhoneList(argMultimap.getAllValues(PREFIX_PHONE));
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Remark remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Person person = new Person(name, phoneList, email, address, remark, tagList);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
