package templates;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import templates.files.pdf.ResumeExportService;

import com.lowagie.text.DocumentException;

public class Application {

	private static Logger logger = Logger
			.getLogger(Application.class.getName());

	public static void main(String... args) throws InterruptedException,
			DocumentException, IOException {

		Application app = new Application();

		ResumeExportService exportService = new ResumeExportService();

		// Iterate through each templates/*.html file on the classpath.
		for (File file : app.getTemplates()) {

			// pass that in as the template to template service.
			ByteArrayOutputStream pdfDocument = exportService
					.generatePDFDocument(file);

			OutputStream outputStream = new FileOutputStream(file.getName()
					.replace("html", "pdf"));

			// write resulting bytes to file in build/templates
			pdfDocument.writeTo(outputStream);
		}

	}

	private List<File> getTemplates() {
		List<File> templates = new ArrayList<>();
		File templateDirectory = this.getTemplateDirectory();

		for (File file : templateDirectory.listFiles()) {
			if (file.isFile() && file.getName().contains("html")) {
				logger.info("Preparing to print: " + file.getAbsolutePath());
				templates.add(new File(file.getAbsolutePath()));
			}
		}
		return templates;
	}

	private File getTemplateDirectory() {
		ClassLoader classLoader = getClass().getClassLoader();
		return new File(classLoader.getResource("templates/").getFile());
	}

}
