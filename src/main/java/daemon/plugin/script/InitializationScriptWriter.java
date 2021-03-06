package daemon.plugin.script;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class InitializationScriptWriter {
	private final InitializationScript initializationScript;

	public InitializationScriptWriter(InitializationScript initializationScript) {
		this.initializationScript = initializationScript;
	}

	public void writeToExecutableFile(File outputDirectory) throws IOException {
		File outputFile = new File(outputDirectory,
		        initializationScript.getFileName());
		FileUtils.writeStringToFile(outputFile,
		        initializationScript.getContentAsString());
		outputFile.setExecutable(true);
		outputFile.setReadable(true);
	}
}
