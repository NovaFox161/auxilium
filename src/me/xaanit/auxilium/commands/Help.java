package me.xaanit.auxilium.commands;

import java.util.ArrayList;
import java.util.List;

import me.xaanit.auxilium.interfaces.ICommand;
import me.xaanit.auxilium.util.Enums.CommandType;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;

public class Help implements ICommand {

  private String name = "help";

  public Help() {}

  @Override
  public String getCommmandName() {
    return this.name;
  }

  @Override
  public List<IRole> getRoles() {
    return new ArrayList<IRole>();
  }

  @Override
  public CommandType getType() {
    return CommandType.INFO;
  }

  @Override
  public String helpText() {
    return "Returns info on a command!";
  }

  @Override
  public String arguments() {
    return "help <command/list>";
  }

  public void runCommmand(String[] args, IUser user, IChannel channel) {
    boolean a = args[1].equals("botinfo") ? moduleBotinfo(user, channel) : false;
    if (!a)
      return;


  }

  public boolean moduleBotinfo(IUser user, IChannel channel) {

    return true;
  }

}
