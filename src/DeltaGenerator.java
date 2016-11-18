import java.io.File;

public class DeltaGenerator {

	// Finds all the common substrings in the two files over a specific character length
	private int[] findCommonSubstrings(File f1, File f2, int length) {
		return null;
	}
	
	// Chooses a subset of the substrings
	// Maximizes the number of bytes contained within the substrings
	// Can improve by choosing substrings based on minimization of delta generation in algorithm in generateDelta method
	private int[] chooseSubStrings(int[] substrings, File edited) {
		return substrings;
		
	}
	
	// Given a file and an edit of it, create a file that can be used in conjunction with the original to recreate the edit
	public static void generateDelta(File original, File edited) {
		
	}
}
