package cms.app.net;

import android.content.Intent;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//import cms.app.AdminActivity;
//import cms.app.MainActivity;
//import cms.app.StudentActivity;
//import cms.app.TeacherActivity;

public class CMSClient {
    static  String server_addr = "172.20.13.17";
    static int server_port = 20000;
    public static String sendAndReceive(String msg){
        String retMsg = "";
        Socket sock = null;
        DataOutputStream outStm = null;
        DataInputStream inStm = null;
        try{
            sock = new Socket(server_addr,server_port);
//                    InetSocketAddress localAddr = (InetSocketAddress)sock.getLocalSocketAddress();
//                    InetSocketAddress remoteAddr = (InetSocketAddress)sock.getRemoteSocketAddress();
//                    Log.d("red","local:"+localAddr.getAddress().getHostAddress()+":"+localAddr.getPort());
//                    Log.d("red","remote:"+remoteAddr.getAddress().getHostAddress()+":"+remoteAddr.getPort());
            outStm = new DataOutputStream(sock.getOutputStream());
            outStm.writeUTF(msg);
            inStm = new DataInputStream((sock.getInputStream()));
            retMsg =  inStm.readUTF();
        }catch (IOException ex){
            Log.d("red ",ex.toString());
        }finally {
            try{
                if(outStm!=null)outStm.close();
                if(inStm!=null)outStm.close();
                if(sock!=null)sock.close();
            }catch(IOException ex){

            }
        }
        return retMsg;
    }
}
