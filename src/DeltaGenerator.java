import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class DeltaGenerator {
	private static int count = 0;
	
	public static void main(String args[]) {
		byte[] a1 = {'a','b','c', 'd', 'e', 'd', 'a', 'a', 'd', 'a', 'a'};
		byte[] a2 = {'a','d','b','c', 'd', 'a', 'a', 'd', 'a', 'a'};
		generateDelta(a1,a2);
		
	}

	// Find the longest common substring between two byte arrays
	private static int[] longestCommonSubstring(byte[] s1, byte[] s2, int s1Index1, int s1Index2, int s2Index1, int s2Index2, int minSubstringSize) {
		int length = 0;
		int index1 = 0;
		int index2 = 0;
		
		// iterate through the two byte arrays
		for (int i = s1Index1; i < s1Index2; i++) {
			for (int j = s2Index1; j < s2Index2; j++) {
				
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
			System.out.println("found LCS at " + index1 + " with length " + length);
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
		findCommonSubstrings(original, edited, stringLengths, editIndexes, startOriginal, LCS[1], startEdited, LCS[2], minSubstringSize);
		findCommonSubstrings(original, edited, stringLengths, editIndexes, LCS[0]+LCS[1], endOriginal, LCS[0]+LCS[2], endEdited, minSubstringSize);
	}
	
	// Given a file and an edit of it, create a file that can be used in conjunction with the original to recreate the edit
	public static void generateDelta(byte[] a1, byte[] a2) {
		TreeMap<Integer, Integer> stringLengths = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Integer> editIndexes = new TreeMap<Integer, Integer>();
		
		findCommonSubstrings(a1,a2, stringLengths, editIndexes, 0, a1.length, 0, a2.length, 1);
		
		// iterate through indexes of common strings
		for (int i : stringLengths.keySet()) {
			System.out.print("index a " + i + "|");
			System.out.print("length " + stringLengths.get(i) + "|");
			System.out.println("index b " + editIndexes.get(i));
			
			
			/*
			 *  TODO:
			 *  
			 *  - Iterating through the common strings, compare the two pieces of data with one another
			 *  - Need to handle copy/remove/insert operations based on common string locations
			 *  
			 */
		}
	}
}
