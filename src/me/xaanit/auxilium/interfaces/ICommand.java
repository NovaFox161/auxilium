package me.xaanit.auxilium.interfaces;

import me.xaanit.auxilium.objects.Role;
import me.xaanit.auxilium.util.Enums.CommandType;
import sx.blah.discord.handle.obj.IGuild;

import java.util.List;

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
   * @param guild The guild to look at
   * @return Roles that can run command.
   */
  public List<Role> getRoles(IGuild guild);

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



}
