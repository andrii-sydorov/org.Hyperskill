package HttpServer;

import java.util.Base64;

public class HttpBasic {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String encode = "SV9hZG1pcmU6eW91cl9za2lsbHM=";
        byte[] decodeBytes = Base64.getDecoder().decode(encode);
        System.out.println(new String(decodeBytes));
        System.out.println();
        String login = "admin";
        String password = "123456";
        String data = login + ":" + password;
        String encodedString = Base64.getEncoder().encodeToString(data.getBytes());
        System.out.println(encodedString);
        String credeentials = "aXJvdDo5MTkyMDg1MjUxOTIwbWVvdw==";
        String decodedData = new String(Base64.getDecoder().decode(credeentials));
        System.out.println(decodedData);
    }

}
