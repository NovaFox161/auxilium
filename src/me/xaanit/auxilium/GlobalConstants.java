package me.xaanit.auxilium;

import me.xaanit.auxilium.objects.Config;
import me.xaanit.auxilium.objects.Guild;
import sx.blah.discord.api.IDiscordClient;

import java.util.HashMap;
import java.util.Map;

public class GlobalConstants {

  public static IDiscordClient client;

  public static final String PATH = System.getProperty("user.dir") + "\\";

  public static final String CLIENT_PICTURE = "http://i.imgur.com/c2t3307.jpg";
  public static String XAANIT_ID = "233611560545812480";

  public static Map<String, Guild> guilds = new HashMap<>();
  public static Config CONFIG;
}