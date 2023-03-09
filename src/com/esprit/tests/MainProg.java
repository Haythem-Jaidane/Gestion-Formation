/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.tests;

import com.esprit.entities.*;
import com.esprit.services.*;
import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.SpeedTestSocket;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;

/**
 *
 * @author abdel
 */
public class MainProg {
    
    
    
    public static void main(String[] args) throws IOException, InterruptedException {
        
        /*ServiceCours sp1 = new ServiceCours();
        ServiceChapitre sp2 = new ServiceChapitre();
        ServiceContenu sp3 = new ServiceContenu();
        ServiceProgres sp4 = new ServiceProgres();*/
        
        

        
        // ---------------------- COURS ------------------------
        
        //sp1.ajouter(new Cours("55dd", "Introduction au web","55","web"));
        //sp1.modifier(new Cours("55dd9", "jad alik","gtt","web"));
        //sp1.supprimer(new Cours("55dd9"));
        //System.out.println(sp1.afficher());
        
        // --------------------- Chapitre ----------------------
        
        
        //sp2.ajouter(new Chapitre("655dd","html","55dd9"));
        //sp2.modifier(new Chapitre("655dd","css","55dd9"));
        //sp2.supprimer(new Chapitre("655dd"));
        //sp2.afficher(new Chapitre("655dd","html","55dd9"));
        
        // ------------------------ CONTENU --------------------
        
        //sp3.ajouter(new Contenu("888","video",5,"www","655dd"));
        //sp3.modifier(new Contenu("888","video",5,"www","655dd"));
        //sp3.supprimer(new Contenu("888"));
        //System.out.println(sp3.afficher());
        
        // ------------------------ Tuteur ---------------------
        
        //sp5.ajouter(new Tuteur("gtt", "ff", "haythem", 85558855, "99999+"));
        //sp5.modifier(new Tuteur("gtt","ff","jasser",1525852,"............"));
        //sp5.supprimer(new Tuteur("gtt"));
        //System.out.println(sp5.afficher());
        
        // ------------------------ Progres --------------------
        
        //sp4.ajouter(new Progres("55dd9", "gtt", 50, 0, false));
        //sp4.modifier(new Progres("55dd9", "ff", 100, 15, true));
        //sp4.supprimer(new Progres("55dd9", "ff"));
        //System.out.println(sp4.afficher());
        
        List<PcapIf> allDevs = new ArrayList<>();
        StringBuilder errbuf = new StringBuilder();

        // Get a list of available network interfaces
        int r = Pcap.findAllDevs(allDevs, errbuf);
        if (r == Pcap.NOT_OK || allDevs.isEmpty()) {
            System.err.printf("Can't read list of devices, error is %s", errbuf.toString());
            return;
        }

        // Choose a network interface to capture on
        PcapIf device = allDevs.get(0);

        // Open the selected network interface for capturing
        int snaplen = 64 * 1024; // Capture all packets, no truncation
        int flags = Pcap.MODE_PROMISCUOUS; // Capture packets that are not for this host
        int timeout = 10 * 1000; // 10 seconds in milliseconds
        Pcap pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);
        if (pcap == null) {
            System.err.printf("Error while opening device for capture: %s", errbuf.toString());
            return;
        }

        // Create a packet handler to process captured packets
        PcapPacketHandler<String> packetHandler = new PcapPacketHandler<String>() {
            private long lastTime = System.currentTimeMillis();
            private long lastCount = 0;

            @Override
            public void nextPacket(PcapPacket packet, String user) {
                long currentTime = System.currentTimeMillis();
                long currentCount = lastCount + 1;

                double speedMbps = ((currentCount - lastCount) * 8.0 / (currentTime - lastTime)) / 1000000.0;
                System.out.printf("Network speed: %.2f Mbps\n", speedMbps);

                lastTime = currentTime;
                lastCount = currentCount;
            }
        };

        // Start capturing packets and processing them with the packet handler
        pcap.loop(Pcap.LOOP_INFINITE, packetHandler, null);

        // Cleanup when done capturing
        pcap.close();
    }

}
