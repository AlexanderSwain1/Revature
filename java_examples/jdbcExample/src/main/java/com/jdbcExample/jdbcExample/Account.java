package com.jdbcExample.jdbcExample;

public class Account 
{
	private int id;
	private String email;
	private String password;
	
	public Account(int id, String email, String password)
	{
		this.id = id;
		this.email = email;
		this.password = password;
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int value)
	{
		id = value;
	}
	
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String value)
	{
		email = value;
	}
	
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String value)
	{
		password = value;
	}
	
	@Override
	public String toString()
	{
		return "(Id: " + id + "; Email: " + email + "; Password" + password + ")";
	}
}
