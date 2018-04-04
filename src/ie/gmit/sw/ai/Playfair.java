package ie.gmit.sw.ai;

import java.util.LinkedList;
import java.util.List;

//Adrian Sypos

public class Playfair {

	private List<Position> positions;
	private StringBuilder plainText;
	private char[][] cipherTable;
	private String cipherText;

	public Playfair() {
		super();
		this.positions = new LinkedList<Position>();
		this.plainText = new StringBuilder();
		this.cipherTable = new char[5][5];
		this.cipherText = "";
	}
	
	/**
	 * The decrypt method will take already encypted text (cipherText) and decypt it
	 * out to plainText using the decryption key
	 * 
	 * @param key
	 * @param cipherText
	 * @return plainText
	 * @throws Exception 
	 */
	public String decrypt(String key) throws Exception {
		
		String decryptionKey = key;
		char[][] cipherTable = new char[5][5];

		int index = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				cipherTable[i][j] = decryptionKey.charAt(index);
//				System.out.print(cipherTable[i][j] + " ");
				index++;
			}
		}
		//index = 0;
		this.cipherTable = cipherTable;
		
		StringBuilder sb = new StringBuilder();
		
		for(index = 0; index < this.cipherText.length() / 2; index ++) {
			char a = this.cipherText.charAt(2 * index);
			char b = this.cipherText.charAt(2 * index + 1);
			int r1 = (int) Position.getPos(a, cipherTable).getX();
			int c1 = (int) Position.getPos(a, cipherTable).getY();
			int r2 = (int) Position.getPos(b, cipherTable).getX();
			int c2 = (int) Position.getPos(b, cipherTable).getY();

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
			sb.append(cipherTable[r1][c1] +""+ cipherTable[r2][c2]);
		}
		
		return sb.toString();

		//return cipherCrack(cipherTable, 0, sb);
	}// decrypt

	/**
	 * The cipher method will recursively scan through each letter getting its
	 * position in the 2d array and use this to crack the cipher text
	 * 	 *
	 * @param table
	 * @param cipherText
	 * @param index
	 * @return this
	 */
//	private String cipherCrack(char[][] table, int index, StringBuilder sb) {
//		//StringBuilder sb = new StringBuilder();
//		
//		if(index < this.cipherText.length() / 2) {
//			char a = this.cipherText.charAt(2 * index);
//			char b = this.cipherText.charAt(2 * index + 1);
//			int r1 = (int) Position.getPosition(a, table).getPosX();
//			int c1 = (int) Position.getPosition(a, table).getPosY();
//			int r2 = (int) Position.getPosition(b, table).getPosX();
//			int c2 = (int) Position.getPosition(b, table).getPosY();
//
//			if (r1 == r2) {
//				c1 = (c1 + 4) % 5; 
//				c2 = (c2 + 4) % 5;
//			} else if (c1 == c2) {
//				r1 = (r1 + 4) % 5;
//				r2 = (r2 + 4) % 5;
//			} else {
//		        int temp = c1;
//		        c1 = c2;
//		        c2 = temp;
//		    }
//			sb.append(table[r1][c1] +""+ table[r2][c2]);
//			
//			return cipherCrack(table, 1 + index, sb);
//		}else return sb.toString();
//	}// cipherCrack

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public String getPlainText() {
		return plainText.toString();
	}

	public void setPlainText(String plainText) {
		this.plainText.append(plainText);
	}

	public char[][] getCipherTable() {
		return cipherTable;
	}

	public void setCipherTable(char[][] cipherTable) {
		this.cipherTable = cipherTable;
	}

	public String getCipherText() {
		return cipherText;
	}

	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}
}
