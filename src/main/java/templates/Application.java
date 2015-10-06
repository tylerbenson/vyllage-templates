package templates;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import templates.files.pdf.ResumeExportService;

import com.lowagie.text.DocumentException;

public class Application {

	// private static Logger logger = Logger
	// .getLogger(Application.class.getName());

	public static void main(String... args) throws InterruptedException,
			DocumentException, IOException {
		new File("build/documents").mkdirs();

		Application app = new Application();

		ResumeExportService exportService = new ResumeExportService();

		List<File> templates = app.getTemplates();
		System.out.println("Generating " + templates.size() + " PDFs");

		// Iterate through each documents/*.html file on the classpath.
		for (File file : templates) {

			// pass that in as the template to template service.
			ByteArrayOutputStream pdfDocument = exportService
					.generatePDFDocument(file);

			OutputStream outputStream = new FileOutputStream("build/documents/"
					+ file.getName().replace("html", "pdf"));

			// write resulting bytes to file in build/documents
			pdfDocument.writeTo(outputStream);
		}

	}

	private List<File> getTemplates() {
		List<File> templates = new ArrayList<>();
		File templateDirectory = this.getTemplateDirectory();

		for (File file : templateDirectory.listFiles()) {
			System.out.println("Analyzing: " + file.getAbsolutePath());
			if (file.isFile() && file.getName().contains("html")) {
				System.out.println("Preparing to print: "
						+ file.getAbsolutePath());
				templates.add(new File(file.getAbsolutePath()));
			}
		}
		return templates;
	}

	private File getTemplateDirectory() {
		ClassLoader classLoader = getClass().getClassLoader();
		File templateDir = new File("build/resources/main/documents");
		if (!templateDir.exists())
			throw new RuntimeException("template dir not found");
		if (templateDir.listFiles().length == 0)
			throw new RuntimeException("template dir is empty");
		return templateDir;
	}

}