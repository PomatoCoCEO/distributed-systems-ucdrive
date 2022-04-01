package com.DataTransfer.creators;

import com.DataTransfer.tasks.FileUploadTask;
import com.DataTransfer.threads.ServerUpload;
import com.Server.Server;

public class FileTransferUploadCreator extends Thread {
    private Server server;
    public FileTransferUploadCreator(Server server) {
        this.server = server;
        this.start();
    }

    public void run() {
        System.out.println("File Transfer tcp creator alive!");
        while(true) {
            try {
                FileUploadTask ftt = server.getQueueFileRcv().take(); // waits until a new file arrives
                System.out.println("Tirou da queue");
                server.getThreadPoolFiles().execute(new ServerUpload(ftt.getServerConnection(),ftt.getServerSocket()));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }     
        }
    }
}