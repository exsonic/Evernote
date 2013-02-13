/*
 * 2013-Feb-12
 * Bobi Pu, bobi.pu@usc.edu
 * This code is for Evernote's code challenge, #3
 * 
 * */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class MostFrequent {	
	public static void main(String[] args) throws Exception{
		new MostFrequent().run();
	}
	
	int[] array;
	HashMap<String, Integer> wordsTable;
	BufferedReader reader;
	BufferedWriter writer;
	
	void run() throws IOException {
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int inputSize = Integer.parseInt(reader.readLine());
			int outputSize;
			
			for (int i = 0; i < inputSize; i++) {				
				String word = reader.readLine();
				Integer count = wordsTable.get(word);
				wordsTable.put(word, (count == null) ? 1 : ++count);
			}
			outputSize = Integer.parseInt(reader.readLine());
			
			List<Map.Entry<String, Integer>> sortedList = new ArrayList<Map.Entry<String,Integer>>(wordsTable.entrySet());
			Collections.sort(sortedList, new HashTableComparator<String, Integer>());
			
			for (Map.Entry<String, Integer> outputWord : sortedList) {
				writer.write(outputWord.getKey() + "\n");
				writer.flush();
				if (outputSize-- <= 1) {
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
	
	class HashTableComparator<K extends Comparable<? super K>, V extends Comparable<? super V>> implements Comparator<Map.Entry<K, V>> {

		public int compare(Map.Entry<K, V> a, Map.Entry<K, V> b) {
			int compareResult = b.getValue().compareTo(a.getValue());
			if (compareResult != 0) {
				return compareResult;
			} else {
				return a.getKey().compareTo(b.getKey());
			}
		}

	}
	
	MostFrequent() {
		wordsTable = new HashMap<String, Integer>();
		array = null;
		reader = null;
		writer = null;
	}
}
