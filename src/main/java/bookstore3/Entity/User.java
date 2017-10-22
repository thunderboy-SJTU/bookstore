package bookstore3.Entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
private String username;
private String password;
private String name;
private String address;
private String city;
private int authority;

public void setUsername(String value) {
	username = value;
}

public void setPassword(String value) {
	password = value;
}

public void setName(String value)
{
	name = value;
}
public void setAddress(String value) {
	address = value;
}

public void setCity(String value) {
	city = value;
}

public void setAuthority(int value) {
	authority = value;
}


@Id
public String getUsername() {
	return username;
}

public String getPassword() {
	return password;
}

public String getName()
{
	return name;
}

public String getAddress() {
	return address;
}

public String getCity() {
	return city;
}


public int getAuthority() {
	return authority;
}
}
