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
		ArrayList<Integer> commonStringIndexes = new ArrayList<Integer>();
		diff(a1,a2, commonStringLengths, commonStringIndexes, 0, a1.length, 0, a2.length, 1);
		
		for (Integer i : commonStringIndexes) {
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
	public static void diff(byte[] original, byte[] edited, ArrayList<Integer> commonStringLengths, ArrayList<Integer> commonStringIndexes, int startOriginal, int endOriginal, int startEdited, int endEdited, int minSubstringSize) {
		System.out.println("diffing");
		int[] LCS = longestCommonSubstring(original, edited, startOriginal, endOriginal, startEdited, endEdited, minSubstringSize);
		
		// stop searching if no long common substrings are found
		if (LCS == null) return;
		
		// add LCS is found
		commonStringLengths.add(LCS[0]);
		commonStringIndexes.add(LCS[1]);
		
		// search regions before and after
		diff(original, edited, commonStringLengths, commonStringIndexes, startOriginal, LCS[1], startEdited, LCS[2], minSubstringSize);
		diff(original, edited, commonStringLengths, commonStringIndexes, LCS[0]+LCS[1], endOriginal, LCS[0]+LCS[2], endEdited, minSubstringSize);
	}
	
	// Given a file and an edit of it, create a file that can be used in conjunction with the original to recreate the edit
	public static void generateDelta() {
		
	}
}
