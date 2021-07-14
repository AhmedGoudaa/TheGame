package com.xgame;


import com.xgame.message.LogMessage;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

  private final ConsoleGameCommandsExecutor commandsExecutor;


  public Main() {
    this.commandsExecutor = new ConsoleGameCommandsExecutor();
  }

  public static void main(String[] args) {
    Main main = new Main();
    main.play();
//    Thread hook = new Thread(() -> MessageBusFactory.getDefaultInstance().send(new Shutdown()));
//    Runtime.getRuntime().addShutdownHook(hook);
  }

  private void play() {

    commandsExecutor.printCommands();

    Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

    boolean isPlaying = true;
    while (isPlaying) {
      System.out.println( LogMessage.read().getData());
      String s = scanner.nextLine();
      isPlaying = commandsExecutor.getAndExecuteCommand(s.charAt(0));
    }
  }
}
