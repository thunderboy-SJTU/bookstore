package bookstore3.Action.Profile;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.opensymphony.xwork2.ActionSupport;

public class AddProfile extends ActionSupport {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File upload;  
     private String uploadContentType;  
     private String uploadFileName;  
      
     public String execute(){
    	try {
    	if(upload == null)
    		return ERROR;
    	Mongo mongo = new Mongo();
		DB db = mongo.getDB("test");
		HttpServletRequest request = ServletActionContext.getRequest();		
		String username = (String)request.getSession().getAttribute("user");
		GridFS gridFS= new GridFS(db,"profile");
		GridFSInputFile gfs = gridFS.createFile(upload);
		gfs.put("username", username);
		gfs.put("filename", uploadFileName.substring(uploadFileName.lastIndexOf("/")+1,uploadFileName.lastIndexOf(".")));
        gfs.put("contentType", uploadFileName.substring(uploadFileName.lastIndexOf(".")));
        gfs.save();
        return SUCCESS; 
    	}
    	 catch (Exception e) {
             e.printStackTrace();
             System.out.println("Some error occured.");
             return ERROR;
         }
     }  
  
     public File getUpload() {  
          return upload;  
     }  
  
     public void setUpload(File upload) {  
          this.upload = upload;  
     }  
  

     public String getUploadContentType() {  
          return uploadContentType;  
     }  
  
     public void setUploadContentType(String uploadContentType) {  
          this.uploadContentType = uploadContentType;  
     }  
  
     public String getUploadFileName() {  
          return uploadFileName;  
     }  
  
     public void setUploadFileName(String uploadFileName) {  
          this.uploadFileName = uploadFileName;  
     }  
}
