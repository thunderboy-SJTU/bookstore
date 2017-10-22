package bookstore3.Action.Profile;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.opensymphony.xwork2.ActionSupport;

public class ShowProfile extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key;

	public String execute() {
		Mongo mongo = new Mongo();
		DB db = mongo.getDB("test");
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = (String) request.getSession().getAttribute("user");
		GridFS gridFS = new GridFS(db, "profile");
		List<GridFSDBFile> list = new ArrayList<GridFSDBFile>();
		DBCursor cur = gridFS.getFileList();
		while (cur.hasNext()) {
			GridFSDBFile dbFile = (GridFSDBFile) cur.next();
			String totalFileName = dbFile.get("filename").toString()+dbFile.get("contentType").toString();
			if (dbFile.get("username").equals(username) && totalFileName.contains(key)) {
				list.add(dbFile);
			}
		}

		request.setAttribute("list", list);
		return SUCCESS;
	}
	
	public void setKey(String key)
	{
		this.key = key;
	}
	
	public String getKey()
	{
		return key;
	}
}
