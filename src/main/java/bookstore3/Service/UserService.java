package bookstore3.Service;

import java.util.List;

import bookstore3.Entity.User;

public interface UserService {
	public List<User> listAllUser(int offset, int rows);
	public List<User> listUser(String key,int offset, int rows);
	public boolean add(User user);
	public boolean update(User user);
	public boolean remove(User user);
	public User getUser(String username);
	public int getTotalCount();
	
}
