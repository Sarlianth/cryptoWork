package ie.gmit.sw.ai;


public class CipherBreaker {

	public static void main(String[] args) throws Exception, Throwable {
		//Grams n = new Grams("4grams.txt");
		
		String cipherText = "HFZQLYVEDWNITIQPQDUVHYLGXZHFNYBKPACAZQHFVQIQCUUVYCBXABQZQZURHQDZHBKDMVZQHXRGURLQHTXZQVDFYXZHRGGWHBYEGXNYYEGKYVHFLQDBWDVQIZEAUCAHHPQIBRRVBREZNYYQAHPUQDUVHYZXGNRDEOZWQFKCLZZHXVRDEOFEINQZZKZPKDYDCAMEEQUDBCLDBKPAEDUVYCHFZQQEUMSVPBUMURLQHTXZXZCUHTVTPHMDLDRGMDLDVBHCMGUVYCQVPVDMSZXQCPDIQZLQKDUBEMTCYDDBCQGDFEUKQZVPCYUHKDIABDFVFEETGKIDOZEFURLQUVYCKDPTACYQUCFUPVVBBREZZXDTZPWCMEDILYTHZHADMUDBGQHBKIFEMDEWIZRGVQHTKCNWIEGNHCPLLUDPCOFTQGDPNWBYHCHFQZITQVGKUVYCHFBDQVHVHCHFDIYXHFBRUMLZKDZDFQFHNYLGSAPLQCCAZQHCPCBODITCVBMUHFDIYXHFBRUMLZKDLULIDLIDDLQRKWZQACYQUZBHZBDUBHQZUKUZEDGWTVBXABQZQZBUFEUFFTQVEKZQINAHMEPTDFNYFBIZEXBRRVBREZTCILEVFBEDHUBRWDLYTHFHIZNYCPOVBDLIZQHFQPQDUVHYLGCUNYOKDMPCHTXZPCGCHFDYLQDBLTHPQEKCGKTIQIBRVQHBQNDBRXBZEFRFVUEDQYNYMZCPBDHYLKCUXF";
		System.out.println("pre decryption: " + cipherText);
		long startTime = System.currentTimeMillis();
		Playfair pf = new Playfair();
		pf.setCipherText(cipherText);
		String text = pf.decrypt("ABCDEFGHIKLMNOPQRSTUVWXYZ");
		System.out.println(text);
		
//		System.out.println("Running in:");
//		for(int i = 2 ; i >0; i--) {
//			Thread.sleep(1000);
//			System.out.println(i);
//		}
	
		int temp = (int)((10 + 0.087 * (cipherText.length() - 84)));
		System.out.println(temp);
		SimulatedAnnealing sa = new SimulatedAnnealing(temp/3, 50000, cipherText);
	
		sa.annealing(cipherText);
		
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("Execution time: " + (estimatedTime/1000) + "s");
		
		
	}

}
