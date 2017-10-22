package bookstore3.Service;

import java.util.List;

import bookstore3.DAO.UserDao;
import bookstore3.Entity.User;

public class UserServiceImpl implements UserService {
  private UserDao userDao;
  
  public List<User> listAllUser(int offset, int rows)
  {
	  return userDao.listAllUser(offset, rows);
  }
  
  public List<User> listUser(String key,int offset, int rows)
  {
	  return userDao.listUser(key, offset, rows);
  }
  
  public boolean add(User user)
  {
	 return userDao.add(user);
  }
  
  public boolean update(User user)
  {
	  return userDao.update(user);
  }
  
  public boolean remove(User user)
  {
	  return userDao.remove(user);
  }
  
  public User getUser(String username)
  {
	  return userDao.getUser(username);
  }
  
  public int getTotalCount()
  {
	  return userDao.getTotalCount();
  }
  
  public UserDao getUserDao(){
	  return userDao;
  }
  public void setUserDao(UserDao userDao){
	  this.userDao = userDao;
  }
  

}
