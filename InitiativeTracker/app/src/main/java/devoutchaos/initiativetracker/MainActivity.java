package devoutchaos.initiativetracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.Arrays;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*** Declarations (UI) ***/
    Button but1, but2, but5;
    ListView lstVw1;
    ListViewAdapter lstVwAda;
    EditText name, init, pasPer, txtInit;
    TextView txtName, txtPasPer;
    private ViewFlipper switcher;

    /*** Declarations (Lists) ***/
    public ArrayList<String> nameLst = new ArrayList<>();
    public ArrayList<Integer> initLst = new ArrayList<>();
    public ArrayList<Integer> pasPerLst = new ArrayList<>();

    /*** Declarations (Variables) ***/
    String[] nameArr;
    Integer[] initArr, pasPerArr;
    int count;
    private String tempName;
    private Integer tempInit, tempPasPer;

    /*** When the application loads ***/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*** Resets the lists ***/
        nameLst.clear();
        initLst.clear();
        pasPerLst.clear();

        /*** Sets up all UI components ***/
        switcher = (ViewFlipper) findViewById(R.id.ViewFlipper);
        but1 = (Button) findViewById(R.id.butAdd);
        but2 = (Button) findViewById(R.id.butSort);
        but5 = (Button) findViewById(R.id.butConfirm);
        txtName = (TextView) findViewById(R.id.txtName);
        txtPasPer = (TextView) findViewById(R.id.txtPasPer);
        txtInit = (EditText) findViewById(R.id.txtInitiative);
        name = (EditText) findViewById(R.id.editName);
        init = (EditText) findViewById(R.id.editInitiative);
        pasPer = (EditText) findViewById(R.id.editPasPer);
        lstVw1 = (ListView) findViewById(R.id.mainListView);

        /*** Switch to Add Combatant screen ***/
        but1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ClearInputs();
                if (count > 0) {
                    ArraysToList();
                }
                switcher.showNext();
            }
        });

        /*** Return to Initiative Order - adding the combatant in the process ***/
        but5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (name == null)
                {
                    MissingName();
                }
                else if (init == null)
                {
                    MissingInit();
                }
                else if (pasPer == null)
                {
                    MissingPasPer();
                }
                else {
                    PrepForArrays();
                    switcher.showPrevious();
                    AddToArrays();
                }
            }
        });
    }

    /*** Resets input fields ***/
    public void ClearInputs()
    {
        name.setText("");
        init.setText("");
        pasPer.setText("");
    }

    /*** Gets new data into Lists for adding to arrays ***/
    public void PrepForArrays()
    {
        /*** Gets inputs and places them into temporary holders ***/
        tempName = name.getText().toString();
        tempInit = Integer.parseInt(init.getText().toString());
        tempPasPer = Integer.parseInt(pasPer.getText().toString());

        /*** Adds the temporary holders into the relevant lists ***/
        nameLst.add(tempName);
        initLst.add(tempInit);
        pasPerLst.add(tempPasPer);

        /*** Overwrites arrays with List data ***/
        nameArr = nameLst.toArray(new String[0]);
        initArr = initLst.toArray(new Integer[0]);
        pasPerArr = pasPerLst.toArray(new Integer[0]);

        /*** Gets number of entries ***/
        count = nameArr.length;

    }

    /*** Passes arrays into ListViewAdapter ***/
    public void AddToArrays()
    {
        lstVwAda = new ListViewAdapter(this, nameArr, pasPerArr, initArr, nameLst, initLst, pasPerLst);
        lstVw1.setAdapter(lstVwAda);

        /*** Displays number of entries ***/
        Toast.makeText(this, "Combatants present: " + count, Toast.LENGTH_SHORT).show();
    }

    /*** Overwrites lists with Array data ***/
    public void ArraysToList()
    {
        nameLst = new ArrayList(Arrays.asList(nameArr));
        initLst = new ArrayList(Arrays.asList(initArr));
        pasPerLst= new ArrayList(Arrays.asList(pasPerArr));
    }

    /*** Displays a toast when called ***/
    public void MissingName()
    {
        Toast.makeText(this, "Please fill in the 'Name' section", Toast.LENGTH_SHORT).show();
    }

    /*** Displays a toast when called ***/
    public void MissingInit()
    {
        Toast.makeText(this, "Please fill in the 'Initiative' section", Toast.LENGTH_SHORT).show();
    }

    /*** Displays a toast when called ***/
    public void MissingPasPer()
    {
        Toast.makeText(this, "Please fill in the 'Passive Perception' section", Toast.LENGTH_SHORT).show();
    }
}