package me.xaanit.auxilium.listeners;

import me.xaanit.auxilium.GlobalConstants;
import me.xaanit.auxilium.commands.Botinfo;
import me.xaanit.auxilium.util.Util;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionRemoveEvent;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.EmbedBuilder;

public class ReactionListener {


  @EventSubscriber
  public void onReactionRemove(ReactionRemoveEvent event) {
    IUser user = event.getUser();
    IChannel channel = event.getChannel();
    IReaction reaction = event.getReaction();
    IMessage message = event.getMessage();

    if (message.getEmbeds().size() > 0) {
      if (message.getEmbeds().get(0).getAuthor().getName().startsWith("Botinfo"))
        if (Util.isValidPageEmoji(reaction))
          new Botinfo().runCommand(user, channel, reaction, message);
    }

  }

  @EventSubscriber
  public void onReactionAdd(ReactionAddEvent event) {
    if (!Util.isOurUser(event.getAuthor()))
      return;
    if (Util.isOurUser(event.getUser()))
      return;
    IGuild guild = event.getGuild();
    if (!Util.botHasPerm(Permissions.ADD_REACTIONS, guild)) {
      EmbedBuilder em = Util.basicEmbed("249999", GlobalConstants.CLIENT_PICTURE, "Critical error!",
          guild.getIconURL(), "Please fix this immediately!");

      em.withDesc("Hello! My name is Auxilium. I am added to your server **" + guild.getName()
          + "**.\n\nI am missing the permission called \"Add Reactions\". This is a vital permission for "
          + "me due to me using reactions for \"pages\" on a lot of my commands.");
      Util.sendMessage(guild.getOwner().getOrCreatePMChannel(), em.build());
      return;
    }

    Util.removeReaction(event.getMessage(), event.getUser(), event.getReaction());

  }

}
