package projects.Medium.MusicAdvisor.Stage03;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Stage 3/5: Useful connections
 * <ol>
 * <li>Description</li>
 * <p>As you can see from the previous stage, we need to find a way to get a
 * response code from the URL in the user's browser. By default, Java doesn't
 * have browser capabilities but has opportunities to create your own simple
 * HTTP server.</p>
 * 
 * <li>What is HTTP</li>
 * <p>HTTP means Hypertext Transfer Protocol, which is a stateless data transfer
 * protocol based on client-server technology, where messaging occurs according
 * to the request-response scheme. The main manipulation object is the resource
 * indexed by the URI. Every time you need to make an HTTP request, follow this
 * structure:</p>
 * 
 * <p>A request line:</p>
 * 
 * <code>method_name URI HTTP/version</code>
 * 
 * <p>Example:</p>
 * 
 * <code>GET /some/uri HTTP/1.1</code>
 * <p>There are a lot of HTTP methods, but in this project, we will consider two of
 * them:</p>
 * <p><b>GET</b> requests a representation of the specified resource. They only retrieve
 * data and have no other effect.</p>
 * <p>The <b>POST</b> method requests that the server accepts the entity enclosed in the
 * request as a new subordinate of the web resource identified by the URI.</p>
 * <p>The response looks like this:</p>
 * <p>A response line:</p>
 * <code>HTTP/version status_code message</code>
 * <p>Example:</p>
 * <p><code>HTTP/1.1 200 OK</code></p>
 * 
 * <li>Creating a server in java</li>
 * 
 * <p><b>com.sun.net.httpserver</b> package included in Java SE contains classes. The main
 * class is <b>HttpServer</b>. This class implements a simple HTTP server. To create an
 * instance of this class, you need to use static factory method "create" and
 * bind it to IP and port.</p>
 * 
 * <code>HttpServer server = HttpServer.create();</code>
 * <code>server.bind(new InetSocketAddress(8080), 0);</code>
 * 
 * <p>These lines will create an <b>http</b> server that will listen for incoming TCP
 * connections from clients on 8080 port. Another main concept is context. When
 * an HTTP request is received, the appropriate <b>HttpContext</b> (and handler) is
 * located by finding the context whose path is the longest matching prefix of
 * the request URI's path. To create the context, you should use the method
 * <b>createContext</b> and pass a string of URI path and handler that implements the
 * <b>HttpHandler</b> interface.</p>
 * 
 * <p><code>server.createContext("/",
 *  new HttpHandler() {
 *      public void handle(HttpExchange exchange) throws IOException {
 *      String hello = "hello, world";
 *      exchange.sendResponseHeaders(200, hello.length());
 *      exchange.getResponseBody().write(hello.getBytes());
 *      exchange.getResponseBody().close();
 *      }
 *  }
 * );
 * </code></p>
 * 
 * <p>The lines above will create a context to which all requests will be
 * redirected by the server, and the context handler will always return "hello
 * world".</p>
 * 
 * <p>To start the server, add the line <b>server.start()</b>; use the command
 * <b>server.stop(1)</b> to shut down the server. 1 here is the maximum delay in
 * seconds to wait until all handlers have finished.</p>
 * 
 * <p>If you try to run it, you can open your browser at <b>localhost:8080</b>, and you
 * will see this message.</p>
 * 
 * <p>In this stage, you will receive a query parameter with the authorization code
 * from the Spotify page. It looks like <b>http:localhost:8080?code=123</b>. To get the
 * query inside the <b>HttpExchange</b> handler, you can use the following line:</p>
 * 
 * <p><code>String query = exchange.getRequestURI().getQuery();</code></p>
 * 
 * <li>Making HTTP requests in Java</li>
 * 
 * <p>JDK 11 provides a few classes in the <b>java.net.http</b> package to make HTTP
 * requests: read more about them at openjdk.java.net. First, you should create
 * an <b>HttpClient</b> instance:</p>
 * 
 * <p><code>HttpClient client = HttpClient.newBuilder().build();</code></p>
 * 
 * <p>Then you should setup the http request by creating an <b>HttpRequest</b> instance.
 * It supports the Builder pattern, so you should just call
 * <b>HttpRequest.newBuilder()</b>, then add some methods to setup your request and
 * then call a <b>build()</b> method to create it. Here is an example how to create a
 * simple GET request:</p>
 * 
 * <p><code>HttpRequest request = HttpRequest.newBuilder()
 * .uri(URI.create("http://localhost:8080"))
 * .GET()
 * .build();</code></p>
 * 
 * <p>To send the request, use the client instance:</p>
 * 
 * <p><code>HttpResponse<String> response = client.send(request,
 * HttpResponse.BodyHandlers.ofString());
 * System.out.println(response.body());</code></p>
 * 
 * <p>This code will print "hello, world" if it connects to the server from the
 * section above.</p>
 * 
 * <p>In this stage, you will create a POST request to get the Spotify access
 * token. The main difference between GET and POST requests is that POST may
 * contain a body with some data. It may be a file, json, xml, or other format.
 * You should set the type of data with "Content-type" header. This example
 * shows how to send a POST request with data in the format
 * <b>application/x-www-form-urlencoded</b>:</p>
 * 
 * <p><code>HttpRequest request = HttpRequest.newBuilder()
 *                                  .header("Content-Type", "application/x-www-form-urlencoded")
 *                                  .uri(URI.create("http://localhost:8080"))
 *                                  .POST(HttpRequest.BodyPublishers.ofString("login=admin&password=admin"))
 *                                  .build();</code></p>
 * 
 * <p>You should put the body data inside the <b>POST()</b> method using the
 * <b>HttpRequest.BodyPublishers.ofString</b> method. <b>x-www-form-urlencoded</b> data
 * consists of <b>key=value</b> pairs, separated by the & symbol.</p>
 * 
 * <p>Similarly, you can send json data by setting "Content-type" as
 * <b>application/json</b> and passing json inside the <b>ofString()</b> method.</p>
 * 
 * <p>Useful hint: you can use reqbin to test POST and GET requests to API without
 * Java. ReqBin is an online API testing tool where you can send API requests
 * directly from the browser and check the response. You can specify the URL of
 * an API endpoint, select an appropriate HTTP method and enter the data you
 * want to send in the Content tab to send the request. Then you can check the
 * returned status code, its response time and content to see if the API
 * functions as expected.</p>
 * 
 * <li>Objectives</li>
 * 
 * <p>Using the Spotify authorization guide and the information given here (you
 * need the section Authorization Code Flow), improve your program by adding
 * real authorization on Spotify.</p>
 * <ol>
 * <li>Choose any free port on your machine (for example, 8080), and add the
 * <b>http://localhost:your_port</b> to the whitelist of <b>redirect_uri</b> in your
 * application settings on the Spotify site (Dashboard -> your app -> edit
 * settings -> redirect URIs).
 * Note that you should use the <b>http</b> protocol for localhost, not https, like in
 * the Spotify example.)</li>
 * <li>On the <b>auth</b> command, before printing the auth link (from the previous stage),
 * you should start an HTTP server that will listen for the incoming requests.
 * When the user confirms or rejects the authorization, the server should return
 * the following text to the browser:
 * <ul>
 * <li>"Got the code. Return back to your program." if the query contains the
 * authorization code.</li>
 * <li>"Authorization code not found. Try again." otherwise.</li>
 * </ul>
 * This code is bound to each user who has a Spotify account and uses your app.
 * Actually, you should ask this code once for each new user and save it
 * somewhere.</li>
 * <li>After the code is received, the server must shut down and you should get
 * <b>access_token</b> by making a POST request on
 * <code>https://accounts.spotify.com/api/token</code> with parameters described in the
 * guide, and then print the response body.</li>
 * </ol>
 * <p>Also, in this stage, you should read the Spotify access server point from the
 * command line argument. Server path should be passed to the program using
 * <b>-access</b> argument. If this argument is not set, you should use a default
 * argument, <code>https://accounts.spotify.com</code>. Make sure you replace constants to
 * this argument value everywhere!</p>
 * 
 * <li>Example</li>
 * 
 * <p>Below is an output example of the described program. Try to output all cases
 * like in the example.</p>
 * 
 * <p>The greater-than symbol followed by a space (<b>> </b>) represents the user input.
 * Note that it's not part of the input.</p>
 * 
 * > new</br>
 * Please, provide access for application.</br>
 * > auth</br>
 * use this link to request the access code:</br>
 * <code>https://accounts.spotify.com/authorize?client_id=a19ee7dbfda443b2a8150c9101bfd645&redirect_uri=http://localhost:8080&response_type=code</code></br>
 * waiting for code...</br>
 * code received</br>
 * making http request for access_token...</br>
 * response:</br>
 * <code>{"access_token":"BQBSZ0CA3KR0cf0LxmiNK_E87ZqnkJKDD89VOWAZ9f0QXJcsCiHtl5Om-EVhkIfwt1AZs5WeXgfEF69e4JxL3YX6IIW9zl9WegTmgLkb4xLXWwhryty488CLoL2SM9VIY6HaHgxYxdmRFGWSzrgH3dEqcvPoLpd26D8Y","token_type":"Bearer","expires_in":3600,"refresh_token":"AQCSmdQsvsvpneadsdq1brfKlbEWleTE3nprDwPbZgNSge5dVe_svYBG-RG-_PxIGxVvA7gSnehFJjDRAczLDbbdWPjW1yUq2gtKbbNrCQVAH5ZBtY8wAYskmOIW7zn3IEiBzg","scope":""}</code></br>
 * ---SUCCESS---</br>
 * > new</br>
 * ---NEW RELEASES---</br>
 * Mountains [Sia, Diplo, Labrinth]</br>
 * Runaway [Lil Peep]</br>
 * The Greatest Show [Panic! At The Disco]</br>
 * All Out Life [Slipknot]</br>
 * > exit</br>
 * ---GOODBYE!---</br>
 * </ol>
 */

public class Connections {

    private static String clientID = "ebaccb57b2754da89ac2a9aef07c78fa";
    private static String redirectedURI = "http://localhost:8080";
    private static String responseType = "code";
    private static String uriAuthorize = "https://accounts.spotify.com/authorize?";
    private static Scanner sc;
    private static String access;

    static {
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            map.put(args[i], args[i + 1]);
        }
        access = map.get("-access") == null ? "https://accounts.spotify.com" : map.get("-access");
        boolean isRunning = true;
        boolean isSigned = false;
        while (isRunning) {
            String userOption = sc.nextLine();
            if (!userOption.equals("auth") & isSigned == false) {
                System.out.println("Please, provide access for application.");
                continue;
            }
            switch (userOption) {
                case "featured":
                    printFeature();
                    break;
                case "new":
                    printNew();
                    break;
                case "categories":
                    printCategories();
                    break;
                case "playlists Mood":
                    printPlaylists();
                    break;
                case "auth":
                    LocalServer ls = LocalServer.getLocalServer();
                    ls.startServer();
                    System.out.println("use this link to request the access code:");
                    System.out.println(createCodeRequest());
                    System.out.println("waiting for code...");
                    while (ls.getCode() == null) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ie) {
                            System.out.println("Error by thread sleeping");
                        }
                    }
                    ls.stopServer(1);
                    if (ls.getCode() != null) {
                        System.out.println("code received");
                    } else {
                        System.out.println("Timeout error\n");
                        break;
                    }
                    LocalClient lc = LocalClient.getLocalClient();
                    System.out.println("making http request for access_token...");
                    lc.setCode(ls.getCode());
                    lc.setAccess(access);
                    lc.postRequest();
                    System.out.println("response:\n" + lc.getAccessToken());
                    System.out.println("---SUCCESS---");
                    isSigned = true;
                    break;
                case "exit":
                    printExit();
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid input command. Try again!");

            }
        }
        sc.close();
    }

    private static void printFeature() {
        String s = """
                ---FEATURED---
                Mellow Morning
                Wake Up and Smell the Coffee
                Monday Motivation
                Songs to Sing in the Shower""";
        System.out.println(s);
    }

    private static void printNew() {
        String s = """
                ---NEW RELEASES---
                Mountains [Sia, Diplo, Labrinth]
                Runaway [Lil Peep]
                The Greatest Show [Panic! At The Disco]
                All Out Life [Slipknot]""";
        System.out.println(s);
    }

    private static void printCategories() {
        String s = """
                ---CATEGORIES---
                Top Lists
                Pop
                Mood
                Latin""";
        System.out.println(s);
    }

    private static void printPlaylists() {
        String s = """
                ---MOOD PLAYLISTS---
                Walk Like A Badass
                Rage Beats
                Arab Mood Booster
                Sunday Stroll""";
        System.out.println(s);
    }

    public static void printExit() {
        String s = """
                ---GOODBYE!---
                """;
        System.out.println(s);
    }

    private static String createCodeRequest() {
        String data = String.format("client_id=%s&response_type=%s&redirect_uri=%s", clientID,
                responseType, redirectedURI);
        String address = uriAuthorize + data;
        return address;
    }

}
