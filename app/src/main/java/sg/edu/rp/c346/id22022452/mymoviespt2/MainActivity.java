package sg.edu.rp.c346.id22022452.mymoviespt2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText title;
    EditText genre;
    EditText year;
    Spinner rating;
    Button insert;
    Button showlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.editTextTitle);
        genre = findViewById(R.id.editTextGenre);
        year = findViewById(R.id.editTextYear);
        rating = findViewById(R.id.spinnerRating);
        insert = findViewById(R.id.buttonUpdate);
        showlist = findViewById(R.id.buttonDelete);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                String titleInp = title.getText().toString();
                String singerInp = genre.getText().toString();
                int yearInp = Integer.parseInt(year.getText().toString());
                String ratingInp = String.valueOf(rating);

                rating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String ratingInp = parent.getItemAtPosition(position).toString();
                        Toast.makeText(MainActivity.this, "Selected: " + ratingInp, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Do nothing
                    }
                });

                db.insertMovie(titleInp, singerInp, yearInp, ratingInp);
                title.setText("");
                genre.setText("");
                year.setText("");
            }
        });

        showlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}