package jk.kamoru.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EclipseGarbageEraser8 {

	protected final Log logger = LogFactory.getLog(getClass());
	
	List<String> garbageNames = Arrays.asList(".project", ".classpath", ".settings");
	String[] folders;

	public EclipseGarbageEraser8(String[] folders) {
		this.folders = folders;
		logger.info("Eclipse Garbage Eraser " + garbageNames);
	}

	public void start() {
		Arrays.asList(folders).forEach(folder -> {
			logger.info("Scanning... " + folder);
			File baseDir = new File(folder);
			if (baseDir.isDirectory())
				process(baseDir);
			else
				logger.warn("  It is not a directory! " + baseDir.getAbsolutePath());
		});
	}

	private void process(File baseDir) {
		FileUtils.listFiles(baseDir, null, true)
			.stream()
			.filter(file -> garbageNames.contains(file.getName()))
			.sorted()
			.forEach(file -> {
				try {
					FileUtils.forceDelete(file);
					logger.info(String.format("  deleted %s - %s", file.isFile() ? "file" : "folder", file));
				} catch (IOException e) {
					logger.error("  delete error ", e);
				}
			});
	}

}
