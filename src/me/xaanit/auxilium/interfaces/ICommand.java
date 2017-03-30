package me.xaanit.auxilium.interfaces;

import java.util.List;

import me.xaanit.auxilium.util.Enums.CommandType;
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
  public List<IRole> getRoles();

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
