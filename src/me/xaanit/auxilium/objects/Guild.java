package me.xaanit.auxilium.objects;

import java.util.ArrayList;
import java.util.List;

import me.xaanit.auxilium.interfaces.ICommand;
import me.xaanit.auxilium.util.Util;

public class Guild {

  private String prefix = "+";
  private String id = "";
  private boolean devOverride = true;
  private boolean crashReports = true;
  private List<Command> commands = new ArrayList<Command>();


  public Guild(String id) {
    this.id = id;
    ICommand[] arr = Util.getCommandList();
    for (ICommand c : arr)
      this.commands.add(new Command(c.getCommmandName()));
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
    ICommand[] coms = Util.getCommandList();
    for (int i = 0; i < coms.length; i++)
      if (coms[i].getCommmandName().equalsIgnoreCase(com))
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
