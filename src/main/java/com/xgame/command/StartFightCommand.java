package com.xgame.command;

import com.xgame.GameKey;
import com.xgame.comman.MessageBus;
import com.xgame.comman.MessageBusFactory;
import com.xgame.entity.Character;
import com.xgame.factory.ServiceFactory;
import com.xgame.message.FightAction;
import com.xgame.message.ListAllCharacters;
import com.xgame.message.LogMessage;
import com.xgame.service.CharacterService;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;

public class StartFightCommand implements Command {

  private final MessageBus       messageBus;
  private final CharacterService characterService;

  public StartFightCommand(MessageBus messageBus, CharacterService characterService) {
    this.messageBus       = messageBus;
    this.characterService = characterService;
  }

  public StartFightCommand() {
    this(MessageBusFactory.getDefaultInstance(), ServiceFactory.getCharacterInstance());
  }

  @Override
  public GameKey commandKey() {
    return GameKey.FIGHT;

  }

  @Override
  public void execute() {
    Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);

    messageBus.send(LogMessage.of("Playing... "));
    messageBus.send(LogMessage.of("Choose character u want to  play against by entering it's name for the list below... "));
    messageBus.send(new ListAllCharacters());

   messageBus.send(LogMessage.read());
    String              characterName     = in.nextLine();
    Optional<Character> characterOptional = characterService.findCharacter(characterName);

    while (characterOptional.isEmpty()) {
      messageBus.send(LogMessage.of("character name is wrong try correct one."));
     messageBus.send(LogMessage.read());
      characterName     = in.nextLine();
      characterOptional = characterService.findCharacter(characterName);
    }

    Character character = characterOptional.get();

    // fighting starts here


    boolean isPlaying = true;

    while (isPlaying) {

      messageBus.send(LogMessage.of("You are playing now..."));
      messageBus.send(LogMessage.of("Press any key fro 'a' to 'Z' to fight or '0' to go back."));

     messageBus.send(LogMessage.read());
      String s = in.nextLine();

      if ('0' == s.charAt(0)) {
        isPlaying = false;
      } else {
        messageBus.send(new FightAction(s.charAt(0), character));
      }

    }


  }

  private Optional<Character> validateName(String name) {
    if (name != null && !name.isBlank()) {
      return characterService.findCharacter(name);
    }
    return Optional.empty();
  }

}
