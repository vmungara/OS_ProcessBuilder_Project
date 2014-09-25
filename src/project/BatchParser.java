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

import project.command.Command;
import project.command.FileCommand;
import project.command.WdCommand;

/**
 * @author
 *
 */
public class BatchParser {

	static Batch buildBatch(File batchFile) throws ProcessException {

		if (batchFile == null || !batchFile.exists()) {
			throw new ProcessException("cannot find batch file");
		}

		Batch batchObj = new Batch();
		Command cmd = null;
		
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
					cmd = buildCommand(elem);
					batchObj.addCommand(cmd);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		// TODO
		return batchObj;
	}

	static Command buildCommand(Element elem) throws DOMException,
			ProcessException {
		Command cmd = null;
		String cmdName = elem.getNodeName();

		if (cmdName == null) {
			throw new ProcessException("unable to parse command from "
					+ elem.getTextContent());
		
		} else if ("wd".equalsIgnoreCase(cmdName)) {
			System.out.println("Parsing wd");
			cmd = new WdCommand();
			cmd.parse(elem);
		
		} else if ("file".equalsIgnoreCase(cmdName)) {
			System.out.println("Parsing file");
			cmd = new FileCommand();
			cmd.parse(elem);

		
		} else if ("cmd".equalsIgnoreCase(cmdName)) {
			System.out.println("Parsing cmd");
			
		
		} else if ("pipe".equalsIgnoreCase(cmdName)) {
			System.out.println("Parsing pipe");
			// Command cmd = PipeCommand.parse(elem);
		
		} else {
			throw new ProcessException("Unknown command " + cmdName + " from: "
					+ elem.getBaseURI());
		}
		return cmd;
	}
}
