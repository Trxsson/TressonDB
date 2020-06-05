package db.main;

import db.NetworkSystem;
import other.Logger;

import java.io.File;
import java.util.HashMap;

public class Launcher extends Logger {

    public static int port = 5060;

    private static File main_folder = new File("TressonDB/");
    private static File configs_folder = new File("TressonDB/configs/");
    private static File datebases_folder = new File("TressonDB/databases/");
    //private static File websites_folder = new File("TressonDB/web/");

    public static HashMap<String, Boolean> session_ids = new HashMap<>();

    public static void main(String[] args) {
        log(Modes.info, "Initializing...");
        log(Modes.info, "Starting Network system....");
        NetworkSystem.startNet();
        if(!main_folder.exists()){
            main_folder.mkdir();
        }
        if(!configs_folder.exists()){
            configs_folder.mkdir();
        }
        if(!datebases_folder.exists()) {
            datebases_folder.mkdir();
        }
    }

    public static File getMain_folder() {
        return main_folder;
    }

    public static File getDatebases_folder() {
        return datebases_folder;
    }
}
