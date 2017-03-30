package me.xaanit.auxilium.util;

import java.awt.Color;
import java.io.File;
import java.io.FileReader;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.vdurmont.emoji.Emoji;

import me.xaanit.auxilium.GlobalConstants;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IReaction;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RequestBuffer;

public class Util {

	/* Files */
	/**
	 * Reads a JSON file for a value.
	 * 
	 * @param read
	 *            The JSON key to read
	 * @return The JSON value.
	 */
	public static String readConfig(String read) {
		JSONObject jsonObject = null;
		try {
			File aFile = new File(GlobalConstants.PATH + "config.json");

			FileReader fr = null;
			fr = new FileReader(aFile);

			JSONParser parser = new JSONParser();
			Object obj = null;
			obj = parser.parse(fr);

			fr.close();

			jsonObject = (JSONObject) obj;
		} catch (Exception ex) {
			// TODO: Error handling
		}
		String s = (String) jsonObject.get(read);
		return s;
	}

	/* Bot */
	/**
	 * Checks to see if the bot has a permission in a channel
	 * 
	 * @param perm
	 *            The permission to look for
	 * @param channel
	 *            The channel to look for (also gives all guild perms)
	 * @return True if you have the permission, false otherwise
	 */
	public static boolean botHasPerm(Permissions perm, IChannel channel) {
		return channel.getModifiedPermissions(GlobalConstants.client.getOurUser()).contains(perm);
	}

	/**
	 * Checks to see if the bot has a general permission.
	 * 
	 * @param perm
	 *            The permission to look for
	 * @param guild
	 *            The guild to look in
	 * @return True if we have it, false otherwise
	 */
	public static boolean botHasPerm(Permissions perm, IGuild guild) {
		return GlobalConstants.client.getOurUser().getPermissionsForGuild(guild).contains(perm);
	}

	/* General */
	/**
	 * Makes a basic EmbedBuilder for you.
	 * 
	 * @param hex
	 *            The hex code for the sidebar
	 * @param authorIcon
	 *            The author icon
	 * @param authorName
	 *            The author name
	 * @param footerIcon
	 *            The footer icon
	 * @param footerText
	 *            The footer text
	 * @return A new EmbedBuilder
	 */
	public static EmbedBuilder basicEmbed(String input, String authorIcon, String authorName, String footerIcon,
			String footerText) {
		EmbedBuilder em = new EmbedBuilder();
		if (!input.equals(""))
			em.withColor(hexToColor(usedColours(input)));
		if (!authorIcon.equals(""))
			em.withAuthorIcon(authorIcon);
		if (!authorName.equals(""))
			em.withAuthorName(authorName);
		if (!footerIcon.equals("") && !footerText.equals(""))
			em.withFooterIcon(footerIcon);
		if (!footerText.equals(""))
			em.withFooterText(footerText);
		return em;
	}

	/**
	 * Returns the hex for the most used colours (for consistency)
	 * 
	 * @param input
	 *            The word to convert to hex
	 * @return The hex (what they entered if not found)
	 */
	public static String usedColours(String input) {
		switch (input.toLowerCase()) {
			case "basic":
				return "249999";
			case "error":
				return "ad0c0c";
			default:
				return input;
		}
	}

	/**
	 * Returns an integer array of [R, G, B] from a provided hex
	 *
	 * @param hex
	 *            The hex to convert.
	 * 
	 * @return A new int[] of R G B values
	 */
	public static int[] hexToRGB(String hex) {
		hex = hex.replace("#", "");
		int r = Integer.valueOf(hex.substring(0, 2), 16);
		int g = Integer.valueOf(hex.substring(2, 4), 16);
		int b = Integer.valueOf(hex.substring(4, 6), 16);

		return new int[] { r, g, b };

	}

	/**
	 * Returns a new Color object from the provided hex.
	 * 
	 * @param hex
	 *            The hex to convert
	 * @return A color object.
	 */
	public static Color hexToColor(String hex) {
		int[] arr = hexToRGB(hex);
		return new Color(arr[0], arr[1], arr[2]);
	}

	/**
	 * Returns the Hexadecimal for reactions :zero: through :ten:
	 * 
	 * @param page
	 *            The page to look for
	 * @return The hexadecimal
	 */
	public static String pageHexadecimal(int page) {
		switch (page) {
			case 0:
				return "&#x30;&#x20e3;";
			case 1:
				return "&#x31;&#x20e3;";
			case 2:
				return "&#x32;&#x20e3;";
			case 3:
				return "&#x33;&#x20e3;";
			case 4:
				return "&#x34;&#x20e3;";
			case 5:
				return "&#x35;&#x20e3;";
			case 6:
				return "&#x36;&#x20e3;";
			case 7:
				return "&#x37;&#x20e3;";
			case 8:
				return "&#x38;&#x20e3;";
			case 9:
				return "&#x39;&#x20e3;";
			default:
				return "&#x1f51f;";
		}
	}

	/**
	 * Returns whether or not the user in question is Auxilium
	 * 
	 * @param user
	 *            The user to look for
	 * @return True if it is; false otherwise
	 */
	public static boolean isOurUser(IUser user) {
		return user.getID().equals(GlobalConstants.client.getApplicationClientID());
	}

	/**
	 * Gets the username and discriminator of a user. (I.e xaanit#1703)
	 * 
	 * @param user
	 *            The user to get
	 * @return The username and Discriminator combo.
	 */
	public static String getNameAndDescrim(IUser user) {
		return user.getName() + "#" + user.getDiscriminator();
	}

	/* Message Management */

	/**
	 * Deletes a specified IMessage
	 * 
	 * @param message
	 *            The message to delete
	 * @param channel
	 *            The channel it's in, to avoid infinite recursion.
	 */
	public static void deleteMessage(IMessage message, IChannel channel) {
		RequestBuffer.request(() -> {
			try {
				message.delete();
			} catch (DiscordException ex) {
				if (!channel.isPrivate())
					deleteMessage(message, channel);
			}
		});
	}

	/**
	 * Edits a specified message
	 * 
	 * @param message
	 *            The message to edit.
	 * @param str
	 *            The string to edit it with
	 * @param e
	 *            The EmbedObject to edit it with
	 */
	public static void editMessage(IMessage message, String str, EmbedObject e) {
		RequestBuffer.request(() -> {
			try {
				message.edit(str, e);
			} catch (DiscordException ex) {
				if (isOurUser(message.getAuthor()))
					editMessage(message, str, e);
			}
		});
	}

	/**
	 * Edits a specified message
	 * 
	 * @param message
	 *            The message to edit
	 * @param str
	 *            The string to edit it with
	 */
	public static void editMessage(IMessage message, String str) {
		RequestBuffer.request(() -> {
			try {
				message.edit(str);
			} catch (DiscordException ex) {
				if (isOurUser(message.getAuthor()))
					editMessage(message, str);
			}
		});
	}

	/**
	 * Edits a specified message
	 * 
	 * @param message
	 *            The message to edit
	 * @param e
	 *            The EmbedObject to edit it with
	 */
	public static void editMessage(IMessage message, EmbedObject e) {
		RequestBuffer.request(() -> {
			try {
				message.edit(e);
			} catch (DiscordException ex) {
				if (isOurUser(message.getAuthor()))
					editMessage(message, e);
			}
		});
	}

	/**
	 * Sends a message to the specified channel
	 * 
	 * @param channel
	 *            The channel to send to
	 * @param str
	 *            The string to send
	 * @param em
	 *            The Embed to send
	 * @return The message
	 */
	public static IMessage sendMessage(IChannel channel, String str, EmbedObject em) {
		return RequestBuffer.request(() -> {
			try {
				return channel.sendMessage(str, em);
			} catch (DiscordException ex) {
				// Since D4J throws a 403 instead of an error when you can't DM
				// a user.
				if (!channel.isPrivate())
					sendMessage(channel, str, em);
			} catch (MissingPermissionsException e) {
				EnumSet<Permissions> perms = EnumSet.noneOf(Permissions.class);
				perms.add(Permissions.SEND_MESSAGES);
				throw new MissingPermissionsException("I can not send this message!", perms);
			}
			return null;
		}).get();
	}

	/**
	 * Sends a message to the specified channel
	 *
	 * @param channel
	 *            The channel to send to.
	 * @param str
	 *            The string to send
	 * @return The message
	 */
	public static IMessage sendMessage(IChannel channel, String str) {
		return RequestBuffer.request(() -> {
			try {
				return channel.sendMessage(str);
			} catch (DiscordException ex) {
				// Since D4J throws a 403 instead of an error when you can't DM
				// a user.
				if (!channel.isPrivate())
					sendMessage(channel, str);
			} catch (MissingPermissionsException e) {
				EnumSet<Permissions> perms = EnumSet.noneOf(Permissions.class);
				perms.add(Permissions.SEND_MESSAGES);
				throw new MissingPermissionsException("I can not send this message!", perms);
			}
			return null;
		}).get();
	}

	/**
	 * Sends a message to the specified channel
	 * 
	 * @param channel
	 *            The channel to send to
	 * @param em
	 *            The Embed to send
	 * @return The message
	 */
	public static IMessage sendMessage(IChannel channel, EmbedObject em) {
		return RequestBuffer.request(() -> {
			try {
				return channel.sendMessage(em);
			} catch (DiscordException ex) {
				// Since D4J throws a 403 instead of an error when you can't DM
				// a user.
				if (!channel.isPrivate())
					sendMessage(channel, em);
			} catch (MissingPermissionsException e) {
				EnumSet<Permissions> perms = EnumSet.noneOf(Permissions.class);
				perms.add(Permissions.SEND_MESSAGES);
				throw new MissingPermissionsException("I can not send this message!", perms);
			}
			return null;
		}).get();
	}

	/**
	 * Adds a reaction to the message
	 * 
	 * @param m
	 *            The message to add to
	 * @param es
	 *            The reaction list to add
	 */
	public static void addReaction(IMessage m, Emoji... es) {
		final AtomicInteger i = new AtomicInteger();
		RequestBuffer.request(() -> {
			for (; i.get() < es.length; i.incrementAndGet()) {
				if (es[i.intValue()] != null) {
					m.addReaction(es[i.intValue()]);
				}
			}

		});
	}

	/**
	 * Removes a reactions from a message
	 * 
	 * @param m
	 *            The message to remove from
	 * @param r
	 *            The reaction to remove
	 */
	public static void removeReaction(IMessage m, IReaction r) {
		RequestBuffer.request(() -> {
			try {
				m.removeReaction(r);
			} catch (DiscordException ex) {
				removeReaction(m, r);
			} catch (MissingPermissionsException e1) {
				throw e1;
			}
		});
	}

	/**
	 * Removes a user's reaction from a message
	 * 
	 * @param m
	 *            The message to remove from
	 * @param u
	 *            The user to remove from
	 * @param r
	 *            The reaction to remove
	 */
	public static void removeReaction(IMessage m, IUser u, IReaction r) {
		RequestBuffer.request(() -> {
			try {
				m.removeReaction(u, r);
			} catch (DiscordException ex) {
				removeReaction(m, u, r);
			} catch (MissingPermissionsException e1) {
				throw e1;
			}
		});
	}

	/**
	 * Removes all reactions from a message
	 * 
	 * @param m
	 *            The message to remove all reactions from
	 */
	public static void removeAllReactions(IMessage m) {
		RequestBuffer.request(() -> {
			try {
				m.removeAllReactions();
			} catch (DiscordException ex) {
				removeAllReactions(m);
			} catch (MissingPermissionsException e1) {
				throw e1;
			}
		});
	}

	/**
	 * Sends a message if the user has no permission to run a command
	 * 
	 * @param user
	 *            The user who ran the command
	 * @param channel
	 *            The channel to send it in
	 * @param roles
	 *            The roles needed to run the command.
	 * @return The message sent
	 */
	public static IMessage noPerms(IUser user, IChannel channel, List<String> roles) {
		EmbedBuilder em = basicEmbed("error", user.getAvatarURL(), getNameAndDescrim(user),
				channel.getGuild().getIconURL(), "User has too low of a rank!");
		String list = "";
		for (String str : roles) {
			list += "�� " + channel.getGuild().getRoleByID(str).getName() + "\n";
		}
		em.withDesc("You have too low of a rank! You need one of the following ranks to use the command:\n" + list);
		return sendMessage(channel, em.build());
	}

	/**
	 * Sends a message if the user is missing arguments for a command
	 * 
	 * @param user
	 *            The user who ran the command
	 * @param channel
	 *            The channel to send it in
	 * @param arguments
	 *            The arguments needed
	 * @return The message sent
	 */
	public static IMessage missingArguments(IUser user, IChannel channel, String arguments) {
		EmbedBuilder em = basicEmbed("error", user.getAvatarURL(), getNameAndDescrim(user),
				channel.getGuild().getIconURL(), "User has too few arguments");
		em.appendDesc("You are missing required arguments!\n\n" + arguments);
		return sendMessage(channel, em.build());
	}

	/**
	 * Notifies a user if the bot is missing a permission on the guild.
	 * 
	 * @param channel
	 *            The channel to send it in
	 * @param perm
	 *            The permission the bot is missing
	 * @return The message sent
	 */
	public static IMessage botNeedsPerm(IChannel channel, Permissions perm) {
		EmbedBuilder em = basicEmbed("error", GlobalConstants.CLIENT_PICTURE, "Permissions Error",
				GlobalConstants.client.getUserByID(GlobalConstants.XAANIT_ID).getAvatarURL(),
				"I require this permission to run this command!");
		em.withDesc(
				"I am missing a permission! Please notify the server admins (or owner) if you believe this is in error!");
		em.appendField("Permission", perm.toString(), false);
		return sendMessage(channel, em.build());
	}

	/* Role Management */

	public static Permissions getPerm(String input) {
		switch (input.toLowerCase()) {
			case "add_reactions":
				return Permissions.ADD_REACTIONS;
			case "administrator":
				return Permissions.ADMINISTRATOR;
			case "attach_files":
				return Permissions.ATTACH_FILES;
			case "ban":
				return Permissions.BAN;
			case "change_nickname":
				return Permissions.CHANGE_NICKNAME;
			case "create_invite":
				return Permissions.CREATE_INVITE;
			case "embed_links":
				return Permissions.EMBED_LINKS;
			case "kick":
				return Permissions.KICK;
		}
		return null;
	}

	/**
	 * Changes a role's name
	 * 
	 * @param role
	 *            The role to edit
	 * @param name
	 *            The name to set it to
	 */
	public static void changeRoleName(IRole role, String name) {
		RequestBuffer.request(() -> {
			try {
				role.changeName(name);
			} catch (DiscordException ex) {
				changeRoleName(role, name);
			}
		});
	}

	/**
	 * Changes a role's colour
	 * 
	 * @param role
	 *            The role to edit
	 * @param hex
	 *            The hex to change it to
	 */
	public static void changeRoleColour(IRole role, String hex) {
		RequestBuffer.request(() -> {
			try {
				role.changeColor(hexToColor(hex));
			} catch (DiscordException ex) {
				changeRoleColour(role, hex);
			}
		});
	}

	/**
	 * Changes if a role is displayed differently than online members or not
	 * 
	 * @param role
	 *            The role to edit
	 * @param hoisted
	 *            If it should be or not
	 */
	public static void changeRoleHoisted(IRole role, boolean hoisted) {
		RequestBuffer.request(() -> {
			try {
				role.changeHoist(hoisted);
			} catch (DiscordException ex) {
				changeRoleHoisted(role, hoisted);
			}
		});
	}

	/**
	 * Changes if a role is mentionable or not
	 * 
	 * @param role
	 *            The role to edit
	 * @param mentionable
	 *            If it should be mentionable or not
	 */
	public static void changeRoleMentionable(IRole role, boolean mentionable) {
		RequestBuffer.request(() -> {
			try {
				role.changeMentionable(mentionable);
			} catch (DiscordException ex) {
				changeRoleMentionable(role, mentionable);
			}
		});
	}

	/**
	 * Changes a role's permissions
	 * 
	 * @param role
	 *            The role to edit
	 * @param perms
	 *            The permissions to change it to
	 */
	public static void changeRolePermissions(IRole role, EnumSet<Permissions> perms) {
		RequestBuffer.request(() -> {
			try {
				role.changePermissions(perms);
			} catch (DiscordException ex) {
				changeRolePermissions(role, perms);
			}
		});
	}

}

// RequestBuffer.request(() -> {try {} catch (DiscordException ex) {}});
