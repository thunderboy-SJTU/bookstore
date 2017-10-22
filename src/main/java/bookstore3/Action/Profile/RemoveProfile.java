package bookstore3.Action.Profile;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.opensymphony.xwork2.ActionSupport;

public class RemoveProfile extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String id;

	public String execute() throws Exception {
		Mongo mongo = new Mongo();
		DB db = mongo.getDB("test");
		GridFS gridFS = new GridFS(db, "profile");
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(id));
		gridFS.remove(query);
		return SUCCESS;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
