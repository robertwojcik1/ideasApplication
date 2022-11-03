package IdeasApplication.handlers;

import IdeasApplication.dao.CategoryDao;
import IdeasApplication.dao.QuestionDao;
import IdeasApplication.input.UserInputCommand;
import IdeasApplication.model.Category;
import IdeasApplication.model.Question;

import java.util.List;
import java.util.logging.Logger;

public class QuestionCommandHandler extends BaseCommandHandler {

    private static Logger LOG = Logger.getLogger(QuestionCommandHandler.class.getName());
    public QuestionCommandHandler() {
        questionDao = new QuestionDao();
        categoryDao = new CategoryDao();
    }

    private QuestionDao questionDao;
    private CategoryDao categoryDao;
    private static final String COMMAND_NAME = "question";
    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public void handle(UserInputCommand command) throws Throwable {

        if(command.getAction()==null) {
            throw new IllegalArgumentException("action can't be null");
        }

        switch (command.getAction()) {
            case LIST:
                LOG.info("List of questions...");

                if(!command.getParam().isEmpty()) {
                    throw new IllegalArgumentException("question list doesn't support any additional params");
                }
                List<Question> questions = questionDao.findAll();
                questions.forEach(System.out::println);
                break;
            case ADD:
                LOG.info("Add question");

                if(command.getParam().size() != 2) {
                    throw new IllegalArgumentException("wrong command format. Check help for more information");
                }
                String categoryName = command.getParam().get(0);
                String questionName = command.getParam().get(1);

                Category category = (Category) categoryDao.findOne(categoryName)
                        .orElseThrow(() -> new IllegalArgumentException("Category not found:" + categoryName));

                questionDao.add(new Question(questionName, category));
                break;
            default:
                throw new IllegalArgumentException(String.format("Unknown action: %s from command: %s",
                        command.getAction(), command.getCommand()));
        }
    }
}

