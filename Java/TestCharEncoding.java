import java.nio.charset.StandardCharsets;


public class TestCharEncoding {

	static String text1 = "abc";
	static String text2 = "ab我𤭢";
	public static void main(String[] args) throws Exception {
		
		byte[] bb1 = text2.getBytes(StandardCharsets.UTF_8);
		String utf8 = "ab" + '\u6211' + '\uD852' + '\uDF62';
		byte[] bb2 = utf8.getBytes(StandardCharsets.UTF_8);
		printByteArray(bb2);
		String utf16 = new String(bb2,StandardCharsets.UTF_16);
		byte[] bb3 = utf16.getBytes(StandardCharsets.UTF_16BE);
		printByteArray(bb3);
		for(int i=0; i<utf16.length(); ) {
			int codePoint = utf16.codePointAt(i);
			System.out.println("codePoint=" + Integer.toHexString(codePoint));
			char[] chs = Character.toChars(codePoint);
			System.out.println("chs=" + chs.length);
			String p = new String(chs);
			System.out.println("value=" + p);
//			System.out.println("byteLength=" + p.getBytes(StandardCharsets.UTF_16BE).length);
			i = i + chs.length;
			
		}
	}
	
	public static void printByteArray(byte[] arr) {
		
		for (int i=0; i<arr.length; i++){
			System.out.print(String.format("%02X ", arr[i]));
		}
		System.out.println();
	}
}