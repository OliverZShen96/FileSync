import java.io.FileWriter;
import java.io.IOException;

public class Test {
	public static void main(String args[]) throws IOException {
		DeltaGenerator generator = new DeltaGenerator(args[0], args[1]);
		generator.generateDelta();
		
		DeltaInterpreter interpreter = new DeltaInterpreter("fileDelta.txt", "input1.txt");
		interpreter.interpretDelta();
	}
}
