package com.bong.smart;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bong.common.FileManager;

@Controller("smart.imageController")
public class ImageController {
	@Resource(name="fileManager")
	private FileManager fileManager;

	// HTML 5을 지원하지 않는 브라우저
	@RequestMapping(value="/image/generalUpload", 
			method={RequestMethod.POST, RequestMethod.GET})
	public String generalUpload(HttpServletRequest req,
			HttpSession session, Image image) throws Exception {
		
			String cp=req.getContextPath();
		
		    String root = session.getServletContext().getRealPath("/");
		    String pathname = root + File.separator + "uploads" + File.separator + "image";
			File dir = new File(pathname);
			if(!dir.exists())
				dir.mkdirs();

		    String strUrl = image.getCallback() + "?callback_func=" + image.getCallback_func();
		    
		    // 단일 파일인 경우
		    boolean flag=false;
		    String saveFilename = fileManager.doFileUpload(image.getFiledata(), pathname);
		    
		    if(saveFilename!=null) {
				String fulllpathname=pathname+"/"+saveFilename;
				int width=fileManager.getImageWidth(fulllpathname);
				if(width>500)
					width=500;
		    	
		    	strUrl += "&bNewLine=true&sFileName="; 
                strUrl += saveFilename;
                strUrl += "&sWidth="+width;
                strUrl += "&sFileURL="+cp+"/uploads/image/"+saveFilename;
                
                flag=true;
		    }
			
			if(! flag) {
				strUrl += "&errstr=error";
			}
			
			return "redirect:"+strUrl;
	}

	// HTML 5 upload
	@RequestMapping(value="/image/html5Upload", 
			method={RequestMethod.POST, RequestMethod.GET})
	public void html5Upload(HttpServletRequest req,
			HttpServletResponse resp,
			HttpSession session) throws Exception {
		
			String cp=req.getContextPath();
		
		    String root = session.getServletContext().getRealPath("/");
		    String pathname = root + File.separator + "uploads" + File.separator + "image";
			File dir = new File(pathname);
			if(!dir.exists())
				dir.mkdirs();

		    String strUrl ="";
		    if(!"OPTIONS".equals(req.getMethod().toUpperCase())) {
		    	String filename=req.getHeader("file-name");
		    	// String ext=filename.substring(filename.lastIndexOf("."));
		    	
		    	InputStream is=req.getInputStream();
		    	String saveFilename = fileManager.doFileUpload(is, 
		    			filename, pathname);
		    	
				String fulllpathname=pathname+"/"+saveFilename;
				int width=fileManager.getImageWidth(fulllpathname);
				if(width>500)
					width=500;
		    	
				strUrl += "&bNewLine=true&sFileName="; 
                strUrl += saveFilename;
                strUrl += "&sWidth="+width;
                strUrl += "&sFileURL="+cp+"/uploads/image/"+saveFilename;
		    }
			
			PrintWriter out=resp.getWriter();
			out.print(strUrl);
	}
}
