import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;

public class DeltaGenerator {
	
	public static void main(String args[]) {
		byte[] a1 = {'a','b','c', 'd', 'e', 'd', 'a', 'a', 'd', 'a', 'a'};
		byte[] a2 = {'a','d','b','c', 'd', 'a', 'a', 'd', 'a', 'a'};
		System.out.println(Arrays.toString(longestCommonSubstring(a1, a2)));
	}

	// Find the longest common substring between two byte arrays
	private static char[] longestCommonSubstring(byte[] s1, byte[] s2) {
		int length = 0;
		int index = 0;
		
		for (int i = 0; i < s1.length; i++) {
			for (int j = 0; j < s2.length; j++) {
				if (s1[i] == s2[j]) {
					int commonLength = 0;
					int a = i;
					int b = j;
					while (s1[a] == s2[b]) {
						commonLength++;
						a++;
						b++;
						if (a >= s1.length || b >= s2.length) break;
					}
					if (commonLength > length) {
						length = commonLength;
						index = i;
					}
				}
			}
		}
		
		char[] LCS = new char[length];
		for (int i = index, j = 0; i < index + length; i++, j++) {
			LCS[j] = (char) s1[i];
		}
		return LCS;
		
	}
	
	// Given a file and an edit of it, create a file that can be used in conjunction with the original to recreate the edit
	public static void generateDelta(String original, String edited) {
		
	}
}
