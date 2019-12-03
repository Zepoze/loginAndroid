package snir2.stjolorient.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Edition_setting extends AppCompatActivity {


    public static String EXTRA_USR= "snir2.stjolorient.login.EXTRA_USR";
    public static String EXTRA_PWD= "snir2.stjolorient.login.EXTRA_PWD";
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition_setting);
        i=getIntent();
        Toast.makeText(this,"Entrez les données a sauvegarder",Toast.LENGTH_SHORT).show();
    }

    public void retour(View view) {

        String usr = ((TextView)findViewById(R.id.username)).getText().toString();
        String pwd = ((TextView)findViewById(R.id.password)).getText().toString();
        String stored_usr=i.getStringExtra(EXTRA_USR);
        String stored_pwd=i.getStringExtra(EXTRA_PWD);

        if(!usr.equals(stored_usr)&&!pwd.equals(stored_pwd)) {
            Intent in = new Intent();
            in.putExtra(EXTRA_USR, usr);
            in.putExtra(EXTRA_PWD, pwd);
            setResult(RESULT_OK, in);
            finish();
        }else{
            Toast.makeText(this,"Les id doivent çetre different de ceux déja enregistré ! ",Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onBackPressed(){
        setResult(RESULT_CANCELED);
        finish();
    }
}
