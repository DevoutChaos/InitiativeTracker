package devoutchaos.initiativetracker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Interface Declarations
    Button but1, but2, but3, but4, but5;
    ListView lstVw1;
    ListViewAdapter lstVwAda;
    EditText name, init, pasPer;
    TextView txtName, txtInit, txtPasPer;
    String[] nameArr;
    Integer[] initArr, pasPerArr;
    private ViewFlipper switcher;
    int count;

    public static List<String> nameLst = new ArrayList<>();
    public static List<Integer> initLst = new ArrayList<>();
    public static List<Integer> pasPerLst = new ArrayList<>();

    private String tempName;
    private Integer tempInit, tempPasPer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameLst.clear();
        initLst.clear();
        pasPerLst.clear();

        switcher = (ViewFlipper) findViewById(R.id.ViewFlipper);
        but1 = (Button) findViewById(R.id.butAdd);
        but2 = (Button) findViewById(R.id.butSort);
        but3 = (Button) findViewById(R.id.butUp);
        but4 = (Button) findViewById(R.id.butDown);
        but5 = (Button) findViewById(R.id.butConfirm);
        txtName = (TextView) findViewById(R.id.txtName);
        txtPasPer = (TextView) findViewById(R.id.txtPasPer);
        txtInit = (TextView) findViewById(R.id.txtInitiative);
        name = (EditText) findViewById(R.id.editName);
        init = (EditText) findViewById(R.id.editInitiative);
        pasPer = (EditText) findViewById(R.id.editPasPer);
        lstVw1 = (ListView) findViewById(R.id.mainListView);

        but1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ClearInputs();
                switcher.showNext();
            }
        });

        but5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                PrepForArrays();
                switcher.showPrevious();
                AddToArrays();
            }
        });
    }

    public void ClearInputs()
    {
        name.setText("");
        init.setText("");
        pasPer.setText("");
    }

    public void PrepForArrays()
    {
        tempName = name.getText().toString();
        tempInit = Integer.parseInt(init.getText().toString());
        tempPasPer = Integer.parseInt(pasPer.getText().toString());
        nameLst.add(tempName);
        initLst.add(tempInit);
        pasPerLst.add(tempPasPer);
        nameArr = nameLst.toArray(new String[0]);
        initArr = initLst.toArray(new Integer[0]);
        pasPerArr = pasPerLst.toArray(new Integer[0]);
        count = nameArr.length;

    }

    public void AddToArrays()
    {
        lstVwAda = new ListViewAdapter(this, nameArr, pasPerArr, initArr);
        lstVw1.setAdapter(lstVwAda);
        Toast.makeText(this, "Combatants present: " + count, Toast.LENGTH_SHORT).show();
    }

}
