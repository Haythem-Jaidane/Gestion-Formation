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
    
    private static final String ACCESS_TOKEN = "sl.BaMT_WYOuLQgP2yx_LL1uD5gkGDDuspK51BmUHZYKGtUwKKD7h2mFmlyX0bkUkHF-c0exLwFQMJY50eO2oN5A3fa1zvxJ56OXsth8jM5w-hLUkVSks6YrLZk5jtzbfhYoMmW7QM";
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
