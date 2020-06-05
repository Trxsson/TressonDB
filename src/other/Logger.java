package other;

public class Logger {

    public enum Modes {
        normal,
        info,
        warning,
        reminder,
        error,
        login_error,
        ok
    }

    public static void log(Modes mode, String text){
        String colors = "";
        if ( mode == Modes.normal ) {
            colors = "\u001b[37m[NORMAL] ";
        } else if ( mode == Modes.info ) {
            colors = "\u001b[36m[INFO] ";
        } else if ( mode == Modes.warning ) {
            colors = "\u001b[33m[WARNING] ";
        } else if ( mode == Modes.reminder ) {
            colors = "\u001b[32m[REMINDER] ";
        } else if ( mode == Modes.error ) {
            colors = "\u001b[31m[ERROR] ";
        } else if ( mode == Modes.login_error ) {
            colors = "\u001b[31m[ERROR] ";
        } else if ( mode == Modes.ok ) {
            colors = "\u001b[32m[OK] ";
        }
        System.out.println(colors + text);
    }

}
