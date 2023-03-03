/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.tests;

/**
 *
 * @author LENOVO
 */
import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CreateFolderResult;
import com.dropbox.core.v2.files.DownloadZipResult;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class TestDropbox {
    private static final String ACCESS_TOKEN = "sl.BZ3irX-xxIC8Ww6QyOM4ZDyswBjPIt4wXXVBq2fG6cJl_3ktMKXRnON0X76jQ7uGso0tLm-awQZ0XolBZmM6CehWFBpjhse9AhNozvN4xe4gUSZ1eQFTf5L69tTJrvDGA8p1bTA";

    public static void main(String args[]) throws DbxException, FileNotFoundException, IOException {
        // Create Dropbox client
        DbxRequestConfig config = DbxRequestConfig.newBuilder("TeckWork").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());
        
        CreateFolderResult result = client.files().createFolderV2("/T");
        System.out.println(result);
        /*while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }*/

    }
}