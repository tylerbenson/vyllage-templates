package templates;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import templates.files.pdf.ResumeExportService;

import com.lowagie.text.DocumentException;

public class Application {

	public static void main(String... args) throws InterruptedException,
			DocumentException, IOException {

		Application app = new Application();

		ResumeExportService exportService = new ResumeExportService();

		ByteArrayOutputStream pdfDocument = exportService
				.generatePDFDocument(app.getFile("templates/default.html"));

		OutputStream outputStream = new FileOutputStream("hello.pdf");
		pdfDocument.writeTo(outputStream);

		// Iterate through each templates/*.html file on the classpath.

		// pass that in as the template to template service.

		// write resulting bytes to file in build/templates
	}

	private String getFile(String fileName) {

		StringBuilder result = new StringBuilder("");

		// Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result.toString();

	}
}
