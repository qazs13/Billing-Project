package Database_Tables;


public class Admin_User 
{
    private String adminUserName;
    private String adminPassword;
    
    public Admin_User()
    {
        
    }

    public Admin_User(String adminUserName,String adminPassword)
    {
        this.adminUserName = adminUserName;
        this.adminPassword = adminPassword;
    }    
    
    public void setAdminUserName(String adminUserName) 
    {
        this.adminUserName = adminUserName;
    }

    public void setAdminPassword(String adminPassword) 
    {
        this.adminPassword = adminPassword;
    }    

    public String getAdminUserName() 
    {
        return adminUserName;
    }
    
    public String getAdminPassword() 
    {
        return adminPassword;
    }

}
