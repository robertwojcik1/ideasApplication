package input;

import IdeasApplication.input.UserInputCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserInputCommandTest {

    @Test
    void shouldBuildCorrectUserInputCommandWithMultipleParam() {
        //given
        String input = "command action param1 param2 param3";

        //when
        UserInputCommand userInputCommand = new UserInputCommand(input);

        //then
        Assertions.assertEquals("command", userInputCommand.getCommand());
        Assertions.assertEquals("action", userInputCommand.getAction());
        Assertions.assertLinesMatch(List.of("param1", "param2", "param3"), userInputCommand.getParam());

    }
}