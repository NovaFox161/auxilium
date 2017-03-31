package me.xaanit.auxilium.listeners;

import me.xaanit.auxilium.commands.Botinfo;
import me.xaanit.auxilium.commands.Help;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class CommandListener {


  @EventSubscriber
  public void onUserCommand(MessageReceivedEvent event) {
    IUser user = event.getAuthor();
    IChannel channel = event.getChannel();
    String m = event.getMessage().toString();
    IMessage message = event.getMessage();
    String[] args = m.split("\\s");



    if (m.toString().startsWith("+"))
      getCommand(args, user, channel, message);
  }

  public void getCommand(String[] args, IUser user, IChannel channel, IMessage message) {
    switch (args[0].toLowerCase().replaceFirst("\\+", "")) {
      case "botinfo":
        new Botinfo().runCommand(user, channel, null, message);
      case "help":
        new Help().runCommmand(args, user, channel);
    }
  }

}
