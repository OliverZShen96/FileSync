import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class DeltaInterpreter {
	
	public static FileWriter w;
	public static String deltaPath;
	public static String filePath;
	
	public DeltaInterpreter(String deltaPath, String filePath) throws IOException {
		w = new FileWriter("newFile.txt");
		this.deltaPath = deltaPath;
		this.filePath = filePath;
	}
	
	public static void interpretDelta() throws IOException {
		byte[] a1 = Files.readAllBytes(new File(filePath).toPath());
		BufferedReader br = new BufferedReader(new FileReader(deltaPath));
		int curr;
		int i = 0;
		while ((curr = br.read()) != -1) {
			char c = (char) curr;
			
			// Delete
			if (c == 'D' || c == 'W' || c == 'C') {
				char type = c;
				String num = "";
				while (true) {
					c = (char)br.read();
					if (c == type) break;
					num = num + c;
				}
				System.out.println("["+num+"]");
				
				if (type == 'W') {
					for (int j = Integer.parseInt(num); j > 0; j--) {
						c = (char) br.read();
						w.write(c);
					}
				}
			} else {
				System.out.println("SOMETHING BAD HAPPENED");
			}
		}
		w.close();
	}
}
