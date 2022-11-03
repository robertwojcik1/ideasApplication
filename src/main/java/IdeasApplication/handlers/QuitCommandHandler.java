package IdeasApplication.handlers;

import IdeasApplication.QuitIdeasApplicationException;
import IdeasApplication.input.UserInputCommand;

public class QuitCommandHandler extends BaseCommandHandler {
    public static final String COMMAND_NAME = "quit";

    @Override
    public void handle(UserInputCommand command) throws QuitIdeasApplicationException {

        throw new QuitIdeasApplicationException();
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}
