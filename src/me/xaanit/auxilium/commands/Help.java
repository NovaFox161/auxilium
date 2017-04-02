package me.xaanit.auxilium.commands;

import me.xaanit.auxilium.GlobalConstants;
import me.xaanit.auxilium.interfaces.ICommand;
import me.xaanit.auxilium.objects.Role;
import me.xaanit.auxilium.util.Enums.CommandType;
import me.xaanit.auxilium.util.Util;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

import java.util.ArrayList;
import java.util.List;

public class Help implements ICommand {

  private String name = "help";


  public Help() {}

  @Override
  public String getCommandName() {
    return this.name;
  }

  @Override
  public List<Role> getRoles(IGuild guild) {
    return new ArrayList<>();
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

  public void runCommand(String[] args, IUser user, IChannel channel) {

    if (args.length > 1)
      args[1] = args[1].toLowerCase();
    boolean a = args.length != 1 && (args[1].equals("botinfo") ? moduleBotinfo(user, channel)
            : args[1].equals("help") && moduleHelp(user, channel));

    if (a)
      return;

    ICommand[] commands = Util.getCommandList();
    CommandType[] types = new CommandType[] {CommandType.INFO};
    EmbedBuilder em = Util.basicEmbed("basic", GlobalConstants.CLIENT_PICTURE, "Help - List",
        user.getAvatarURL(), "Requested by: " + Util.getNameAndDescrim(user));
    for (CommandType t : types) {
      String content = "None";
      for (ICommand c : commands)
        if (c.getType() == t)
          if (content.equals("None"))
            content = "�� " + c.getCommandName() + "\n";
          else
            content += "�� " + c.getCommandName() + "\n";
      em.appendField(t.toString(), content, false);
    }
    Util.sendMessage(channel, em.build());
  }

  private boolean moduleBotinfo(IUser user, IChannel channel) {
    EmbedBuilder em = Util.basicEmbed("basic", GlobalConstants.CLIENT_PICTURE, "Help - Botinfo",
        user.getAvatarURL(), "Requested by: " + Util.getNameAndDescrim(user));
    em.appendField("Description", new BotInfo().helpText(), false);
    em.appendField("Arguments", new BotInfo().arguments(), false);
    Util.sendMessage(channel, em.build());
    return true;
  }

  private boolean moduleHelp(IUser user, IChannel channel) {
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