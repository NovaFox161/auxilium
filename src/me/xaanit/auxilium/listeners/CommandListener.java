package me.xaanit.auxilium.listeners;

import me.xaanit.auxilium.GlobalConstants;
import me.xaanit.auxilium.commands.BotInfo;
import me.xaanit.auxilium.commands.Define;
import me.xaanit.auxilium.commands.Dev;
import me.xaanit.auxilium.commands.Help;
import me.xaanit.auxilium.util.Util;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

import java.util.Arrays;

public class CommandListener {

  @SuppressWarnings("unused")
  @EventSubscriber
  public void onUserCommand(MessageReceivedEvent event) {
    IUser user = event.getAuthor();
    IChannel channel = event.getChannel();
    String m = event.getMessage().toString();
    IMessage message = event.getMessage();
    String[] args = m.split("\\s");

    if (m.equals("+logout")) {
      if (!user.getID().equals("233611560545812480"))
        return;
      Util.sendMessage(channel, "Logging out.....");
      GlobalConstants.client.logout();
    }

    if (m.startsWith("+hex")) {
      if (!user.getID().equals("233611560545812480"))
        return;
      Util.sendMessage(channel, Arrays.toString(Util.hexToRGB(args[1])));
      return;
    }

        //m.toString()... really xaanit??? Just like string to string method you like....
    if (m.startsWith("+"))
      getCommand(args, user, channel, message);
  }

  private void getCommand(String[] args, IUser user, IChannel channel, IMessage message) {
    switch (args[0].toLowerCase().replaceFirst("\\+", "")) {
      case "botinfo":
        new BotInfo().runCommand(user, channel, null, message);
        break;
      case "help":
        new Help().runCommand(args, user, channel);
        break;
      case "dev":
        new Dev().runCommand(args, user, channel, message);
        break;
      case "define":
        new Define().runCommand(args, user, channel);
        break;
    }
  }
}