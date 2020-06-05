package db;

import db.main.Launcher;
import other.Logger;
import utils.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DatabaseSystem extends Logger {

    public static void startUp(){
        log(Modes.info, "Started Database FSystem");
    }

    public static List<String> dumpDataBases(){
        List<String> databases = new ArrayList<>();

        for(File folder : Launcher.getDatebases_folder().listFiles()){
            if(folder.isDirectory()){
                databases.add(folder.getName());
            }
        }

        return databases;
    }

    public static String dumpDataBaseToString(){
        String result = "[DATABASES]";

        result += " " + TextUtils.ListToStringDump(dumpDataBases());

        return result;
    }

}
