package me.xaanit.auxilium.listeners;

import me.xaanit.auxilium.GlobalConstants;
import me.xaanit.auxilium.util.Util;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.shard.DisconnectedEvent;
import sx.blah.discord.handle.obj.IGuild;

public class BotListener {

  @EventSubscriber
  public void onLoad(ReadyEvent event) {
    for (IGuild g : event.getClient().getGuilds())
      GlobalConstants.guilds.put(g.getID(), Util.load(g));
    System.out.println("Guilds loaded.");

  }

  @EventSubscriber
  public void onDisconnect(DisconnectedEvent event) {
    Util.emergencySave();
  }

}
