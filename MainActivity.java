package snir2.stjolorient.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public static int REQUEST_EDITION = 2;

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);Toast.makeText(this, "Connexion !", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_main);
    }

    public void connexion(View view) {
        String stored_usr = prefs.getString("username", "admin");
        String stored_pwd = prefs.getString("password", "admin");

        String usr = ((TextView) findViewById(R.id.username)).getText().toString();
        String pwd = ((TextView) findViewById(R.id.password)).getText().toString();

        if (usr.equals(stored_usr) && pwd.equals(stored_pwd)) {
            Toast.makeText(this, "Connexion réussie !", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Edition_setting.class);
            i.putExtra(Edition_setting.EXTRA_USR,stored_usr);
            i.putExtra(Edition_setting.EXTRA_PWD,stored_pwd);
            startActivityForResult(i, REQUEST_EDITION);

        } else {
            Toast.makeText(this, "Connexion echouée  !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_EDITION)
        {
            if(resultCode==RESULT_OK){

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("username", data.getStringExtra(Edition_setting.EXTRA_USR));
                editor.putString("password", data.getStringExtra(Edition_setting.EXTRA_PWD));
                editor.commit();
                Toast.makeText(this, "Sauvegardé ! !", Toast.LENGTH_SHORT).show();
            }

            if (resultCode==RESULT_CANCELED){
                Toast.makeText(this, "Non sauvegardé", Toast.LENGTH_SHORT).show();
            }
        }
    }
}