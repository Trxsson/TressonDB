package other;

import org.json.simple.JSONObject;

import java.io.File;

public class ConfigFile {

    private JSONObject own = new JSONObject();
    private File ownFile;

    public ConfigFile(String path){
        ownFile = new File(path);
    }

}
