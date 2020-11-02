package consoleApp.models;

public class Account 
{
	private int id;
	private String email;
	private String password;
	private Role role;
	
	public Account(int id, String email, String password, Role role)
	{
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
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
	
	public Role getRole()
	{
		return role;
	}
	
	public void setRole(Role value)
	{
		role = value;
	}
	
	@Override
	public String toString()
	{
		return "{ id: " + id + "; email: " + email + "; password: " + password + " }";
	}
	
	public enum Role
	{
		Customer,
		Employee,
	}
}
