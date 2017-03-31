package me.xaanit.auxilium.objects;

import java.util.ArrayList;
import java.util.List;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;

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
