import java.io.FileWriter;
import java.io.IOException;

public class DeltaInterpreter {
	
	public FileWriter w;
	public String deltaPath;
	public String filePath;
	public DeltaInterpreter(String deltaPath, String filePath) throws IOException {
		w = new FileWriter("newFile.txt");
		this.deltaPath = deltaPath;
		this.filePath = filePath;
	}
	
	public static void interpretDelta() {
		
	}
}
