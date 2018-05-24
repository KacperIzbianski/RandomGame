package pl.swiebodzin.pzs.game2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    int globalCounter = 2;
    int number;
   int click = 0;
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
                     number = getRandomNumber();
                   playerNum = getNumberFromEditText();
                   // if(editNumber.getText().toString().length() != 0){
                    //    viewNumber.setText(String.valueOf(number));
                     //   couter--;
                     //   clickButton.setText(String.valueOf(couter));

                     //   if(couter == 0){
                     //       editNumber.setText("");
                     //       shiftPlayer(currentPlayer);
                    //    }
                    //}

                    if(playerNum !=0) {
                        gameLogic();
                    }



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
                click++;
                break;

            case 2 :
                currentPlayer = 1;
                One.setVisibility(View.VISIBLE);
                Two.setVisibility(View.INVISIBLE);
                couter = 5;
                click++;
                break;
        }


        return currentPlayer;
    }
 public void gameLogic(){
        try{

          viewNumber.setText(String.valueOf(number));
          clickButton.setText(String.valueOf(globalCounter));
          couter--;

            if(click == 2){
                globalRound();
            }
          if(couter == 0){
              checkCaunter();
              editNumber.setText("");
              shiftPlayer(currentPlayer);
           }





            int number = Integer.parseInt(viewNumber.getText().toString()); //sprawdzenie czy zostala wpisana liczba w pole a jak nie to wyskakuje komunikat
            playerNum = Integer.parseInt(editNumber.getText().toString());

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
public int getNumberFromEditText(){
        try{
            return  Integer.parseInt(editNumber.getText().toString());

        }catch(NumberFormatException e){
            Log.d("error","number not found");

    }return 0;
}
public void globalRound(){
        if(globalCounter>0){
            globalCounter--;
            click = 0;
        }

}
public void getGameOverActivity(){ //przenosi nas do innej klasy java
        Intent intent = new Intent(this, GameOverActivity.class);
        startActivity(intent);
}

public void checkCaunter(){
    if(globalCounter == 0){
        getGameOverActivity();
    }
}

}
