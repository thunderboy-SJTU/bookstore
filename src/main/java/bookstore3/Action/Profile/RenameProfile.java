package bookstore3.Action.Profile;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class RenameProfile extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String filename;
	private JSONObject result;
	public String execute() throws Exception {
		Mongo mongo = new Mongo();
		DB db = mongo.getDB("test");
		GridFS gridFS = new GridFS(db, "profile");
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		GridFSDBFile file = gridFS.findOne(query);
		file.put("filename", filename);
		file.save();
		JSONObject json = new JSONObject();
		json.put("success", true);
		result = json;
		return SUCCESS;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}
	
	public void setId(String id) {
		this.id = id;
	}
    
	public void setResult(JSONObject result) {
		this.result = result;
	}
	public String getFilename()
	{
		return filename;
	}
	public String getId() {
		return id;
	}
	public JSONObject getResult(){
		return result;
	}

}
