package sample04;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileOutputter implements Outputter {
	private String filePath, fileName;

	public FileOutputter() {
		System.out.println("FileOutputter 기본 생성자");
	}

	@Autowired
	public void setFilePath(@Value("C:/Spring/") String filePath) {
		System.out.println("setFilePath 메소드");
		this.filePath = filePath;
	}
	
	@Autowired
	public void setFileName(@Value("result_maven.txt") String fileName) {
		System.out.println("setFileName 메소드");
		this.fileName = fileName;
	}

	@Override
	public void output(String message) {
		// 파일로 출력
		System.out.println("output 메소드");
		try {
			FileWriter fileWriter = new FileWriter(filePath + fileName);
			fileWriter.write(message);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
