package me.xaanit.auxilium.objects;

import me.xaanit.auxilium.GlobalConstants;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IRole;

public class Role {

  private String guild;
  private String id;

  Role(IRole role, IGuild guild) {
    this.guild = guild.getID();
    this.id = role.getID();
  }

  public IGuild getGuild() {
    return GlobalConstants.client.getGuildByID(guild);
  }

  IRole getRole() {
    return getGuild().getRoleByID(id);
  }
}