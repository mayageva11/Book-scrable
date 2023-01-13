package test;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class BookScrabbleHandler implements ClientHandler  {
    private Scanner in;
    private PrintWriter out;

    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        in = new Scanner(inFromclient);
        out = new PrintWriter(outToClient);
        String text= in.nextLine();
        String relevent = text.substring(2);
        String[] books = relevent.split(",");
        //check the first letter of the word
        if (text.startsWith("Q"))// activate query method
        {
            if (DictionaryManager.get().query(books)) {
                out.println("true\n");
            } else {
                out.println("false\n");
            }
        }
        if (text.startsWith("C")) //activate challenge method
        {
            if (DictionaryManager.get().challenge(books)) {
                out.println("true\n");
            } else {
                out.println("false\n");
            }
        }
        out.flush();
    }

    @Override
    public void close() {
        in.close();
        out.close();

    }
    
}

