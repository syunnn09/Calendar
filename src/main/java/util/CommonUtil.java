package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Random;

public class CommonUtil {
	public String hash(String value) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			byte[] digest = md.digest(value.getBytes(StandardCharsets.UTF_8));

			HexFormat hex = HexFormat.of().withLowerCase();
			return hex.formatHex(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}

	public String generatePassword(int length) {
		String[] words = "abcdefghijklmnopqrstuvwxyz123456789".split("");
		Random random = new Random();

		String result = "";
		for (int i = 0; i < length; i++) {
			result += words[random.nextInt(words.length)];
		}

		return result;
	}

	public String generatePassword() {
		return generatePassword(7);
	}
}
