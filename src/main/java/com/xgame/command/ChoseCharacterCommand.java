package com.xgame.command;

import com.xgame.GameKey;
import com.xgame.comman.MessageBus;
import com.xgame.comman.MessageBusFactory;
import com.xgame.entity.Character;
import com.xgame.factory.ServiceFactory;
import com.xgame.message.ChoseCharacter;
import com.xgame.message.ListAllCharacters;
import com.xgame.message.LogMessage;
import com.xgame.service.CharacterService;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;

public class ChoseCharacterCommand implements Command {

  private final MessageBus       messageBus;
  private final CharacterService characterService;

  public ChoseCharacterCommand(MessageBus messageBus, CharacterService characterService) {
    this.messageBus       = messageBus;
    this.characterService = characterService;
  }

  public ChoseCharacterCommand() {
    this(MessageBusFactory.getDefaultInstance(), ServiceFactory.getCharacterInstance());
  }

  @Override
  public GameKey commandKey() {
    return GameKey.CHOOSE_CHAR;
  }


  @Override
  public void execute() {
    Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);

    messageBus.send(LogMessage.of("Switching Characters..."));
    messageBus.send(LogMessage.of("List of all characters chose one of them by enter it's name : -"));
    messageBus.send(LogMessage.of("---------------------"));
    messageBus.send(new ListAllCharacters());
    messageBus.send(LogMessage.of("---------------------"));


    boolean nameIsNotCorrect = true;

    while (nameIsNotCorrect) {

      messageBus.send(LogMessage.of("Enter the character name ?"));
      messageBus.send(LogMessage.read());
      String name = in.nextLine();

      nameIsNotCorrect = validateName(name)
              .map(character -> {
                messageBus.send(new ChoseCharacter(character));
                return false;
              }).orElse(true);

    }
  }

  private Optional<Character> validateName(String name) {
    if (name != null && !name.isBlank()) {
      return characterService.findCharacter(name);
    }
    return Optional.empty();
  }

}
