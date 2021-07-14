package com.xgame.command;

import com.xgame.GameKey;
import com.xgame.comman.MessageBus;
import com.xgame.comman.MessageBusFactory;
import com.xgame.entity.Character;
import com.xgame.message.CreateNewCharacter;
import com.xgame.message.LogMessage;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;

public class CreateNewCharacterCommand implements Command {

  private final MessageBus messageBus;

  public CreateNewCharacterCommand(MessageBus messageBus) {
    this.messageBus = messageBus;
  }

  public CreateNewCharacterCommand() {
    this(MessageBusFactory.getDefaultInstance());
  }

  @Override
  public GameKey commandKey() {
    return GameKey.NEW_CHAR;
  }

  @Override
  public void execute() {
    Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);

    messageBus.send(LogMessage.of("Adding new character..."));

    boolean isNotValidCharacter = true;

    while (isNotValidCharacter) {

      messageBus.send(LogMessage.of("Enter character name"));

     messageBus.send(LogMessage.read());
      var characterName = in.nextLine();

      messageBus.send(LogMessage.of("Enter character power"));

     messageBus.send(LogMessage.read());
      var power = in.nextLine();

      isNotValidCharacter = validateCharacterData(characterName, power)
              .map(newCharacter -> {
                messageBus.send(new CreateNewCharacter(newCharacter));
                return false;
              })
              .orElse(true);

      messageBus.send(LogMessage.of(""));
    }


  }

  private Optional<Character> validateCharacterData(String name, String power) {

    if (name != null) {
      if (!name.isBlank()) {
        try {

          int p = Integer.parseInt(power);
          return Optional.of(new Character(name, p));

        } catch (NumberFormatException e) {
          messageBus.send(LogMessage.of(LogMessage.Log.ERROR, "invalid character data, please try again."));
        }
      }

    }
    return Optional.empty();
  }
}
