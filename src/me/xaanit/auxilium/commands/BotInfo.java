package me.xaanit.auxilium.commands;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import me.xaanit.auxilium.GlobalConstants;
import me.xaanit.auxilium.interfaces.ICommand;
import me.xaanit.auxilium.objects.Role;
import me.xaanit.auxilium.util.Enums.CommandType;
import me.xaanit.auxilium.util.Util;
import sx.blah.discord.api.IShard;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.EmbedBuilder;

import java.util.ArrayList;
import java.util.List;

public class BotInfo implements ICommand {

  private String name = "botinfo";

  public BotInfo() {}

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
        moduleNavigation(message);
        return;
      }
      if (equals(1, reaction)) {
        modulePatreon(message);
        return;
      }

      if (equals(2, reaction)) {
        moduleBasicInfo(message);
        return;
      }

      if (equals(3, reaction)) {
        moduleSupport(message);
        return;
      }
      if (equals(4, reaction)) {
        moduleOtherBots(message);
        return;
      }
    }
    EmbedBuilder em = Util.basicEmbed("basic", GlobalConstants.CLIENT_PICTURE,
        "Botinfo - Navigation", "", "Requested by: " + Util.getNameAndDescrim(user));
    appendNavInfo(em);
    IMessage m = Util.sendMessage(channel, em.build());
    Emoji[] toAdd = getEmojiList(0);
    Util.addReaction(m, toAdd);

  }

  private boolean moduleNavigation(IMessage message) {
    EmbedBuilder em = Util.basicEmbed("basic", GlobalConstants.CLIENT_PICTURE,
        "Botinfo - Navigation", "", message.getEmbeds().get(0).getFooter().getText());
    appendNavInfo(em);
    Emoji[] toAdd = getEmojiList(0);
    Util.removeAllReactions(message);
    try {
      Thread.sleep(500); //Fuck you xaanit!
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Util.editMessage(message, em.build());
    Util.addReaction(message, toAdd);
    return true;
  }

  private boolean modulePatreon(IMessage message) {
    EmbedBuilder em = Util.basicEmbed("basic", GlobalConstants.CLIENT_PICTURE, "Botinfo - Patreon",
        "", message.getEmbeds().get(0).getFooter().getText());
    em.withDesc(
        "First off, thank you for wanting to donate to my patreon! It can be found [here.](https://www.patreon.com/xaanit)");
    appendNavInfo(em);
    Emoji[] toAdd = getEmojiList(1);
    Util.removeAllReactions(message);
    try {
      Thread.sleep(500); //Fuck you xaanit!
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Util.editMessage(message, em.build());
    Util.addReaction(message, toAdd);
    return true;
  }

  private boolean moduleBasicInfo(IMessage message) {
    IShard shard = message.getGuild().getShard();
    EmbedBuilder em = Util.basicEmbed("basic", GlobalConstants.CLIENT_PICTURE, "Botinfo - Basic",
        "", message.getEmbeds().get(0).getFooter().getText());
    em.appendField("Current Ping [Shard " + shard.getInfo()[0] + "]",
        shard.getResponseTime() + " ms", true);
    em.appendField("Version", GlobalConstants.CONFIG.getVersion(), true);
    em.appendField("Size [Shard" + shard.getInfo()[0] + "]",
        shard.getGuilds().size() + " [" + shard.getUsers().size() + " users]", false);
    em.appendField("Current Dev",
        Util.getNameAndDescrim(GlobalConstants.client.getUserByID(GlobalConstants.CONFIG.getDev())),
        false);
    appendNavInfo(em);
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

  private boolean moduleSupport(IMessage message) {
    EmbedBuilder em = Util.basicEmbed("basic", GlobalConstants.CLIENT_PICTURE, "Botinfo - Support",
        "", message.getEmbeds().get(0).getFooter().getText());
    em.withDesc(
        "Need support with Auxilium? Have ideas? Found a bug? My support server can be found [here (click me)!](https://discord.gg/wewb82H)");
    appendNavInfo(em);
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

  private boolean moduleOtherBots(IMessage message) {
    EmbedBuilder em = Util.basicEmbed("basic", GlobalConstants.CLIENT_PICTURE,
        "Botinfo - Other bots", "", message.getEmbeds().get(0).getFooter().getText());
    em.withDesc(
        "�� [Tatsumaki](https://www.tatsumaki.xyz/profile) - This is an all around amazing bot.\n"
            + "�� [Dyno]() - This is good for music\n"
            + "�� [DisCal](https://www.cloudcraftgaming.com/discal/) - This is a very good bot for organising events.");
    appendNavInfo(em);
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

  private String getNavigationInfo() {
    return "�� 0 - Navigation page.\n" + "�� 1 - Patreon information.\n"
        + "�� 2 - Basic bot information.\n"
        + "�� 3 - Support server information\n�� 4 - Bots I suggest";
  }

  private void appendNavInfo(EmbedBuilder e) {
    e.appendField("Navigation", getNavigationInfo(), false);
  }

  private boolean equals(int page, IReaction r) {
    return r.getUnicodeEmoji().getHtmlHexadecimal().equals(Util.pageHexadecimal(page));
  }

  private Emoji[] getEmojiList(int pageToLeaveOut) {
    Emoji[] pages = new Emoji[] {EmojiManager.getForAlias("zero"), EmojiManager.getForAlias("one"),
        EmojiManager.getForAlias("two"), EmojiManager.getForAlias("three"),
        EmojiManager.getForAlias("four")};
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