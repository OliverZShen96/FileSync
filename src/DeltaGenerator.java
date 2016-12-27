import java.util.Arrays;
import java.util.TreeMap;

public class DeltaGenerator {
	private static int count = 0;
	
	public static void main(String args[]) {
		String first = "abababab";
		String second = "babababa";
		byte[] a1 = first.getBytes();
		byte[] a2 = second.getBytes();
		generateDelta(a1,a2);
		
	}

	// Find the longest common substring between two byte arrays
	private static int[] longestCommonSubstring(byte[] s1, byte[] s2, int s1Start, int s1End, int s2Start, int s2End, int minSubstringSize) {
		int length = 0;
		int index1 = 0;
		int index2 = 0;
		
		// iterate through the two byte arrays
		for (int i = s1Start; i <= s1End; i++) {
			for (int j = s2Start; j <= s2End; j++) {
				
				// comparing bytes one by one
				if (s1[i] != s2[j]) continue;
				
				// found matching bytes
				// count the length of the common string of bytes
				int commonLength = 0;
				int a = i;
				int b = j;
				while (s1[a] == s2[b]) {
					commonLength++;
					a++;
					b++;
					if (a >= s1.length || b >= s2.length) break;
					if (a > s1End || b > s2End) break;
				}
				
				// compare with LCS
				if (commonLength > length) {
					length = commonLength;
					index1 = i;
					index2 = j;
				}
			}
		}
		
		if (length >= minSubstringSize) {
			return new int[] {length, index1, index2};
		} else {
			return null;
		}
	}
	
	// Finds all common substrings in two byte arrays larger than a certain size
	// Places matches in stringData hash
	// Works recursively
	public static void findCommonSubstrings(byte[] original, byte[] edited, TreeMap<Integer, Integer> stringLengths, TreeMap<Integer, Integer> editIndexes, int startOriginal, int endOriginal, int startEdited, int endEdited, int minSubstringSize) {
		int[] LCS = longestCommonSubstring(original, edited, startOriginal, endOriginal, startEdited, endEdited, minSubstringSize);
		
		// stop searching if no long common substrings are found
		if (LCS == null) return;
		
		// add LCS is found, add data to the maps
		// the keys are indexes of strings in the original array
		// the values are the length of the strings, and the indexes of the strings in the edited array
		stringLengths.put(LCS[1], LCS[0]);
		editIndexes.put(LCS[1], LCS[2]);
		count++;
		
		// search regions before and after
		findCommonSubstrings(original, edited, stringLengths, editIndexes, startOriginal, LCS[1]-1, startEdited, LCS[2]-1, minSubstringSize);
		findCommonSubstrings(original, edited, stringLengths, editIndexes, LCS[0]+LCS[1], endOriginal, LCS[0]+LCS[2], endEdited, minSubstringSize);
	}
	
	// Given a file and an edit of it, create a file that can be used in conjunction with the original to recreate the edit
	public static void generateDelta(byte[] a1, byte[] a2) {
		TreeMap<Integer, Integer> stringLengths = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Integer> editIndexes = new TreeMap<Integer, Integer>();
		
		findCommonSubstrings(a1,a2, stringLengths, editIndexes, 0, a1.length-1, 0, a2.length-1, 1);
		
		// iterate through indexes of common strings
		
		// key i is the index in the original data
		// value from stringLengths is length of common string
		// value from editIndexes is the index in the edited data
		
		int counter = 0;
		int prevI = 0;
		int prevJ = 0;
		int prevLength = 0;
		for (int i : stringLengths.keySet()) {
			int j = editIndexes.get(i);
			
			if (i > j) System.out.println("Delete " + (i - prevI - prevLength));
			if (i < j) System.out.println("Write " + (j - prevJ - prevLength));
			System.out.println("Copy " + stringLengths.get(i));
			
			prevI = i;
			prevJ = j;
			prevLength = stringLengths.get(i);
		}
		
		
	}
}
