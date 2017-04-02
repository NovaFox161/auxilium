package me.xaanit.auxilium.commands;

import me.ialistannen.javadocbot.javadoc.JavadocManager;
import me.ialistannen.javadocbot.javadoc.model.JavadocClass;
import me.xaanit.auxilium.GlobalConstants;
import me.xaanit.auxilium.interfaces.ICommand;
import me.xaanit.auxilium.objects.Role;
import me.xaanit.auxilium.util.Enums.CommandType;
import me.xaanit.auxilium.util.Util;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.handle.obj.IEmbed.IEmbedAuthor;
import sx.blah.discord.handle.obj.IEmbed.IEmbedField;
import sx.blah.discord.handle.obj.IEmbed.IEmbedFooter;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
import java.util.Collection;
import java.util.List;

public class Dev implements ICommand {

  private String name = "dev";

  public Dev() {}

  @Override
  public String getCommandName() {
    return this.name;
  }

  @Override
  public List<Role> getRoles(IGuild guild) {
    return null;
  }

  @Override
  public CommandType getType() {
    return CommandType.DEV;
  }

  @Override
  public String helpText() {
    return "Lots of dev commands.";
  }

  @Override
  public String arguments() {
    return "Lots";
  }


  public void runCommand(String[] args, IUser user, IChannel channel, IMessage m) {
    IGuild guild = channel.getGuild();
    String[] devIDs = new String[] {"233611560545812480", "130510525770629121"};
    boolean isDev = false;
    for (String str : devIDs)
      if (user.getID().equals(str))
        isDev = true;
    if (!isDev) {
      EmbedBuilder em = Util.basicEmbed("Error", user.getAvatarURL(), Util.getNameAndDescrim(user),
          guild.getIconURL(), "Only developers can run this command!");
      em.withDesc("You are not a developer!");
      Util.sendMessage(channel, em.build());
      return;
    }

    boolean a = args.length != 1 && (args[1].equalsIgnoreCase("forcesave") ? moduleForceSave(user, channel)
            : args[1].equalsIgnoreCase("guilds") ? moduleGuilds(user, channel)
            : args[1].equalsIgnoreCase("quote") ? moduleQuote(args, user, channel, m)
            : args[1].equalsIgnoreCase("delete") ? moduleDelete(args, user, channel)
            : args[1].equalsIgnoreCase("class") && moduleJavadocClass(args, user, channel));

    if (a)
      return;


    EmbedBuilder em = Util.basicEmbed("basic", user.getAvatarURL(), "Dev - List",
        GlobalConstants.CLIENT_PICTURE, "Requested By: " + Util.getNameAndDescrim(user));
    em.withDesc(
        "�� `forcesave` - Save every guild.\n�� `guilds` - List every guild.\n�� `quote` - Quotes a message.\n�� `delete` - Deletes the bot's messages (Providing a string of Message IDs)");
    Util.sendMessage(channel, em.build());

  }


  private boolean moduleForceSave(IUser user, IChannel channel) {
    for (String key : GlobalConstants.guilds.keySet()) {
      Util.save(GlobalConstants.guilds.get(key));
    }
    EmbedBuilder em = Util.basicEmbed("basic", user.getAvatarURL(), "Dev - Force save",
        GlobalConstants.CLIENT_PICTURE,
        GlobalConstants.client.getGuilds().size() + " guilds saved.");
    em.withDesc("Guild saving done!");
    Util.sendMessage(channel, em.build());

    return true;
  }

  private boolean moduleGuilds(IUser user, IChannel channel) {
    String res = "";
    for (IGuild g : GlobalConstants.client.getGuilds()) {
      res += "�� " + g.getName() + "\n���� " + Util.getNameAndDescrim(g.getOwner()) + "\n\n";
      if (res.length() >= 1050)
        break;
    }
    EmbedBuilder em = Util.basicEmbed("basic", user.getAvatarURL(), "Dev - Guilds",
        GlobalConstants.CLIENT_PICTURE, GlobalConstants.client.getGuilds().size() + " guilds.");
    em.withDesc(res);
    Util.sendMessage(channel, em.build());

    return true;
  }

  private boolean moduleQuote(String[] args, IUser user, IChannel channel, IMessage m) {
    Util.deleteMessage(m, channel);
    IMessage message = channel.getGuild().getMessageByID(args[2]);
    if (message == null) {
      EmbedBuilder em = Util.basicEmbed("error", channel.getGuild().getIconURL(), "Error",
          user.getAvatarURL(), "Requested by: " + Util.getNameAndDescrim(user));
      em.withDesc("This message is either invalid, or does not exist in this guild!");
      Util.sendMessage(channel, em.build());
      return true;
    }
    EmbedBuilder em = Util.basicEmbed("basic", message.getAuthor().getAvatarURL(),
        Util.getNameAndDescrim(message.getAuthor()), message.getGuild().getIconURL(),
        "#" + message.getChannel().getName() + " | Quoted by: " + Util.getNameAndDescrim(user));
    String s = getContent(message);
    if (s.equals("ATTACHMENTS_FOUND"))
      em.withThumbnail(message.getAttachments().get(0).getUrl());
    else
      em.withDesc(s);
    Util.sendMessage(channel, em.build());
    return true;

  }

  private boolean moduleDelete(String[] args, IUser user, IChannel channel) {

    for (String str : args) {
      if (str.replaceAll("[0-9]", "").equals("")) {
        IMessage message = channel.getMessageByID(str);
        if (message != null) {
          if (message.getAuthor().getID().equals("292875568947396609")) {
            Util.deleteMessage(message, channel);
          }
        }
      }
    }
    EmbedBuilder em = Util.basicEmbed("basic", user.getAvatarURL(), Util.getNameAndDescrim(user),
        channel.getGuild().getIconURL(), "Requested by: " + Util.getNameAndDescrim(user));
    em.withDesc((args.length - 2) + " messages deleted.");
    Util.sendMessage(channel, em.build());
    return true;
  }

  private boolean moduleJavadocClass(String[] args, IUser user, IChannel channel) {
    if (args.length == 2) {
      EmbedBuilder em = Util.basicEmbed("error", channel.getGuild().getIconURL(), "Dev - Javadoc",
          user.getAvatarURL(), "Requested By: " + Util.getNameAndDescrim(user));
      em.withDesc("Missing class name!");
      Util.sendMessage(channel, em.build());
      return true;
    }
    args[2] = stripFormatting(args[2]);
    JavadocManager manager = new JavadocManager();
    String name = args[2];
    Collection<JavadocClass> classes = manager.getClass(name);
    if (classes.size() < 1) {
      EmbedBuilder em = Util.basicEmbed("error", channel.getGuild().getIconURL(), "Dev - Javadoc",
          user.getAvatarURL(), "Requested By: " + Util.getNameAndDescrim(user));
      em.withDesc("No classes found by the name: **" + name + "**");
      Util.sendMessage(channel, em.build());
      return true;
    }


    JavadocClass javadocClass = classes.iterator().next();
    EmbedBuilder em = Util.basicEmbed("basic", getIconUrlForClass(javadocClass),
        javadocClass.getNameWithModifiers(), user.getAvatarURL(),
        "Requested by: " + Util.getNameAndDescrim(user));
    em.withAuthorUrl(javadocClass.getUrl());
    em.appendField("Class hierarchy", trimToSize(javadocClass.getExtendsImplements(), 2048), false);
    em.appendField("Description", trimToSize(javadocClass.getDescription(), 2048), false);
    Util.sendMessage(channel, em.build());
    return true;
  }

  private String getContent(IMessage m) {
    String res = "";

    if (m.getAttachments().size() > 0)
      res = "ATTACHMENTS_FOUND";
    if (m.getEmbeds().size() > 0) {
      IEmbed e = m.getEmbeds().get(0);

      IEmbedAuthor a = e.getAuthor();
      Color c = e.getColor();
      IEmbedFooter f = e.getFooter();
      if (a != null) {
        res += a.getIconUrl() == null ? "Author Icon: None\n"
            : "[Author Icon](" + a.getIconUrl() + ")" + "\n";
        res += a.getName() == null ? "Author Name: None\n" : "Author Name: " + a.getName() + "\n";
        res +=
            a.getUrl() == null ? "Author URL: None\n" : "[Author URL](" + a.getUrl() + ")" + "\n";
      }
      res += "Color: [" + c.getRed() + ", " + c.getGreen() + ", " + c.getBlue() + "]\n";
      res += "Description: " + (e.getDescription() == null ? "None" : e.getDescription()) + "\n";
      res += "Title: " + (e.getTitle() == null ? "None" : e.getTitle()) + "\n";
      res += "Title URL: " + (e.getUrl() == null ? "None" : e.getUrl()) + "\n";
      if (f != null) {
        res += f.getIconUrl() == null ? "Footer Icon: None"
            : "[Footer Icon](" + f.getIconUrl() + ")\n";
        res += "Footer Text: " + (f.getText() == null ? "None" : f.getText()) + "\n";
      }
      res += "Timestamp: " + Util.readableTime(m.getCreationDate()) + "\n";
      int i = 0;
      for (IEmbedField ef : e.getEmbedFields()) {
        res += "Field Title [ID: " + i + "]: " + ef.getName() + "\n\n";
        res += "Field Text [ID: " + i + "]: " + ef.getValue() + "\n\n";
        i++;
      }

    }

    if (res.equals(""))
      res = m.getContent() + "\n\nTimestamp: " + Util.readableTime(m.getCreationDate());
    return res;
  }


  /**
   * Trims a String to a given size
   *
   * @param input The input String
   * @param max The max size
   * @return The trimmed String, or the original if it was small enough
   */
  @SuppressWarnings("SameParameterValue")
  private static String trimToSize(String input, int max) {
    if (input.length() < max) {
      return input;
    }
    return input.substring(0, max - 3) + "...";
  }

  private String getIconUrlForClass(JavadocClass javadocClass) {
    if (javadocClass.getDeclaration().contains("abstract")) {
      return "https://www.jetbrains.com/help/img/idea/2016.3/classTypeAbstract.png";
    } else if (javadocClass.getType().equalsIgnoreCase("interface")) {
      return "https://www.jetbrains.com/help/img/idea/2016.3/classTypeInterface.png";
    } else if (javadocClass.getType().equalsIgnoreCase("enum")) {
      return "https://www.jetbrains.com/help/img/idea/2016.3/classTypeEnum.png";
    } else if (javadocClass.getDeclaration().contains("final")) {
      return "https://www.jetbrains.com/help/img/idea/2016.3/classTypeFinal.png";
    }
    return "https://www.jetbrains.com/help/img/idea/2016.3/classTypeJavaClass.png";
  }

  /**
   * Strips all formatting from the String
   *
   * @param input The input String
   * @return The String without any formatting
   */
  private static String stripFormatting(String input) {
    String strippedContent = input.replaceAll("[*`_~]", "");
    return strippedContent.replaceAll("\\[(.+?)]\\(.+?\\)", "$1");
  }
}