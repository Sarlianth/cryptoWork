package ie.gmit.sw.ai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
public class Grams {
	
	private String fileName;
	private Map<String, Integer> nGrams;
	private long no;
	
	public Grams(String fileName) {
		this.fileName = fileName;
		this.nGrams = new HashMap<String, Integer>();
	}// Constructor

	public Map<String, Integer> loadNGrams()  throws Exception {
		long count = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
		String line = "";
		//System.out.println("Loading n-grams...");
		while((line = br.readLine()) != null) {
			nGrams.put(line.split(" ")[0], Integer.parseInt(line.split(" ")[1]));
			count += Double.parseDouble(line.split(" ")[1]);
		}
		setNo(count);
		br.close();	
		return this.nGrams;
	}
	
	public double scoreText(String cipherText) {
		double score = 0;
		int frequency = 0;
		
		//score += Math.log10((double) ((nGrams.get(text.substring(i, i+4)) != null) ? nGrams.get(text.substring(i, i+4)) : 1))/this.count);
		for(int i=0; i< cipherText.length() - 4; i++){
			if(nGrams.get(cipherText.substring(i, i+4)) != null){
				frequency = nGrams.get(cipherText.substring(i, i+4));
			}else{
				frequency = 1;
			}
			score += Math.log10((double) frequency/this.getNo());
		}
		
		return score;
	}
	
//	private double quadGramProbability(String key) {
//		return Math.log10((double) getNGramFrequencyCount(key) / this.no);
//	}
//	
//	public double scoreText(String textString) {
//		String text = textString.replace(" ",  "");
//		
//		return IntStream.range(0, (text.length() - 4 + 1))
//				.mapToObj(i -> new String(text.toCharArray(), i, 4))
//				.mapToDouble(quadgram -> quadGramProbability(quadgram)).sum();
//	}
//	
//	public int getNGramFrequencyCount(String key) {
//		if(this.nGrams.get(key.toUpperCase()) == null) {
//			return 1;
//		}
//		return this.nGrams.get(key.toUpperCase());
//	}
	
	public void setNo(long no) {
		this.no = no;
	}
	
	public long getNo() {
		return this.no;
	}
	
}
