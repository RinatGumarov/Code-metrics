package rubyMetrics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.function.Consumer;

public class CLI {

	private static String samples = Global.filesPath;
	private static String testReport = Global.filesPath + "report.csv";
	
	public void run() {

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Command (h - help): ");
			String input = scanner.nextLine();
			switch (input) {
			case "h":
				showHelp();
				break;
			case "f":
				showFiles();
				break;
			case "t":
				testFile(scanner);
				break;
			case "a":
				testAllFiles();
				break;
			case "e": // exit
				scanner.close();
				return;
			default:
				System.out.println("Unknown command");
			}
		}
	}
	
	private void testAllFiles() {
		System.out.println("Sample files:");
		fileProcessor((File f) -> { if (f.isFile() && f.getName().endsWith(".rb")) testFile(f.getName()); });
		
	}

	private void testFile(String fileName) {
		if (checkFileName(fileName)) {
			testFileAndWriteReport(fileName); // Old file will be overwritten
		} else {
			System.out.println("File name is incorrect!");
		}
	}

	/*
	 * Print names of all files from the sample folder
	 */
	private void showFiles() {
		System.out.println("Sample files:");
		fileProcessor((File f) -> { if (f.isFile()) System.out.println(f.getName()); });
	}

	/*
	 * Print to console help information
	 */
	private void showHelp() {
		System.out.println("Help:\n"
				+ " h - show help\n"
				+ " f - show sample files\n"
				+ " t - test a file\n"
				+ " a - test all file *.rb\n"
				+ " e - exit");
	}
	
	private void testFile(Scanner scanner) {
		System.out.println("All files (from sample folder):");
		showFiles();
		System.out.print("Enter the name of the file which will test: ");
		String fileName = scanner.nextLine();
		
		if (checkFileName(fileName)) {
			testFileAndWriteReport(fileName); // Old file will be overwritten
		} else {
			System.out.println("File name is incorrect!");
		}
	}
	
	/*
	 * Check if file with such name there is in sample folder
	 */
	private boolean checkFileName(String fileName) {

		File folder = new File(samples);
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
			if (file.isFile() && file.getName().equals(fileName)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Extract metrics and write it in the report file
	 * Precondition: fileName is always correct
	 */
	private void testFileAndWriteReport(String fileName) {
		
		FileInputStream fileContent;
		try {
			fileContent = new FileInputStream(samples + "/" + fileName);
			
			MetricsCounter metricsCounter = new MetricsCounter(samples + "/" + fileName);
			writeReport(metricsCounter, fileName);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void fileProcessor(Consumer<File> consumer) {
		File folder = new File(samples);
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
			consumer.accept(file);
		}
	}
	
	/*
	 * Write report in csv file, append line to file
	 */
	private void writeReport(MetricsCounter metricsCounter, String filename) {
		writeReportHeader();
		appendMetrics(metricsCounter, filename);
	}
	
	private void writeReportHeader() {
		File file = new File(testReport);
		if (!file.exists()) {
			try {
				FileWriter fw = new FileWriter(testReport, false);
				fw.write("Filename" + "\t");
				fw.write("Date Time" + "\t");
				fw.append("Methods" + "\t");
				fw.append("Statements" + "\t");
				fw.append("Classes" + "\t");
				fw.append("Interfaces" + "\t");
				fw.append("Attributes" + "\t");
				fw.append("Loops" + "\t");
				fw.append("Conditions" + "\t");
				fw.append("Reuse Ratio" + "\t");
				fw.append("Children" + "\t");
				fw.append("Specialization Ratio" + "\t");
				fw.append("Depth of Inheritance tree" + "\t");
				fw.append("Method Inheritance Factor" + "\t");
				fw.append("Attributes Inheritance Factor" + "\t");
				fw.append("Number of overridden methods" + "\t");
				fw.append("Lack of cohesion methods" + "\t");
				fw.append("Tight class cohesion" + "\t");
				fw.append('\n');  
	            fw.flush();
	            fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void writeTimeStamp(FileWriter fw) throws IOException {
		fw.write(new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date()) + "\t");
	}
	
	public void appendMetrics(MetricsCounter metricsCounter, String filename) {

		try {
			FileWriter fw = new FileWriter(testReport, true); // Append file
			fw.append(filename + "\t");
			writeTimeStamp(fw);
			for (String metric : metricsCounter.getMetricsStrings()) {
				fw.append(metric.replace(";", " ") + "\t");
			}
            fw.append('\n');  
            fw.flush();
            fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
