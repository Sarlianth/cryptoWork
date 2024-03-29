package ie.gmit.sw.ai;

/*
 * Adrian Sypos - G00309646
 * CipherBreaker - Class containing the main method, printing menu and allowing user to interact through command line UI
 */

public class CipherBreaker {

	public static void main(String[] args) throws Exception, Throwable {
		String cipherText = "HFZQLYVEDWNITIQPQDUVHYLGXZHFNYBKPACAZQHFVQIQCUUVYCBXABQZQZURHQDZHBKDMVZQHXRGURLQHTXZQVDFYXZHRGGWHBYEGXNYYEGKYVHFLQDBWDVQIZEAUCAHHPQIBRRVBREZNYYQAHPUQDUVHYZXGNRDEOZWQFKCLZZHXVRDEOFEINQZZKZPKDYDCAMEEQUDBCLDBKPAEDUVYCHFZQQEUMSVPBUMURLQHTXZXZCUHTVTPHMDLDRGMDLDVBHCMGUVYCQVPVDMSZXQCPDIQZLQKDUBEMTCYDDBCQGDFEUKQZVPCYUHKDIABDFVFEETGKIDOZEFURLQUVYCKDPTACYQUCFUPVVBBREZZXDTZPWCMEDILYTHZHADMUDBGQHBKIFEMDEWIZRGVQHTKCNWIEGNHCPLLUDPCOFTQGDPNWBYHCHFQZITQVGKUVYCHFBDQVHVHCHFDIYXHFBRUMLZKDZDFQFHNYLGSAPLQCCAZQHCPCBODITCVBMUHFDIYXHFBRUMLZKDLULIDLIDDLQRKWZQACYQUZBHZBDUBHQZUKUZEDGWTVBXABQZQZBUFEUFFTQVEKZQINAHMEPTDFNYFBIZEXBRRVBREZTCILEVFBEDHUBRWDLYTHFHIZNYCPOVBDLIZQHFQPQDUVHYLGCUNYOKDMPCHTXZPCGCHFDYLQDBLTHPQEKCGKTIQIBRVQHBQNDBRXBZEFRFVUEDQYNYMZCPBDHYLKCUXF";
		
		int OPTIMAL_TEMP = (int)((10 + 0.087 * (cipherText.length() - 84)));
		int BEST_TEMP = OPTIMAL_TEMP / 3;
		
		Playfair playfair = new Playfair();
		playfair.setCipherText(cipherText);
		
		long startTime = System.currentTimeMillis();

		SimulatedAnnealing sa = new SimulatedAnnealing(BEST_TEMP, 50000, cipherText);
		sa.annealing();
		
		System.out.println("Execution time: " + ((System.currentTimeMillis() - startTime)/1000) + "s");	
	}
}
