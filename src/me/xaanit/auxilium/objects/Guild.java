package me.xaanit.auxilium.objects;

import java.util.ArrayList;
import java.util.List;

import me.xaanit.auxilium.commands.Botinfo;
import me.xaanit.auxilium.interfaces.ICommand;

public class Guild {

  private String prefix = "+";
  private String id = "";
  private boolean devOverride = true;
  private boolean crashReports = true;
  private List<ICommand> commands = new ArrayList<ICommand>();


  public Guild() {
    commands.add(new Botinfo());
 //   commands.add(new Help());
  }
  
  public ICommand getCommand(String com) {
    for(ICommand c : commands)
      if(c.getCommmandName().equalsIgnoreCase(com))
        return c;
    return null;
  }

}
