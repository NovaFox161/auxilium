package me.xaanit.auxilium.commands;

import java.util.List;

import me.xaanit.auxilium.interfaces.ICommand;
import me.xaanit.auxilium.util.Enums.CommandType;
import sx.blah.discord.handle.obj.IRole;

public class Dev implements ICommand {

  private String name = "dev";

  public Dev() {}

  @Override
  public String getCommmandName() {
    return this.name;
  }

  @Override
  public List<IRole> getRoles() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CommandType getType() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String helpText() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String arguments() {
    // TODO Auto-generated method stub
    return null;
  }



}
