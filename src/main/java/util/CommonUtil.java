package util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;

public class CommonUtil {
	public static String hash(String value) {
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

	public static boolean checkLogin(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		Object user = session.getAttribute("userId");
		if (user == null) {
			try {
				res.sendRedirect("login");
				return false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public static boolean isLogined(HttpServletRequest req) {
		HttpSession session = req.getSession();
		return session.getAttribute("userId") != null;
	}

	public static boolean isJoinRoom(HttpServletRequest req, HttpServletResponse res, int roomId) {
		if (!checkLogin(req, res)) {
			return false;
		}

		HttpSession session = req.getSession();
		int userId = (int) session.getAttribute("userId");
		AccountDao accountDao = new AccountDao();
		return accountDao.checkJoin(userId, roomId);
	}
}
