/**
 * 
 */
package project;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author
 *
 */
public class BatchParser {

	static Batch buildBatch(File batchFile) {

		if (batchFile == null || !batchFile.exists()) {

		}

		try {

			FileInputStream fis = new FileInputStream(batchFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fis);

			Element pnode = doc.getDocumentElement();
			NodeList nodes = pnode.getChildNodes();
			for (int idx = 0; idx < nodes.getLength(); idx++) {
				Node node = nodes.item(idx);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;
					buildCommand(elem);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		// TODO
		return null;
	}

	static Command buildCommand(Element elem) throws DOMException,
			ProcessException {

		String cmdName = elem.getNodeName();

		if (cmdName == null) {
			throw new ProcessException("unable to parse command from "
					+ elem.getTextContent());
		} else if ("wd".equalsIgnoreCase(cmdName)) {
			System.out.println("Parsing wd");
			// Command cmd = WDCommand.parse(elem);
		} else if ("file".equalsIgnoreCase(cmdName)) {
			System.out.println("Parsing file");
			// Command cmd = FileCommand.parse(elem);
		} else if ("cmd".equalsIgnoreCase(cmdName)) {
			System.out.println("Parsing cmd");
			// Command cmd = CmdCommand.parse(elem);
			// parseCmd(elem); // Example of parsing a cmd element
		} else if ("pipe".equalsIgnoreCase(cmdName)) {
			System.out.println("Parsing pipe");
			// Command cmd = PipeCommand.parse(elem);
		} else {
			throw new ProcessException("Unknown command " + cmdName + " from: "
					+ elem.getBaseURI());
		}
		return null;
	}
}
