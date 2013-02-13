/*
 * 2013-Feb-12
 * Bobi Pu, bobi.pu@usc.edu
 * This code is for Evernote's code challenge, #4
 * 
 * */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class MultiplyExceptSelf {	
	public static void main(String[] args) throws Exception{
		new MultiplyExceptSelf().run();
	}
	
	long[] input;
	long product;
	BufferedReader reader;
	BufferedWriter writer;
	
	void run() throws IOException {
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int size = Integer.parseInt(reader.readLine());
			input = new long[size];
			
			for (int i = 0; i < size; i++) {
				input[i] = Long.parseLong(reader.readLine());
			}
			
			for (int i = 0; i < size; i++) {
				product = 1;
				for (int j = 0; j < size; j++) {
					if (j != i) {
						product *= input[j];
					}
				}
				writer.write(product + "\n");
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
	
	MultiplyExceptSelf() {
		product = 1;
		input = null;
		reader = null;
		writer = null;
	}
}
