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
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.UploadBuilder;
import com.dropbox.core.v2.files.WriteMode;
import com.esprit.utils.ConnectToDropbox;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import org.web3j.protocol.core.methods.response.Log;

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
    
    public void DownloadTmp(String idCours) throws DbxException, IOException{
        String path = "/tmp";
        File file = new File(path,idCours);

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Metadata pathMetadata = cnx.files().getMetadata("/Techwork/"+idCours);
        cnx.files().download(pathMetadata.getPathLower()).download(outputStream);
    }
    
    private static void printProgress(long uploaded, long size) {
        System.out.printf("Uploaded %12d / %12d bytes (%5.2f%%)\n", uploaded, size, 100 * (uploaded / (double) size));
    }
}
