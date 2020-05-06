package Database_Tables;

import java.util.Vector;

public class Customer 
{
    String msisdn;
    String f_name;
    String l_name;
    String email;
    String address;
    Vector <Customer> allCustomers;

    public Customer()
    {
        allCustomers = new Vector();
    }
    
    public Customer(String msisdn)
    {
        this.msisdn = msisdn;
    }
    
    public Customer(String msisdn, String f_name, String l_name, String email, String address)
    {
        this.msisdn = msisdn;
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.address = address;
    }

    public void setMsisdn(String msisdn) 
    {
        this.msisdn = msisdn;
    }

    public void setF_name(String f_name) 
    {
        this.f_name = f_name;
    }

    public void setL_name(String l_name) 
    {
        this.l_name = l_name;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public void setAllCustomers(Vector<Customer> allCustomers) 
    {
        this.allCustomers = allCustomers;
    }

    public String getMsisdn() 
    {
        return msisdn;
    }

    public String getF_name() 
    {
        return f_name;
    }

    public String getL_name() 
    {
        return l_name;
    }

    public String getEmail() 
    {
        return email;
    }

    public String getAddress() 
    {
        return address;
    }

    public Vector<Customer> getAllCustomers() 
    {
        return allCustomers;
    }
}
