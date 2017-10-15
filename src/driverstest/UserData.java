/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driverstest;

/**
 *
 * @author gamer
 */
public class UserData {
    String username = new String();
    String password = new String();
    int demoLeft = 3;
    
    public UserData (String u, String p){
        username = u;
        password = p;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String s)
    {
        username = s;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String s)
    {
        password = s;
    }
    
    public int getDemoLeft()
    {
        return demoLeft;
    }
    
    public void setDemoLeft(int i)
    {
        demoLeft = i;
    }

}
