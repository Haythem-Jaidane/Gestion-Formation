/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.util.IOUtil.ProgressListener;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CreateFolderResult;
import com.dropbox.core.v2.files.DeleteResult;
import com.dropbox.core.v2.files.DownloadZipResult;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.UploadBuilder;
import com.dropbox.core.v2.files.WriteMode;
import com.esprit.utils.ConnectToDropbox;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Date;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 *
 * @author LENOVO
 */
public class ServiceAPIDropbox {
    private DbxClientV2 cnx ;
    
    public ServiceAPIDropbox() throws DbxException{
        cnx = ConnectToDropbox.getInstance().getClient();
    }
    
    public void ajouterCoursFolderDropbox(String Cours_id) throws DbxException{
        CreateFolderResult result = cnx.files().createFolderV2("/Teckwork/"+Cours_id);
        System.out.println(result);
    }
    
    public void ajouterChaptireFolderDropbox(String Cours_id,String Chapitre_id) throws DbxException{
        CreateFolderResult result = cnx.files().createFolderV2("/Teckwork/"+Cours_id+"/"+Chapitre_id);
        System.out.println(result);
    }
    
    public String ajouterContenuFileDropbox(File F,String id_Cours,String id_Chapitre,String Name) throws FileNotFoundException, IOException{
        try (InputStream in = new FileInputStream(F)) {
            ProgressListener progressListener = l -> printProgress(l, F.length());
            

            FileMetadata metadata = cnx.files().uploadBuilder("/Teckwork/"+id_Cours+"/"+id_Chapitre+"/"+Name)
                    .withMode(WriteMode.ADD)
                    .withClientModified(new Date(F.lastModified()))
                    .uploadAndFinish(in, progressListener);

            return metadata.getPathLower();
        } catch (DbxException ex) {
            System.err.println("Error uploading to Dropbox: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error reading from file \"" + F + "\": " + ex.getMessage());
        }
        return "";
    }
    
    public void supprimerFolder(String path) throws DbxException{
        DeleteResult delete = cnx.files().deleteV2("/Teckwork/"+path);
        System.out.println(delete);
    }
    
    public void supprimerFile(String path) throws DbxException{
        DeleteResult delete = cnx.files().deleteV2(path);
        System.out.println(delete);
    }
    
    public void DownloadTmp(String path) throws DbxException, FileNotFoundException, IOException{
        
            // Download folder as a zip file
            
            String FOLDER_PATH="/Teckwork/"+path;
            
            
            downloadFolder(cnx, FOLDER_PATH, System.getProperty("user.dir")+"/src/com/esprit/service/tmp/",path);
        System.out.println("Folder download completed successfully!");
            
      
    }
    
    private static void downloadFolder(DbxClientV2 client, String folderPath, String localFolderPath, String Folder_name ) throws IOException, DbxException {
        ListFolderResult result = client.files().listFolder(folderPath);
        
        File folder = new File(localFolderPath+"/"+Folder_name);
        System.out.println(folder.mkdirs());
        
        System.out.println(result);
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                if (metadata instanceof FileMetadata) {
                    System.out.println(localFolderPath);
                    downloadFile(client, (FileMetadata) metadata, localFolderPath+"/"+Folder_name);
                } else {
                    downloadFolder(client, metadata.getPathLower(), localFolderPath + "/" +Folder_name, metadata.getName());
                }
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }
    }

    private static void downloadFile(DbxClientV2 client, FileMetadata fileMetadata, String localFolderPath) throws IOException, DbxException {
        String localFilePath = localFolderPath + File.separator + fileMetadata.getName();
        try (InputStream in = client.files().download(fileMetadata.getPathLower()).getInputStream();
             OutputStream out = new FileOutputStream(localFilePath)) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            
            
        }
    }
    
    private static void printProgress(long uploaded, long size) {
        System.out.printf("Uploaded %12d / %12d bytes (%5.2f%%)\n", uploaded, size, 100 * (uploaded / (double) size));
    }
}
