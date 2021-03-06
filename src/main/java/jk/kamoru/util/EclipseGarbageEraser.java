package jk.kamoru.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EclipseGarbageEraser {

	protected final Log logger = LogFactory.getLog(getClass());
	
	List<String> garbageNames = Arrays.asList(".project", ".classpath", ".settings");
	String[] folders;

	public EclipseGarbageEraser(String[] folders) {
		this.folders = folders;
		logger.info("Eclipse Garbage Eraser " + garbageNames);
	}

	public void start() {
		for (String folder : folders) {
			logger.info("Scanning... " + folder);
			File baseDir = new File(folder);
			if (!baseDir.isDirectory()) {
				logger.warn("  It is not a directory! " + baseDir.getAbsolutePath());
				return;
			}
			process(baseDir);
		}
	}

	private void process(File baseDir) {
		for (File file : FileUtils.listFiles(baseDir, null, true)) {
			if (garbageNames.contains(file.getName())) {
				try {
					FileUtils.forceDelete(file);
					logger.info(String.format("  deleted %s - %s", file.isFile() ? "file" : "folder", file));
				} catch (IOException e) {
					logger.error("  delete error ", e);
				}
			}
		}
	}

}
