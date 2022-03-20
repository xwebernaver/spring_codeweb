package org.zerock.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zerock.domain.BoardAttachVO;
import org.zerock.mapper.BoardAttachMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class FileCheckTask {
	
	@Setter(onMethod_ = @Autowired)
	private BoardAttachMapper attachMapper;
	
	
	public String getFolderYesterDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		
		String str = sdf.format(cal.getTime());
		
		return str.replace("-", File.separator);
	}
	
	@Scheduled(cron="* 09 * * * *")
	public void CheckFiles() throws Exception {
		log.warn("File Check Task run................");
		
		log.warn("============================================");
		
		// file list in database
		List<BoardAttachVO> fileList = attachMapper.getOldFiles();
		
		// ready for check file in directory with database file list
		
		Stream<BoardAttachVO> stream = fileList.stream();
		stream.forEach(vo -> System.out.println(vo.getFileName()));

		
		List<Path> fileListPaths = fileList.stream()
				.map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName()))
				.collect(Collectors.toList());
		
		
		// image file has thumail file
		fileList.stream().filter(vo -> vo.isFileType() == true)
			.map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), "s_" + vo.getUuid() + "_" + vo.getFileName()))
			.forEach(p -> fileListPaths.add(p));
		
		log.warn("============================================");
		
		log.warn("getFolderYesterDay() : " + getFolderYesterDay());
		
		System.out.println("------------- DB 조회 파일 개수 : " + fileListPaths.size());
		fileListPaths.forEach(p -> log.warn("db file : " + p));	 // db search Files
		
		
		// files in yesterday directory
		File targetDir = Paths.get("C:\\upload", getFolderYesterDay()).toFile();	// storage dir Files
		
		
		System.out.println("------------- 디렉터리 파일 개수 : " + targetDir.listFiles().length);
		
		File[] tmpFileList = targetDir.listFiles();
		for(File f : tmpFileList) {
			String str = f.getName();
			if(f.isDirectory()) {
				log.warn(str+"\t");
				log.warn("DIR\n");
			}else {				
				log.warn(str+"\t" + f.length()+"byte\n");				
			}
		}
		

		
		
		// 디렉토리에 있는 파일(targetDir) 중에 DB 에서 조회(fileListPaths) 한 파일과 일치 하지 않다면 삭제 대상이 된다  
		File[] removeFiles = targetDir.listFiles(
				file -> fileListPaths.contains(file.toPath()) == false);
		
		log.warn("------------------------------------------");
		
		for(File file : removeFiles) {
			log.info("delete file : " + file.getAbsolutePath());
			log.warn("delete file : " + file.getAbsolutePath());
			
			file.delete();
		}
		
	}
}











