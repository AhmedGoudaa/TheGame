package com.xgame;

import com.xgame.comman.MessageBus;
import com.xgame.comman.MessageBusFactory;
import com.xgame.command.Command;
import com.xgame.message.LogMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConsoleGameCommandsExecutor {

  private final Map<Character, Command> commandMap;
  private final MessageBus              messageBus;


  public ConsoleGameCommandsExecutor() {
    this.commandMap = new HashMap<>();
    this.messageBus = MessageBusFactory.getDefaultInstance();
    loadCommands();
    this.messageBus.loadHandlers();
  }

  private void loadCommands() {
    // load all the  implementations of Command
    // take a look at  src/main/resources/META-INF/services/
    ServiceLoader<Command> commandServiceLoader = ServiceLoader.load(Command.class);

    Map<Character, Command> comms = commandServiceLoader.stream()
            .map(ServiceLoader.Provider::get)
            .collect(Collectors.toMap(command -> command.commandKey().getKey(), Function.identity()));

    commandMap.putAll(comms);
  }


  /**
   * get command by command representative key and execute this command.
   *
   * @param commandKey representative command key
   * @return continue playing or not
   */
  public boolean getAndExecuteCommand(char commandKey) {

    // todo handle this case better
    if (GameKey.EXIT.getKey() == commandKey) {
      commandMap.get(commandKey).execute();

      return false;
    } else {

      Command command = commandMap.get(commandKey);
      if (command == null) {
        messageBus.send(LogMessage.of("choose one of the available keys"));
      } else {
        command.execute();
      }
    }
    printCommands();
    return true;
  }


  public void printCommands() {
    commandMap.forEach((gamekey, command) ->
            messageBus.send(LogMessage.of(String.format(command.commandKey().getCommandDescription(),command.commandKey().getKey()))));
  }

  public void printHelloMessage() {
    messageBus.send(LogMessage.of("********************* Welcome To the GAME *********************"));
  }


}
