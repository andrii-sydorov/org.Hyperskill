package projects.Medium.MusicAdvisor.Stage04;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProcessingLinks {

    private static String access;
    private static String resource;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        getCommandLineArguments(args);
        Application app = Application.getApplication(access, resource);
        app.start();
    }

    private static void getCommandLineArguments(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            map.put(args[i], args[i + 1]);
        }
        access = map.get("-access") == null ? "https://accounts.spotify.com" : map.get("-access");
        resource = map.get("-resource") == null ? "https://api.spotify.com" : map.get("-resource");
    }

}
