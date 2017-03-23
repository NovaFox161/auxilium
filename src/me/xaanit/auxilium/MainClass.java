package me.xaanit.auxilium;

import me.xaanit.auxilium.util.Util;
import sx.blah.discord.api.ClientBuilder;

public class MainClass {

	public static void main(String[] args) {

		String token = Util.readConfig("token");
		GlobalConstants.client = new ClientBuilder().setMaxReconnectAttempts(100000).withToken(token).build();
		registerListeners();
		GlobalConstants.client.login();
	}

	public static void registerListeners() {
		Object[] o = new Object[] {};
		for (Object obj : o)
			GlobalConstants.client.getDispatcher().registerListener(obj);
	}

}
