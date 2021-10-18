package com.southgis.webgis.SSHConnection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SSHWebListener implements ServletContextListener {

    private SSHConnection sshConnection;

    public SSHWebListener(){
        super();
    }
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("SSHWebListener initialized ... !");
        try {
            sshConnection = new SSHConnection();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("SSHWebListener destroyed ... !");
        sshConnection.closeSSH();
    }
}
