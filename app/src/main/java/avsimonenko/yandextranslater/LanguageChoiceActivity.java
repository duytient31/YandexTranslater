package avsimonenko.yandextranslater;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by avsimonenko on 05.04.17.
 */

public class LanguageChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_choice);
        Intent intent = getIntent();

        String message = intent.getStringExtra(TranslateFragment.LANG_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        ListView languagesListView = (ListView) findViewById(R.id.languages_list_view);

        String[] toyNames = {
                "Red Toy Wagon",
                "Chemistry Set",
                "Yo-Yo",
                "Pop-Up Book",
                "Generic Uncopyrighted Mouse",
                "Finger Paint",
                "Sock Monkey",
                "Microscope Set",
                "Beach Ball",
                "BB Gun",
                "Green Soldiers",
                "Bubbles",
                "Spring Toy",
                "Fortune Telling Ball",
                "Plastic Connecting Blocks",
                "Water Balloon",
                "Paint-by-Numbers Kit",
                "Tuber Head",
                "Cool Ball with Holes in It",
                "Toy Truck",
                "Flying Disc",
                "Two-Handed Pogo Stick",
                "Toy Hoop",
                "Dysmorphia Doll",
                "Toy Train",
                "Fake Vomit",
                "Toy Telephone",
                "Barrel of Primates",
                "Remote Control Car",
                "Square Puzzle Cube",
                "Football",
                "Intergalactic Electronic Phasers",
                "Baby Horse Dolls",
                "Machines that turn into other Machines",
                "Teddy Bears",
                "Shaved Ice Maker",
                "Glow Stick",
                "Squirt Guns",
                "Miniature Replica Animals Stuffed with Beads that you swore to your parents would be worth lots of money one day",
                "Creepy Gremlin Doll",
                "Neodymium-Magnet Toy"
        };

    }
}
