package me.xaanit.auxilium.commands;

import me.xaanit.auxilium.interfaces.ICommand;
import me.xaanit.auxilium.objects.Role;
import me.xaanit.auxilium.util.Enums.CommandType;
import me.xaanit.auxilium.util.Util;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

import java.util.List;

public class Define implements ICommand {

  private String name = "define";

  public Define() {}

  @Override
  public String getCommmandName() {
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
    return "Defines a word";
  }

  @Override
  public String arguments() {
    return "define <word>";
  }


  public void runCommand(String[] args, IUser user, IChannel channel) {
    if (args.length == 1) {
      EmbedBuilder em = Util.basicEmbed("error", user.getAvatarURL(), "Missing arguments",
          channel.getGuild().getIconURL(), "Requested by: " + Util.getNameAndDescrim(user));
      em.withDesc("Missing arguments: " + arguments().replace(this.name, ""));
      Util.sendMessage(channel, em.build());
      return;
    }

    String word = args[1];

    if (word.equalsIgnoreCase("recursion")) {
      EmbedBuilder em = Util.basicEmbed("basic", user.getAvatarURL(), "Define - " + word,
          channel.getGuild().getIconURL(), "Requested By: " + Util.getNameAndDescrim(user));
      em.withDesc("Use `" + Util.getGuild(channel.getGuild()).getPrefix()
          + "define recursion` for more info on this word.");
      Util.sendMessage(channel, em.build());
      return;
    }
    
    

  }
}
