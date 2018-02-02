package com.toinouf.scoketio;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.github.nkzawa.socketio.client.SocketIOException;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {
    /*Pour les lib
    import com.github.nkzawa.socketio.client.Socket;
    import com.github.nkzawa.socketio.client.SocketIOException;
    Il faut regarder le Gradle (dependencies) c'est notre gestionnaire de package
    Et pour que ça fonctionne il faut les permissions pour utiliser internet -> AndroidManifest.xml
    <uses-permission android:name="android.permission.INTERNET" />
    */
    Button b1;
    TextView t1;
    //Ici je comprend pas comment ça marche mais ça marche
    private Socket mSocket;
    { // surtour ici      ^  Je comprend pas on met point virgule et ensuite du code entre { } Bah du coup fromage.
        try {
            // Ici il faut que tu mette ton IP -> Terminal -> ifconfig et en0 -> la tu as ton ip local : 8080 c'est le port d'écoute du serveur node
            mSocket = IO.socket("http://192.168.1.32:8080");
            Log.e("CONNECTED", "SUCCESS");
        } catch (URISyntaxException e) {
            Log.i("aaa","AAA");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (TextView) findViewById(R.id.key);
        //CONNECTION AU SERVEUR
        mSocket.connect();
        b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //emit permet d'envoyer un message au serveur avec un libelle et un message
                mSocket.emit("buttonfromandroid","&");
            }
        });
        //Ici on créer un listener sur le mot clé sendtophone qui est envoyé par le serv quand l'user click sur le button web.
        mSocket.on("sendtophone", onNewMessage);

    }
    //copy past de la doc de Socket.io
    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //on recup le ce qu'on envoie
                    String message = args[0].toString();;
                    Log.i("aaa",message);
                    t1.setText(message);
                }
            });
        }
    };
}
