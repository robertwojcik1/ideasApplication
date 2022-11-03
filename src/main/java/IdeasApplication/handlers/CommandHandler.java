package IdeasApplication.handlers;

import IdeasApplication.QuitIdeasApplicationException;
import IdeasApplication.input.UserInputCommand;

public interface CommandHandler {
    void handle(UserInputCommand command) throws Throwable;

    boolean supports(String name);
}
