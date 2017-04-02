package me.xaanit.auxilium.commands;

import me.xaanit.auxilium.interfaces.ICommand;
import me.xaanit.auxilium.objects.Role;
import me.xaanit.auxilium.util.Enums.CommandType;
import me.xaanit.auxilium.util.Util;
import sx.blah.discord.handle.obj.IGuild;

import java.util.List;

public class UserInfo implements ICommand {

  private String name = "userinfo";

  public UserInfo() {}

  @Override
  public String getCommandName() {
    return this.name;
  }

  @Override
  public List<Role> getRoles(IGuild guild) {
    return Util.getGuild(guild).getCommand(this.name).getRoles();
  }

  @Override
  public CommandType getType() {
    return CommandType.INFO;
  }

  @Override
  public String helpText() {
    return "Returns info on a user.";
  }

  @Override
  public String arguments() {
    return "userinfo [user]";
  }
}