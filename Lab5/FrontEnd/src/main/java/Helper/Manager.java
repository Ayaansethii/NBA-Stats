/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

/**
 *
 * @author student
 */

public class Manager {
    private int id;
    private String username;
    private String password; // For simplicity store plain text (in production, use hashing)

    public Manager(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    // Getters and setters
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
