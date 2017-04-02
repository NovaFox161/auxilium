package me.xaanit.auxilium.objects;

import me.xaanit.auxilium.interfaces.ICommand;
import me.xaanit.auxilium.util.Util;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;

import java.util.ArrayList;
import java.util.List;

public class Command {

  private String name = "";

  private List<Channel> channels = new ArrayList<Channel>();
  private List<Role> roles = new ArrayList<Role>();



  public Command(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }


  public ICommand getCommand() {
    for (ICommand c : Util.getCommandList())
      if (c.getCommmandName().equalsIgnoreCase(name))
        return c;
    return null;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public List<IChannel> getChannels() {
    List<IChannel> list = new ArrayList<IChannel>();
    for (Channel c : channels)
      list.add(c.getChannel());
    return list;
  }

  public void allowRole(IRole role) {
    boolean found = false;
    for (Role r : roles)
      if (r.getRole().getID().equals(role.getID()))
        found = true;
    if (!found)
      this.roles.add(new Role(role, role.getGuild()));
  }

  public void denyRole(IRole role) {
    for (Role r : roles)
      if (r.getRole().getID().equals(role.getID()))
        this.roles.remove(r);
  }

  public void allowChannel(IChannel channel) {
    boolean found = false;
    for (Channel r : channels)
      if (r.getChannel().getID().equals(channel.getID()))
        found = true;
    if (!found)
      this.channels.add(new Channel(channel, channel.getGuild()));
  }

  public void denyChannel(IChannel channel) {
    for (Channel r : channels)
      if (r.getChannel().getID().equals(channel.getID()))
        this.channels.remove(r);
  }
}
