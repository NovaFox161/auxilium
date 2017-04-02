package me.xaanit.auxilium.objects;

import me.xaanit.auxilium.interfaces.ICommand;
import me.xaanit.auxilium.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Guild {

  private String prefix = "+";
  private String id = "";
  private boolean devOverride = true;
  private boolean crashReports = true;
  private List<Command> commands = new ArrayList<>();


  public Guild(String id) {
    this.id = id;
    ICommand[] arr = Util.getCommandList();
    for (ICommand c : arr)
      this.commands.add(new Command(c.getCommandName()));
  }

  public Command getCommand(String com) {
    for (Command c : commands)
      if (c.getName().equalsIgnoreCase(com))
        return c;
    return null;
  }

  public void addCommand(String com) {
    if (getCommand(com) != null)
      return;
    ICommand[] cmds = Util.getCommandList();
    for (ICommand cmd : cmds)
      if (cmd.getCommandName().equalsIgnoreCase(com))
        commands.add(new Command(com));
  }

  public String getPrefix() {
    return this.prefix;
  }

  public String getId() {
    return this.id;
  }

  public boolean getDevOverride() {
    return this.devOverride;
  }

  public boolean getCrashReports() {
    return this.crashReports;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public void setDevOverride(boolean devOverride) {
    this.devOverride = devOverride;
  }

  public void setCrashReports(boolean crashReports) {
    this.crashReports = crashReports;
  }

}
