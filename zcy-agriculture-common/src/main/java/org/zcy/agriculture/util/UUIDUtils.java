package org.zcy.agriculture.util;

import java.security.SecureRandom;
import java.util.UUID;

public class UUIDUtils {
	static SecureRandom random = new SecureRandom();
	private long sequence = 0L;
	private long lastTimestamp = -1L;
	private static final UUIDUtils instance = new UUIDUtils();


	/**
	 * 获取数字型的code
	 * @return
	 */
	public static Long getCode() {
		return Long.parseLong(getId(6));
	}


	/**
	 * 获取唯一ID值
	 * 
	 * @param numberSize
	 * @return
	 */
	private synchronized String nextId(int numberSize) {
		long timestamp = System.currentTimeMillis() / 1000;
		// 如果上次生成时间和当前时间相同,在同一毫秒内
		if (lastTimestamp == timestamp) {
			sequence = sequence + 1;
			// 判断是否溢出,如果当前秒内 超出10的numberSize次方， 就等待下一秒
			if (sequence >= Math.pow(10, numberSize)) {
				// 自旋等待到下一秒
				timestamp = tilNextMillis(lastTimestamp);
				sequence = random.nextInt((int) Math.pow(10, numberSize) - 1);
				sequence = sequence > 700 ? sequence - 300 : sequence;
			}
		} else {
			// 如果和上次生成时间不同,重置sequence，就是下一毫秒开始，sequence计数重新 开始累加
			sequence = random.nextInt((int) Math.pow(10, numberSize) - 1);
			sequence = sequence > 700 ? sequence - 300 : sequence;
		}
		lastTimestamp = timestamp;
		return timestamp + repairString(sequence + "", numberSize, '0');
	}

	private long tilNextMillis(long lastTimestamp) {
		long timestamp = System.currentTimeMillis() / 1000;
		while (timestamp <= lastTimestamp) {
			timestamp = System.currentTimeMillis() / 1000;
		}
		return timestamp;
	}

	/**
	 * 获取1-9位随机数
	 * 
	 * @param numberSize
	 * @return
	 */
	public static int random(int numberSize) {
		int intValue = Double.valueOf(Math.pow(10, numberSize)).intValue();
		int nextInt = random.nextInt(intValue - intValue / 10) + intValue / 10;
		return nextInt;
	}

	/**
	 * 获取唯一ID
	 * 
	 * @param num
	 * @return
	 */
	public static String getId(int num) {
		return instance.nextId(num);
	}

	/**
	 * 如果字符串s长度小于len，则字符串s前面补c<br>
	 * 如果字符串s长度大于len，则字符串s取后面长度为len的字符<br>
	 * 保证字符串长度永远数len
	 */
	public static String repairString(String s, int len, char c) {
		int cou = 0;
		if (s == null || "".equals(s)) {
			cou = len;
			s = "";
		} else {
			cou = s.length();
			if (cou > len) {
				s = s.substring(cou - len);
				cou = 0;
			} else {
				cou = len - cou;
			}
		}
		for (int i = 0; i < cou; i++) {
			s = c + s;
		}
		return s;
	}

	/**
	 * 获取UUID
	 * 
	 * @return
	 */
	public static String getRandomPwd() {
		return UUID.randomUUID().toString().trim().replaceAll("-", "");
	}

	public static void main(String[] args) {
		System.out.println(UUIDUtils.generateUuid());
		System.out.println(UUIDUtils.getId(6));
	}

	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7",
			"8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	/**
	 * 生成8位UUID
	 * @return
	 */
	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();
	}

	/**
	 * 生成32位UUID
	 * @return
	 */
	public static String generateUuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
