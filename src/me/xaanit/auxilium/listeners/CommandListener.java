package me.xaanit.auxilium.listeners;

import java.io.File;

import me.xaanit.auxilium.GlobalConstants;
import me.xaanit.auxilium.commands.Botinfo;
import me.xaanit.auxilium.commands.Help;
import me.xaanit.auxilium.objects.Guild;
import me.xaanit.auxilium.util.Util;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class CommandListener {
  public static Guild g = new Guild();


  @EventSubscriber
  public void onUserCommand(MessageReceivedEvent event) {
    IUser user = event.getAuthor();
    IChannel channel = event.getChannel();
    String m = event.getMessage().toString();
    IMessage message = event.getMessage();
    String[] args = m.split("\\s");

    // Test case to look at saving
    if (m.equals("+test")) {
   //   g.getCommand("help").allowRole(event.getGuild().getRolesByName("Developer").get(0));
      Util.save(g);
      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      Guild a =Util.load(new File(GlobalConstants.PATH + "test.json"));
      
   //   System.out.println(a.getCommand("help").getRoles().get(0).getRole().getName());
      
      return;
    }

    if (m.toString().startsWith("+"))
      getCommand(args, user, channel, message);
  }

  public void getCommand(String[] args, IUser user, IChannel channel, IMessage message) {
    switch (args[0].toLowerCase().replaceFirst("\\+", "")) {
      case "botinfo":
        new Botinfo().runCommand(user, channel, null, message);
        break;
      case "help":
        new Help().runCommmand(args, user, channel);
        break;
    }
  }

}
