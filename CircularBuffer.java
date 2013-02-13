/*
 * 2013-Feb-12
 * Bobi Pu, bobi.pu@usc.edu
 * This code is for Evernote's code challenge, #1
 * */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.InstantiationException;


class CircularBuffer {
	
//	public static void main(String[] args) throws Exception {
//		new CircularBuffer().run();
//	}
	
	void run() throws IOException {
		try {
			String[] command= reader.readLine().split(" ");
			buffer = new String[Integer.parseInt(command[0])];
			
			while (true) {
				command = reader.readLine().split(" ");
				if (command[0].equals("A") && command.length == 2) {
					append(command);
				} else if(command[0].equals("R") && command.length == 2) {
					remove(command);
				} else if(command[0].equals("L") && command.length == 1) {
					list(command);
				} else if(command[0].equals("Q") && command.length == 1) {
					break;
				}
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
	int front, end;
	String[] buffer;
	BufferedReader reader;
	BufferedWriter writer;
	
	private CircularBuffer() throws InstantiationException{		
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new BufferedWriter(new OutputStreamWriter(System.out));
		front = 0;
		end = 0;
	}
	
	private void append(String[] command) throws Exception{
		int numberOfLines = Integer.parseInt(command[1]);
		for (int i = 0; i < numberOfLines; i++) {
			if (front == end && buffer[front] == null) {
				buffer[end] = reader.readLine();
				continue;
			}
			end = (end + 1) % buffer.length;
			buffer[end] = reader.readLine();
			if (end == front) {
				front = (end + 1) % buffer.length;
			}
		}
	}

	private void remove(String[] command) throws Exception{
		int numberOfLines = Integer.parseInt(command[1]);
		for (int i = 0; i < numberOfLines; i++) {
			if (front == end && buffer[front] == null) {
				//empty
				return;
			} else if (front == end && buffer[front] != null) {
				//only one left
				buffer[front] = null;
			} else {
				buffer[front] = null;
				front = (front + 1) % buffer.length;
			}
		}
	}
	
	private void list(String[] command) throws IOException {
		if (front == end && buffer[front] == null) {
			//empty
		} else if (front == end && buffer[front] != null) {
			//only one left
			writer.write(buffer[front] + "\n");
			writer.flush();
		} else if (end > front) {
			for (int i = front; i <= end; i++) {
				writer.write(buffer[i] + "\n");
				writer.flush();
			}
		} else if(front > end) {
			for (int i = front; i < buffer.length; i++) {
				writer.write(buffer[i] + "\n");
				writer.flush();
			}
			for (int i = 0; i <= end; i++) {
				writer.write(buffer[i] + "\n");
				writer.flush();
			}
		}
	}
}




