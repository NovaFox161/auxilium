package me.xaanit.auxilium.objects;

import me.xaanit.auxilium.GlobalConstants;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;

public class Channel {

  private String guild;
  private String id;


  public Channel(IChannel channel, IGuild guild) {
    this.guild = guild.getID();
    this.id = channel.getID();
  }

  public IGuild getGuild() {
    return GlobalConstants.client.getGuildByID(guild);
  }

  public IChannel getChannel() {
    return getGuild().getChannelByID(id);
  }

}
