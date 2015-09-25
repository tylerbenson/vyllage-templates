package templates.files.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

// @Service
public class ResumeExportService {

	private final Logger logger = Logger.getLogger(ResumeExportService.class
			.getName());

	public ByteArrayOutputStream generatePDFDocument(File file)
			throws DocumentException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			ITextRenderer renderer = preparePDF(file);
			renderer.createPDF(out);
			renderer.finishPDF();

			out.close();
		} catch (IOException e) {
			logger.severe(ExceptionUtils.getStackTrace(e));
		}

		return out;

	}

	protected ITextRenderer preparePDF(File file) throws IOException {
		ITextRenderer renderer = new ITextRenderer();
		// renderer.setDocumentFromString(file);
		renderer.setDocument(file);

		PackageUserAgentCallback callback = new PackageUserAgentCallback(
				renderer.getOutputDevice(), Resource.class);
		callback.setSharedContext(renderer.getSharedContext());
		renderer.getSharedContext().setUserAgentCallback(callback);
		// renderer.setDocumentFromString(htmlContent);

		renderer.layout();
		return renderer;
	}

	/**
	 * Generates a thumbnail of the resume in PNG using the selected style. <br>
	 * http://stackoverflow.com/questions/4929813/convert-pdf-to-thumbnail-image
	 * -in-java?lq=1
	 * 
	 * @param resumeHeader
	 * @param sections
	 * @param styleName
	 * @param width
	 * @param height
	 * @return
	 * @throws DocumentException
	 */
	// public ByteArrayOutputStream generatePNGDocument(
	// final DocumentHeader resumeHeader,
	// final List<DocumentSection> sections, final String styleName,
	// int width, int height) throws DocumentException {
	//
	// if (width == 0 || height == 0) {
	// width = 64 * 2;
	// height = 98 * 2;
	// }
	//
	// ByteArrayOutputStream out = new ByteArrayOutputStream();
	//
	// ITextRenderer renderer = preparePDF(resumeHeader, sections, styleName);
	//
	// File tempFile = null;
	//
	// try {
	// tempFile = File.createTempFile("document", ".pdf");
	// } catch (IOException e) {
	// logger.severe(ExceptionUtils.getStackTrace(e));
	// }
	//
	// // saving the pdf
	// try (FileOutputStream fop = new FileOutputStream(tempFile)) {
	// renderer.createPDF(fop);
	// renderer.finishPDF();
	// // if file doesn't exists, then create it
	// if (!tempFile.exists()) {
	// tempFile.createNewFile();
	// }
	//
	// fop.flush();
	//
	// } catch (IOException e) {
	// logger.severe(ExceptionUtils.getStackTrace(e));
	// }
	//
	// // loading the pdf and rendering an image of the first page
	// try (FileInputStream fis = new FileInputStream(tempFile)) {
	// FileChannel channel = fis.getChannel();
	// MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY,
	// 0, channel.size());
	// PDFFile pdffile = new PDFFile(buf);
	//
	// // draw the first page to an image
	// PDFPage page = pdffile.getPage(0);
	//
	// // get the width and height for the doc at the default zoom
	// Rectangle rect = new Rectangle(0, 0, (int) page.getBBox()
	// .getWidth(), (int) page.getBBox().getHeight());
	//
	// BufferedImage bufferedImage = new BufferedImage(width, height,
	// BufferedImage.TYPE_INT_RGB);
	//
	// Image image = page.getImage(rect.width, rect.height, // width &
	// // height
	// rect, // clip rect
	// null, // null for the ImageObserver
	// true, // fill background with white
	// true // block until drawing is done
	// );
	// FSImageWriter w = new FSImageWriter("png");
	//
	// Image scaledInstance = image.getScaledInstance(width, height,
	// Image.SCALE_SMOOTH);
	//
	// Graphics2D bufImageGraphics = bufferedImage.createGraphics();
	// bufImageGraphics.drawImage(scaledInstance, 0, 0, null);
	//
	// ImageIO.write(bufferedImage, "png", out);
	// w.write(bufferedImage, out);
	// tempFile.delete();
	//
	// out.close();
	//
	// } catch (IOException e) {
	// logger.severe(ExceptionUtils.getStackTrace(e));
	// }
	//
	// return out;
	//
	// }

}
