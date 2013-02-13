/*
 * 2013-Feb-12
 * Bobi Pu, bobi.pu@usc.edu
 * This code is for Evernote's code challenge, #2
 * 
 * */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

class TopFour {	
	public static void main(String[] args) throws Exception{
		new TopFour().run();
	}
	
	int[] array;
	BufferedReader reader;
	BufferedWriter writer;
	
	void run() throws IOException {
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int size = Integer.parseInt(reader.readLine());
			array = new int[size < 4 ? size : 4];
			for (int i = 0; i < array.length; i++) {
				array[i] = Integer.MIN_VALUE;
			}
			
			while (reader.ready()) {
				int input = Integer.parseInt(reader.readLine());
				if (input > array[0]) {
					array[0] = input;
					//sort into ascending order
					Arrays.sort(array);
				}
			}
			for (int i = array.length -1; i >= 0; i--) {
				writer.write(array[i] + "\n");
				writer.flush();
			}
		} catch (Exception e) {
			if (e instanceof NumberFormatException) {
				System.err.println("Please input correct number.");
			} else {
				System.err.println(e.getMessage());
			}
			e.printStackTrace();
		} finally {
			reader.close();
			writer.close();
		}
		
	}
	
	TopFour() {
		array = null;
		reader = null;
		writer = null;
	}
}
