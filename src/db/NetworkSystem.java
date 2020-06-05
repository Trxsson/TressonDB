package db;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import db.main.Launcher;
import other.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;
import java.util.UUID;

public class NetworkSystem extends Logger {

    private static ServerSocket serv;

    public static void startNet(){
        try{
            serv = new ServerSocket(Launcher.port);
            log(Modes.info, "Started..");
            listen();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void listen(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket client = serv.accept();
                    listen();
                    PrintWriter wr = new PrintWriter(client.getOutputStream(), true);
                    BufferedReader rd = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    wr.println("[KEYPAIR]");
                    String password = new String(Base64.getDecoder().decode(rd.readLine()));
                    if(password.equals(HarcodeConfig.password)){
                        String gen = UUID.randomUUID().toString();

                        Launcher.session_ids.put(gen, true);
                        wr.println("[SESSION_ID] " + new String(Base64.getEncoder().encodeToString(gen.getBytes())));
                        log(Modes.reminder, "New Session id: " + gen);
                        
                        String readed = "";
                        while((readed = rd.readLine()) != null){
                            if(readed.startsWith("[DATABASES]")){
                                String pack = "";
                                pack += DatabaseSystem.dumpDataBaseToString();

                                wr.println(pack);
                            }else if(readed.startsWith("[VERSION]")){
                                wr.println("[VERSION] v1.0 TressonDB");
                            }else if(readed.startsWith("[CREATE]")){
                                String[] split = readed.split(" ");
                                if(split.length == 2){
                                    String name = split[1];
                                    if(!(new File(Launcher.getDatebases_folder().getPath() + "/" + name + "/").exists())){
                                        new File(Launcher.getDatebases_folder().getPath() + "/" + name + "/").mkdir();

                                        log(Modes.info, "Created new DB: \"" + name + "\"!");

                                        wr.println("[CREATED] " + name);
                                    }else{
                                        wr.println("[ERROR]002");
                                    }
                                }else{
                                    wr.println("ERROR[001]");
                                }
                            }
                        }
                    }else{
                        log(Modes.login_error, "Someone tried to login! But failed! Dumping Informations...");
                        log(Modes.login_error, "Ipv4: " + client.getInetAddress().getHostAddress().toString());
                        log(Modes.login_error, "Password uses: " + password);
                        log(Modes.login_error, "Dumping complete...");
                        client.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }catch (IllegalArgumentException e_huso){
                    log(Modes.warning, "A huso tried to crash db!");
                }
            }
        }).start();
    }

}
