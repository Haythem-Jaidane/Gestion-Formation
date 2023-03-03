/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.utils;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;

/**
 *
 * @author LENOVO
 */

public class ConnectToDropbox {
    
    private static final String ACCESS_TOKEN = "sl.BZ39cI6hz9SsjxnoCsRzhlqgksczzddcUkcU3HCSyYfjDGwkYwrT_TlnlmSSZNZYJQceFwtuTO0NNJ3pTqFkrl7A0yuS76vMgrxQy3IjpbkG8d81VgTTB14xxtdzy51QB5gV9DE";
    private DbxClientV2 client;
    
    private static ConnectToDropbox instance;
    
    private ConnectToDropbox() throws DbxException{
 
            DbxRequestConfig config = DbxRequestConfig.newBuilder("TeckWork").build();
            client = new DbxClientV2(config, ACCESS_TOKEN);
            FullAccount account = client.users().getCurrentAccount();
            System.out.println(account.getName().getDisplayName());
    }
    
    public static ConnectToDropbox getInstance() throws DbxException {
        if(instance == null) {
            instance = new ConnectToDropbox();
        }
        return instance;
    }

    public DbxClientV2 getClient() {
        return client;
    }
}
