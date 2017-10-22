package bookstore3.Entity;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
   
   private int catid;
   private String catname;
   
   public void setCatid(int catid)
   {
	   this.catid = catid;
   }
   
   public void setCatname(String catname)
   {
	   this.catname = catname;
   }
   
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO) 
   public int getCatid()
   {
	   return catid;
   }
   
   public String getCatname()
   {
	   return catname;
   }
   
}
