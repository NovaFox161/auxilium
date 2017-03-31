package me.xaanit.auxilium.interfaces;

import java.util.List;

import me.xaanit.auxilium.objects.Role;
import me.xaanit.auxilium.util.Enums.CommandType;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;

public interface ICommand {



  /**
   * Getting the name of the command.
   * 
   * @return Command Name
   * 
   */
  public String getCommmandName();

  /**
   * Returns a list of roles who can run the command.
   * 
   * @return Roles that can run command.
   */
  public List<Role> getRoles();

  /**
   * Gets Command Type
   * 
   * @return Command type
   */
  public CommandType getType();

  /**
   * Gets the text for the help command.
   * 
   * @return The help text
   */
  public String helpText();

  /**
   * Get the arguments for the command.
   * 
   * @return The arguments
   */
  public String arguments();

  /**
   * Allows a role to run the command
   * 
   * @param role The role to allow
   */
  public void allowRole(IRole role);

  /**
   * Denies a role from using the command
   * 
   * @param role The role to deny
   */
  public void denyRole(IRole role);

  /**
   * Allows the command to be run in a channel
   * 
   * @param channel The channel to allow
   */
  public void allowChannel(IChannel channel);

  /**
   * Denis the command to be run in a channel
   * 
   * @param channel The channel to deny
   */
  public void denyChannel(IChannel channel);


}
