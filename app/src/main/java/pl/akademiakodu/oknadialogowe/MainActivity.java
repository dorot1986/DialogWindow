package pl.akademiakodu.oknadialogowe;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static android.R.id.progress;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        //showDialog();
        new AsyncTask().execute();
      //  showProgressDialog();
    }

    private void showDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

//        new AlertDialog.Builder(this).setTitle("aaa").setMessage("bbb").create(); //wzorzec budowniczego //z dialog builderem

        dialogBuilder.setTitle("Testowe okno");
        dialogBuilder.setMessage("To jest nasze testowe okno");
        dialogBuilder.setCancelable(false); // blokuje wyjście z okienka bez użycia podanych przycisków
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Kliknięto przycisk OK", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });
        dialogBuilder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Kliknięto przycisk Nie", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();

    }

    public void showProgressDialog() {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Pobieranie pliku");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setMessage("Pobieram plik");
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.show();

    }
    private class AsyncTask extends android.os.AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            progressDialog.show();
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Rozpoczynam pracę");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.setMessage("Kończę pracę");
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            switch (values[0]){
                case 1:{
                    progressDialog.setMessage("Coś robię 1");
                    break;
                }
                case 2: {
                    progressDialog.setMessage("Coś robię 2");
                    break;
                }
                case 3: {
                    progressDialog.setMessage("Coś robię 3");
                    break;
                }
                case 4: {
                    progressDialog.setMessage("Coś robię 4");
                    break;
                }
                case 5: {
                    progressDialog.setMessage("Coś robię 5");
                    break;
                }
            }

        }

        @Override
        protected Void doInBackground(Void... arg) {
            for (int i=0; i<=5; i++){
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }
    }
}
