import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DeltaGenerator {
	
	public static void main(String args[]) {
		byte[] a1 = {'a','b','c', 'd', 'e', 'd', 'a', 'a', 'd', 'a', 'a'};
		byte[] a2 = {'a','d','b','c', 'd', 'a', 'a', 'd', 'a', 'a'};
		ArrayList<Integer> commonStringLengths = new ArrayList<Integer>();
		ArrayList<Integer> commonStringIndexesOriginal = new ArrayList<Integer>();
		ArrayList<Integer> commonStringIndexesEdited = new ArrayList<Integer>();
		findCommonSubstrings(a1,a2, commonStringLengths, commonStringIndexesOriginal, commonStringIndexesEdited, 0, a1.length, 0, a2.length, 1);
		
		System.out.println("Common substrings are at the following indexes in the original sequence:");
		for (Integer i : commonStringIndexesOriginal) {
			System.out.println(i);
		}
		
		System.out.println("Common substrings are at the following indexes in the edited sequence:");
		for (Integer i : commonStringIndexesEdited) {
			System.out.println(i);
		}
		
		System.out.println("The common substrings are of these lengths");
		for (Integer i : commonStringLengths) {
			System.out.println(i);
		}
	}

	// Find the longest common substring between two byte arrays
	private static int[] longestCommonSubstring(byte[] s1, byte[] s2, int s1Index1, int s1Index2, int s2Index1, int s2Index2, int minSubstringSize) {
		int length = 0;
		int index1 = 0;
		int index2 = 0;
		
		for (int i = s1Index1; i < s1Index2; i++) {
			for (int j = s2Index1; j < s2Index2; j++) {
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
						index1 = i;
						index2 = j;
					}
				}
			}
		}
		
		if (length >= minSubstringSize) {
			System.out.println("found LCS at " + index1 + " with length " + length);
			return new int[] {length, index1, index2};
		} else {
			return null;
		}
	}
	
	// Finds all common substrings in two byte arrays larger than a certain size
	// Places matches in the commonStringLengths and commonStringIndexes arraylists
	// Works recursively
	public static void findCommonSubstrings(byte[] original, byte[] edited, ArrayList<Integer> stringLengths, ArrayList<Integer> stringIndexesOriginal, ArrayList<Integer> stringIndexesEdited, int startOriginal, int endOriginal, int startEdited, int endEdited, int minSubstringSize) {
		int[] LCS = longestCommonSubstring(original, edited, startOriginal, endOriginal, startEdited, endEdited, minSubstringSize);
		
		// stop searching if no long common substrings are found
		if (LCS == null) return;
		
		// add LCS is found
		stringLengths.add(LCS[0]);
		stringIndexesOriginal.add(LCS[1]);
		stringIndexesEdited.add(LCS[2]);
		
		// search regions before and after
		findCommonSubstrings(original, edited, stringLengths, stringIndexesOriginal, stringIndexesEdited, startOriginal, LCS[1], startEdited, LCS[2], minSubstringSize);
		findCommonSubstrings(original, edited, stringLengths, stringIndexesOriginal, stringIndexesEdited, LCS[0]+LCS[1], endOriginal, LCS[0]+LCS[2], endEdited, minSubstringSize);
	}
	
	// Given a file and an edit of it, create a file that can be used in conjunction with the original to recreate the edit
	public static void generateDelta() {
		
	}
}
