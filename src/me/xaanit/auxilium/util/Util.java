package me.xaanit.auxilium.util;

import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import me.xaanit.auxilium.GlobalConstants;
import sx.blah.discord.util.EmbedBuilder;

public class Util {

	/* Files */
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

	/* General */
	public static EmbedBuilder basicEmbed() {
		return null;
	}

}
