package me.xaanit.auxilium.listeners;

import me.xaanit.auxilium.GlobalConstants;
import me.xaanit.auxilium.commands.Botinfo;
import me.xaanit.auxilium.commands.Help;
import me.xaanit.auxilium.objects.Guild;
import me.xaanit.auxilium.util.Util;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

public class CommandListener {


  @EventSubscriber
  public void onUserCommand(MessageReceivedEvent event) {
    IUser user = event.getAuthor();
    IChannel channel = event.getChannel();
    String m = event.getMessage().toString();
    IMessage message = event.getMessage();
    IGuild guild = event.getGuild();
    String[] args = m.split("\\s");


    if (m.startsWith("+dev")) {
      if (!user.getID().equals("233611560545812480"))
        return;

      if (args[1].equals("init")) {
        for (IGuild g : GlobalConstants.client.getGuilds()) {
          GlobalConstants.guilds.put(g.getID(), new Guild(g.getID()));
        }
      }

      if (args[1].equals("role")) {
        Util.getGuild(guild).getCommand(args[2]).allowRole(guild.getRoleByID(args[3]));
      }
      if (args[1].equals("channel")) {
        Util.getGuild(guild).getCommand(args[2]).allowChannel(guild.getChannelByID(args[3]));
      }

      if (args[1].equals("save")) {
        String res = "";
        for (String k : GlobalConstants.guilds.keySet()) {
          Util.save(GlobalConstants.guilds.get(k));
          res += GlobalConstants.client.getGuildByID(k).getName() + "\n";
        }

        EmbedBuilder em = Util.basicEmbed("basic", user.getAvatarURL(), "Guild saving",
            guild.getIconURL(), res.split("\n").length + " guilds saved");
        em.withDesc("Guilds Saved: \n" + res);
        Util.sendMessage(channel, em.build());
      }

      if (args[1].equals("load")) {
        for (IGuild g : GlobalConstants.client.getGuilds()) {
          GlobalConstants.guilds.put(event.getGuild().getID(), new Guild(event.getGuild().getID()));
        }

        System.out.println("Guilds loaded");
        System.out.println(GlobalConstants.guilds.containsKey(guild.getID()));
      }



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
