package org.trahim.objects;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	@Size(min = 4,message = "{name.size.error}")
	private String name;

	@Size(min = 6, max = 10, message = "{password.size.error}")
	private String password;

	private boolean admin;

	public User() {
	}

	public User(String name) {
		super();
		this.name = name;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
