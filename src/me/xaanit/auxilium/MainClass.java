package me.xaanit.auxilium;

import me.xaanit.auxilium.listeners.BotListener;
import me.xaanit.auxilium.listeners.CommandListener;
import me.xaanit.auxilium.listeners.ReactionListener;
import me.xaanit.auxilium.util.Util;
import sx.blah.discord.api.ClientBuilder;

public class MainClass {

	public static void main(String[] args) {

		String token = Util.readConfig("token");
		GlobalConstants.client = new ClientBuilder().withRecommendedShardCount().withToken(token).build();
		registerListeners();
		GlobalConstants.client.login();
	}

	public static void registerListeners() {
		Object[] o = new Object[] {new BotListener(), new ReactionListener(), new CommandListener()};
		for (Object obj : o)
			GlobalConstants.client.getDispatcher().registerListener(obj);
	}

}
