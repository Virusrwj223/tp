package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.Phone;

/**
 * Represents a command to delete a seller in the seller management system.
 */
public class DeleteSellerCommand extends DeleteClientCommand {
    /** The command word for this specific action. */
    public static final String COMMAND_WORD = "deleteseller";
    public static final String PARAMETER_PHONE = String.format("%sPHONE", PREFIX_PHONE);
    public static final String MESSAGE_ARGUMENTS = "phoneNumber: %1$s";
    public static final String MESSAGE_USAGE = String.format(
            "%s: Deletes a seller from the address book.\nParameters: %s\n%s",
            COMMAND_WORD,
            DeleteClientCommand.CLIENT_PARAMETERS,
            DeleteClientCommand.CLIENT_RESTRICTIONS
    );

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Seller: %1$s";

    /**
     * Constructs a {@code DeleteSellerCommand} with the specified phone number.
     *
     * @param phoneNumber The phone number of the buyer to delete.
     */
    public DeleteSellerCommand(Phone phoneNumber) {
        super(phoneNumber);
    }
    /**
     * Executes the delete seller command and removes the seller from the model.
     *
     * @param model The model which the command should operate on.
     * @return A {@code CommandResult} object representing the result of the delete operation.
     * @throws CommandException If the buyer cannot be found or deleted.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // Search for the person with the specified phone number
        Client personToDelete = model.getFilteredClientList().stream()
                .filter(Client::isSeller)
                .filter(person -> person.getPhone().equals(phoneNumber))
                .findFirst().orElseThrow(() -> new CommandException(String.format("Seller not found. ", phoneNumber)));
        model.deleteClient(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }
    /**
     * Checks if this {@code DeleteSellerCommand} is equal to another object.
     *
     * @param other The object to compare with this command.
     * @return {@code true} if the other object is an instance of {@code DeleteSellerCommand} with the same phone number
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof DeleteSellerCommand)) {
            return false;
        }
        // state check
        DeleteSellerCommand d = (DeleteSellerCommand) other;
        return this.phoneNumber.equals(d.phoneNumber);
    }
}