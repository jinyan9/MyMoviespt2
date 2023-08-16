package sg.edu.rp.c346.id22022452.mymoviespt2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ThirdActivity extends AppCompatActivity {

    EditText etId, etTitle, etGenre, etYear;
    Spinner spinnerRating;
    Button btnUpdate, btnDelete, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main3);

        etId = findViewById(R.id.editTextTitle);
        etTitle = findViewById(R.id.editTextTitle);
        etGenre = findViewById(R.id.editTextGenre);
        etYear = findViewById(R.id.editTextYear);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
        btnCancel = findViewById(R.id.cancel_button);
        spinnerRating = findViewById(R.id.spinnerRating);

        Intent intent = getIntent();
        Movie data = (Movie) intent.getSerializableExtra("movie");
        etId.setText(String.valueOf(data.getId()));
        etTitle.setText(data.getTitle());
        etGenre.setText(data.getGenre());
        etYear.setText(String.valueOf(data.getYear()));
        spinnerRating.setSelection(getIndex(spinnerRating, data.getRating()));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to update the movie " + data.getTitle());
                myBuilder.setCancelable(false);

                myBuilder.setNegativeButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper db = new DBHelper(ThirdActivity.this);
                        String selectedItem = spinnerRating.getSelectedItem().toString();
                        data.setTitle(etTitle.getText().toString());
                        data.setGenre(etGenre.getText().toString());
                        data.setYear(Integer.parseInt(etYear.getText().toString()));
                        data.setRating(selectedItem);
                        db.updateMovie(data);
                        db.close();
                        finish();
                    }
                });
                myBuilder.setPositiveButton("CANCEL", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the movie " + data.getTitle());
                myBuilder.setCancelable(false);

                myBuilder.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper db = new DBHelper(ThirdActivity.this);
                        db.deleteMovie(data.getId());
                        finish();
                    }
                });
                myBuilder.setPositiveButton("CANCEL", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes");
                myBuilder.setCancelable(false);

                myBuilder.setNegativeButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                myBuilder.setPositiveButton("DO NOT DISCARD", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
        private int getIndex(Spinner spinner, String myString){
            for (int i=0;i<spinner.getCount();i++){
                if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                    return i;
                }
            }
            return 0;
        }

    }
}