package pl.swiebodzin.pzs.game2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button clickButton;
    TextView One;
    TextView Two;
    TextView viewNumber;
    TextView scoreOne;
    TextView scoreTwo;
    EditText editNumber;
    int  currentPlayer = 1;
    int couter = 5;

    int globalcouter1 = 0;
    int globalcouter2 = 0;
    int playerNum = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            One = findViewById(R.id.One);
            Two = findViewById(R.id.Two);
            clickButton = findViewById(R.id.clickButton);
            viewNumber = findViewById(R.id.viewNumber);
            scoreOne = findViewById(R.id.scoreOne);
            scoreTwo = findViewById(R.id.scoreTwo);
            editNumber = findViewById(R.id.editNumber);
             One.setVisibility(View.VISIBLE);
             Two.setVisibility(View.INVISIBLE);

             viewNumber.setText("");
            clickButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int number = getRandomNumber();
                    viewNumber.setText(String.valueOf(number));
                    couter--;
                    clickButton.setText(String.valueOf(couter));

                    if(couter == 0){
                        shiftPlayer(currentPlayer);
                    }


                    gameLogic();


                }
            });

    }

    public int getRandomNumber(){
        Random random = new Random();
        return random.nextInt(10);

    }

    public int shiftPlayer(int player){
        switch (player) {
            case 1:
                currentPlayer = 2;
                One.setVisibility(View.INVISIBLE);
                Two.setVisibility(View.VISIBLE);
                couter = 5;
                break;

            case 2 :
                currentPlayer = 1;
                One.setVisibility(View.VISIBLE);
                Two.setVisibility(View.INVISIBLE);
                couter = 5;
                break;
        }


        return currentPlayer;
    }
 public void gameLogic(){
        try{
            int number = Integer.parseInt(viewNumber.getText().toString()); //sprawdzenie czy zostala wpisana liczba w pole a jak nie to wyskakuje komunikat
            playerNum = Integer.parseInt(editNumber.getText().toString());

            // number = 0;




            if(number == playerNum && playerNum != 0) {

                switch (currentPlayer) {
                    case 1:
                        globalcouter1++;
                        scoreOne.setText(String.valueOf(globalcouter1));
                        break;

                    case 2:
                        globalcouter2++;
                        scoreTwo.setText(String.valueOf(globalcouter2));
                        break;
                }
            }
        }catch(NumberFormatException e){
            Toast.makeText(getBaseContext(), "Podaj Liczbe", Toast.LENGTH_SHORT).show();//comunikat wyswitlany jesli w try stanie sie cos złego wtedy zrobi sie to co jest okreslone w catch

     }


 }

}
