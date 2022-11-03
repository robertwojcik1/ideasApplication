package IdeasApplication.handlers;

abstract class BaseCommandHandler implements CommandHandler {

    public boolean supports(String name) {
        return getCommandName().equals(name);
    }

    protected abstract String getCommandName();
}
