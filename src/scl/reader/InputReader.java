package scl.reader;

import java.io.*;

public class InputReader {
	public static int readInt() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = "";
		try {
			in = br.readLine();
		} catch(IOException ioe) {
			
		}
		int num = -1;
		try {
			num = Integer.parseInt(in);
		} catch (Exception e) {
			
		}
		return num;
	}
}
