package me.xaanit.auxilium.commands;

import java.util.ArrayList;
import java.util.List;

import me.xaanit.auxilium.GlobalConstants;
import me.xaanit.auxilium.interfaces.ICommand;
import me.xaanit.auxilium.objects.Channel;
import me.xaanit.auxilium.objects.Role;
import me.xaanit.auxilium.util.Enums.CommandType;
import me.xaanit.auxilium.util.Util;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

public class Help implements ICommand {

  private String name = "help";
  private List<Channel> channels = new ArrayList<Channel>();
  private List<Role> roles = new ArrayList<Role>();

  public Help() {}

  @Override
  public String getCommmandName() {
    return this.name;
  }

  @Override
  public List<Role> getRoles() {
    return roles;
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
    return "help [command]";
  }


  @Override
  public void allowRole(IRole role) {
    boolean found = false;
    for (Role r : roles)
      if (r.getRole().getID().equals(role.getID()))
        found = true;
    if (!found)
      this.roles.add(new Role(role, role.getGuild()));
  }

  @Override
  public void denyRole(IRole role) {
    boolean found = false;
    for (Role r : roles)
      if (r.getRole().getID().equals(role.getID()))
        found = true;
    if (found)
      this.roles.remove(new Role(role, role.getGuild()));
  }

  @Override
  public void allowChannel(IChannel channel) {
    boolean found = false;
    for (Channel r : channels)
      if (r.getChannel().getID().equals(channel.getID()))
        found = true;
    if (!found)
      this.channels.add(new Channel(channel, channel.getGuild()));
  }

  @Override
  public void denyChannel(IChannel channel) {
    boolean found = false;
    for (Channel r : channels)
      if (r.getChannel().getID().equals(channel.getID()))
        found = true;
    if (found)
      this.channels.remove(new Channel(channel, channel.getGuild()));
  }



  public void runCommmand(String[] args, IUser user, IChannel channel) {

    if (args.length > 1)
      args[1] = args[1].toLowerCase();
    boolean a = args.length == 1 ? false
        : args[1].equals("botinfo") ? moduleBotinfo(user, channel)
            : args[1].equals("help") ? moduleHelp(user, channel) : false;

    if (a)
      return;

    ICommand[] commands = new ICommand[] {new Help(), new Botinfo()};
    CommandType[] types = new CommandType[] {CommandType.INFO};
    EmbedBuilder em = Util.basicEmbed("basic", GlobalConstants.CLIENT_PICTURE, "Help - List",
        user.getAvatarURL(), "Requested by: " + Util.getNameAndDescrim(user));
    for (CommandType t : types) {
      String content = "None";
      for (ICommand c : commands)
        if (c.getType() == t)
          if (content.equals("None"))
            content = "»» " + c.getCommmandName() + "\n";
          else
            content += "»» " + c.getCommmandName() + "\n";
      em.appendField(t.toString(), content, false);
    }
    Util.sendMessage(channel, em.build());
  }


  public boolean moduleBotinfo(IUser user, IChannel channel) {
    EmbedBuilder em = Util.basicEmbed("basic", GlobalConstants.CLIENT_PICTURE, "Help - Botinfo",
        user.getAvatarURL(), "Requested by: " + Util.getNameAndDescrim(user));
    em.appendField("Description", new Botinfo().helpText(), false);
    em.appendField("Arguments", new Botinfo().arguments(), false);
    Util.sendMessage(channel, em.build());
    return true;
  }

  public boolean moduleHelp(IUser user, IChannel channel) {
    EmbedBuilder em = Util.basicEmbed("basic", GlobalConstants.CLIENT_PICTURE, "Help - Help",
        user.getAvatarURL(), "Requested by: " + Util.getNameAndDescrim(user));
    em.appendField("Description", helpText(), false);
    em.appendField("Arguments", arguments(), false);
    em.appendField("Additional Info",
        "<> - Required Arguments on a command.\n[] - Optional Arguments on a command", false);
    Util.sendMessage(channel, em.build());
    return true;
  }
}
