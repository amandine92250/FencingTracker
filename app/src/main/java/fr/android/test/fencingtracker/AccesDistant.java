package fr.android.test.fencingtracker;

import android.util.Log;

import org.json.JSONArray;

public class AccesDistant implements AsynResponse {


    private static final String SERVEURADDR = "http://192.168.0.36/match/serveurmatch.php";

    public AccesDistant()
    {
        super();
    }
    //retour du serveur distant
    @Override
    public void processFinish(String output) {

        Log.d("serveur", "***"+output);
        //decoupage du msg recu
        String[] message = output.split("%");

        if(message.length>1)
        {
            if(message[0].equals("enreg"))
            {
                Log.d("enreg", "***"+message[1]);
            }else if (message[0].equals("dernier"))
        {
            Log.d("dernier", "***"+message[1]);
        }else if (message[0].equals("erreur"))
        {
            Log.d("erreur", "***"+message[1]);
        }
        }


    }

    public void envoi(String operation, JSONArray lesDonneesJson){
        AccesHTTP accesDonnees = new AccesHTTP();
        //line de delegation
        accesDonnees.delegate =this;
        //Ajout parametre
        accesDonnees.AddParam("operation",operation);
        accesDonnees.AddParam("lesdonnees",lesDonneesJson.toString());
        //Appel au serveur
        accesDonnees.execute(SERVEURADDR);

    }
}
