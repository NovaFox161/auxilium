package me.xaanit.auxilium.commands;

import java.util.List;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;

import me.xaanit.auxilium.GlobalConstants;
import me.xaanit.auxilium.interfaces.ICommand;
import me.xaanit.auxilium.util.Enums.CommandType;
import me.xaanit.auxilium.util.Util;
import sx.blah.discord.api.IShard;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IReaction;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

public class Botinfo implements ICommand {

  private String name = "botinfo";

  Emoji[] pages = new Emoji[] {EmojiManager.getForAlias("zero"), EmojiManager.getForAlias("one"),
      EmojiManager.getForAlias("two"), EmojiManager.getForAlias("three"),
      EmojiManager.getForAlias("four")};

  public Botinfo() {}

  @Override
  public String getCommmandName() {
    return this.name;
  }

  @Override
  public List<IRole> getRoles() {
    return null;
  }

  @Override
  public CommandType getType() {
    return CommandType.INFO;
  }

  @Override
  public String helpText() {
    return "Returns more info on the bot.";
  }

  @Override
  public String arguments() {
    return "None";
  }

  public void runCommand(IUser user, IChannel channel, IReaction reaction, IMessage message) {
    IGuild guild = channel.getGuild();
    guild.getID();
    if (reaction != null) {

      if (equals(0, reaction)) {
        moduleNavigation(user, channel, message);
        return;
      }
      if (equals(1, reaction)) {
        modulePatreon(user, channel, message);
        return;
      }

      if (equals(2, reaction)) {
        moduleBasicInfo(user, channel, message);
        return;
      }

      if (equals(3, reaction)) {
        moduleSupport(user, channel, message);
        return;
      }
      if (equals(4, reaction)) {
        moduleOtherbots(user, channel, message);
        return;
      }
    }

    EmbedBuilder em = Util.basicEmbed("basic", GlobalConstants.CLIENT_PICTURE,
        "Botinfo - Navigation", "", "Requested by: " + Util.getNameAndDescrim(user));
    appendNaviInfo(em);
    IMessage m = Util.sendMessage(channel, em.build());
    Emoji[] toAdd = getEmojiList(0);
    Util.addReaction(m, toAdd);

  }

  public boolean moduleNavigation(IUser user, IChannel channel, IMessage message) {
    EmbedBuilder em = Util.basicEmbed("basic", GlobalConstants.CLIENT_PICTURE,
        "Botinfo - Navigation", "", message.getEmbedded().get(0).getFooter().getText());
    appendNaviInfo(em);
    Emoji[] toAdd = getEmojiList(0);
    Util.removeAllReactions(message);
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Util.editMessage(message, em.build());
    Util.addReaction(message, toAdd);
    return true;
  }

  public boolean modulePatreon(IUser user, IChannel channel, IMessage message) {
    EmbedBuilder em = Util.basicEmbed("basic", GlobalConstants.CLIENT_PICTURE, "Botinfo - Patreon",
        "", message.getEmbedded().get(0).getFooter().getText());
    em.withDesc(
        "First off, thank you for wanting to donate to my patreon! It can be found [here.](https://www.patreon.com/xaanit)");
    appendNaviInfo(em);
    Emoji[] toAdd = getEmojiList(1);
    Util.removeAllReactions(message);
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Util.editMessage(message, em.build());
    Util.addReaction(message, toAdd);
    return true;
  }

  public boolean moduleBasicInfo(IUser user, IChannel channel, IMessage message) {
    IShard shard = message.getGuild().getShard();
    EmbedBuilder em = Util.basicEmbed("basic", GlobalConstants.CLIENT_PICTURE, "Botinfo - Basic",
        "", message.getEmbedded().get(0).getFooter().getText());
    em.appendField("Current Ping [Shard " + shard.getInfo()[0] + "]",
        shard.getResponseTime() + " ms", true);
    em.appendField("Version", Util.readConfig("version"), true);
    em.appendField("Size [Shard" + shard.getInfo()[0] + "]",
        shard.getGuilds().size() + " [" + shard.getUsers().size() + " users]", false);
    em.appendField("Current Dev",
        Util.getNameAndDescrim(GlobalConstants.client.getUserByID(Util.readConfig("dev"))), false);
    appendNaviInfo(em);
    Emoji[] toAdd = getEmojiList(2);
    Util.removeAllReactions(message);
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Util.editMessage(message, em.build());
    Util.addReaction(message, toAdd);
    return true;
  }

  public boolean moduleSupport(IUser user, IChannel channel, IMessage message) {
    EmbedBuilder em = Util.basicEmbed("basic", GlobalConstants.CLIENT_PICTURE, "Botinfo - Support",
        "", message.getEmbedded().get(0).getFooter().getText());
    em.withDesc(
        "Need support with Auxilium? Have ideas? Found a bug? My support server can be found [here (click me)!](https://discord.gg/wewb82H)");
    appendNaviInfo(em);
    Emoji[] toAdd = getEmojiList(3);
    Util.removeAllReactions(message);
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Util.editMessage(message, em.build());
    Util.addReaction(message, toAdd);

    return true;
  }

  public boolean moduleOtherbots(IUser user, IChannel channel, IMessage message) {
    EmbedBuilder em = Util.basicEmbed("basic", GlobalConstants.CLIENT_PICTURE,
        "Botinfo - Other bots", "", message.getEmbedded().get(0).getFooter().getText());
    em.withDesc(
        "»» [Tatsumaki](https://www.tatsumaki.xyz/profile) - This is an all around amazing bot.\n"
            + "»» [Dyno]() - This is good for music\n"
            + "»» [DisCal](https://www.cloudcraftgaming.com/discal/) - This is a very good bot for organising events.");
    appendNaviInfo(em);
    Emoji[] toAdd = getEmojiList(4);
    Util.removeAllReactions(message);
    try {
      Thread.sleep(350);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Util.editMessage(message, em.build());
    Util.addReaction(message, toAdd);

    return true;
  }

  public String getNavigationInfo() {
    return "»» 0 - Navigation page.\n" + "»» 1 - Patreon information.\n"
        + "»» 2 - Basic bot information.\n"
        + "»» 3 - Support server information\n»» 4 - Bots I suggest";
  }

  public void appendNaviInfo(EmbedBuilder e) {
    e.appendField("Navigation", getNavigationInfo(), false);
  }

  public boolean equals(int page, IReaction r) {
    return r.getUnicodeEmoji().getHtmlHexadecimal().equals(Util.pageHexadecimal(page));
  }

  public Emoji[] getEmojiList(int pageToLeaveOut) {
    Emoji[] arr = new Emoji[pages.length];
    for (int i = 0; i < pages.length; i++) {
      if (i != pageToLeaveOut) {
        int j = 0;
        for (int k = 0; k < arr.length; k++)
          if (arr[k] == null)
            j = k;
        arr[j] = pages[i];
      }
    }
    return arr;
  }

}
