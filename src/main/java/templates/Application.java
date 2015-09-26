package templates;

import templates.files.pdf.ResumeExportService;

public class Application {

	public static void main(String... args) throws InterruptedException {
		ResumeExportService exportService = new ResumeExportService(null);
		// Iterate through each templates/*.html file on the classpath.

		// pass that in as the template to template service.

		// write resulting bytes to file in build/templates
	}
}
