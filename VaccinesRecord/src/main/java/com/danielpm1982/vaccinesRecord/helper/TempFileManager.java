package com.danielpm1982.vaccinesRecord.helper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

public class TempFileManager {
	public static Path createTempFileFromByteArray(byte[] byteArray, String fileName, String basePath, int maxNumberOfFilesAtDirectory) { //creates a temp folder, checks and empty it if full, creates file from byte[] with currentTimeMillis+fileName at the temp folder and returns the File Path.
		createTempDirectoryIfNotExists(basePath);
		InputStream fileInputStream = new ByteArrayInputStream(byteArray);
		long currentTimeInMillis = System.currentTimeMillis();
		Path tempFilePath = Paths.get(basePath+"/temp/"+currentTimeInMillis+fileName);
		try {
			Files.copy(fileInputStream, tempFilePath, StandardCopyOption.REPLACE_EXISTING);
			return tempFilePath;
		} catch (IOException e) {
			throw new RuntimeException("Error creating tempFile!");
		}
	}
	private static void createTempDirectoryIfNotExists(String basePath) { //create temp directory.
		if(!Files.exists(Paths.get(basePath+"/temp"))) {
			try {
				Files.createDirectory(Paths.get(basePath+"/temp"));
			} catch (IOException e) {
				throw new RuntimeException("Error creating temp directory!");
			}
		}
	}
	public static void checkIfTempDirectoryIsFull(int maxNumberOfFilesAtDirectory, String basePath) { //limit disk temp directory storage to something close to maxNumberOfFilesAtDirectory (>=). Must be called externally, before the whole group of semantically related images are generated (all images that will be sent to a .jsp, for ex.), so that no image of the same group is deleted before being shown to the user (sent to the .jsp). On the next iteration, the directory is checked again and, if full (>= maxNumberOfFilesAtDirectory), is erased. Always before each group of images is generated, for avoiding the risk of showing blank(deleted) images.
		createTempDirectoryIfNotExists(basePath);
		List<Path> pathList = getPathsFromTempDirectoryIfExists(basePath);
		if(pathList!=null&&pathList.size()>=maxNumberOfFilesAtDirectory) {
			emptyTempDirectoryIfExists(basePath);
		}
	}
	private static void emptyTempDirectoryIfExists(String basePath) { //empty all files at directory.
		if(Files.exists(Paths.get(basePath+"/temp"))) {
			try {
				Files.list(Paths.get(basePath+"/temp")).forEach(x->{
					try {
						Files.deleteIfExists(x);
					} catch (IOException e) {
						throw new RuntimeException("Failed to delete temp files!");
					}
				});
			} catch (IOException e) {
				throw new RuntimeException("Error deleting temp files!");
			}
		}
	}
	public static List<Path> getPathsFromTempDirectoryIfExists(String basePath) { //get list of all File Paths from directory.
		Path dirPath = Paths.get(basePath+"/temp");
		if(Files.exists(dirPath)&&Files.isDirectory(dirPath)) {
			try {
				List<Path> filesPathList = Files.list(dirPath).collect(Collectors.toList());
				return filesPathList;
			} catch (IOException e) {
				throw new RuntimeException("Error reading temp directory!");
			}
		} else {
			return null;
		}
	}
}
