package IdeasApplication;

import IdeasApplication.handlers.*;
import IdeasApplication.input.UserInputCommand;
import IdeasApplication.input.UserInputManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IdeasApplication {

    public static void main(String[] args) {
        new IdeasApplication().start();
    }

    private static Logger LOG = Logger.getLogger(IdeasApplication.class.getName());
    private void start() {
        LOG.info("Starting app...");

        boolean applicationLoop = true;

        UserInputManager userInputManager = new UserInputManager();

        List<CommandHandler> handlers = new ArrayList<>();
        handlers.add(new HelpCommandHandler());
        handlers.add(new QuitCommandHandler());
        handlers.add(new CategoryCommandHandler());
        handlers.add(new QuestionCommandHandler());
        handlers.add(new AnswerCommandHandler());

        while (applicationLoop){
            try {
                UserInputCommand userInputCommand = userInputManager.nextCommand();
                LOG.info(userInputCommand.toString());

                Optional<CommandHandler> currentHandler = Optional.empty();
                for (CommandHandler handler : handlers) {
                    if (handler.supports(userInputCommand.getCommand())) {
                        currentHandler = Optional.of(handler);
                        break;
                    }
                }
                currentHandler
                        .orElseThrow(() -> new IllegalArgumentException("Unknown handler: " + userInputCommand.getCommand()))
                        .handle(userInputCommand);
            } catch (QuitIdeasApplicationException e) {
                LOG.info("Quit...");
                applicationLoop = false;
            }  catch (IllegalArgumentException e) {
                LOG.log(Level.WARNING, "validation exception" + e.getMessage());
            } catch (Exception e){
               LOG.log(Level.SEVERE, "Unknown error", e);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }
}
//TODO space handling in categories, questions and answers
//TODO save data to db
//TODO generic DAO
//TODO update and delete data
//TODO streams and lambdas implementation
//TODO web layer
