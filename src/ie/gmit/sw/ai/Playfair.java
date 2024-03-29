package ie.gmit.sw.ai;

import java.util.LinkedList;
import java.util.List;

/*
 * Adrian Sypos - G00309646
 * Playfair - Class that will be responsible for the playfair cipher decryption
 * Some of the code in this class has been adapted from https://www.sanfoundry.com/java-program-decode-message-encoded-using-playfair-cipher/
 */

public class Playfair {

	private List<Position> pos;
	private StringBuilder normalText;
	private char[][] cipher;
	private String cipherText;

	//Constructor
	public Playfair() {
		super();
		this.pos = new LinkedList<Position>();
		this.normalText = new StringBuilder();
		this.cipher = new char[5][5];
		this.cipherText = "";
	}
	
	//Decrypt encrypted text using the key
	public String decrypt(String key) throws Exception {
		
		String decryptKey = key;
		char[][] cipher = new char[5][5];

		int index = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				cipher[i][j] = decryptKey.charAt(index);
//				System.out.print(cipherTable[i][j] + " ");
				index++;
			}
		}
		this.cipher = cipher;
		
		StringBuilder sb = new StringBuilder();
		
		for(index = 0; index < this.cipherText.length() / 2; index ++) {
			char a = this.cipherText.charAt(2 * index);
			char b = this.cipherText.charAt(2 * index + 1);
			int r1 = (int) Position.getPos(a, cipher).getX();
			int c1 = (int) Position.getPos(a, cipher).getY();
			int r2 = (int) Position.getPos(b, cipher).getX();
			int c2 = (int) Position.getPos(b, cipher).getY();

			if (r1 == r2) {
				c1 = (c1 + 4) % 5; 
				c2 = (c2 + 4) % 5;
			} else if (c1 == c2) {
				r1 = (r1 + 4) % 5;
				r2 = (r2 + 4) % 5;
			} else {
		        int temp = c1;
		        c1 = c2;
		        c2 = temp;
		    }
			sb.append(cipher[r1][c1] +""+ cipher[r2][c2]);
		}
		
		return sb.toString();
	}

	public List<Position> getPositions() {
		return pos;
	}

	public void setPositions(List<Position> positions) {
		this.pos = positions;
	}

	public String getPlainText() {
		return normalText.toString();
	}

	public void setPlainText(String plainText) {
		this.normalText.append(plainText);
	}

	public char[][] getCipherTable() {
		return cipher;
	}

	public void setCipherTable(char[][] cipherTable) {
		this.cipher = cipherTable;
	}

	public String getCipherText() {
		return cipherText;
	}

	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}
}
