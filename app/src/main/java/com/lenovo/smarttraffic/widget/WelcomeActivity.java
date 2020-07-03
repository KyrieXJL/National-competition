package com.lenovo.smarttraffic.widget;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.lenovo.smarttraffic.MainActivity;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.map_code.MapActivity;

public class WelcomeActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button beijingButton= (Button) findViewById(R.id.bj_button);
        Button shanghaiButton= (Button) findViewById(R.id.sh_button);
        Button guangzhouButton= (Button) findViewById(R.id.gz_button);
        beijingButton.setOnClickListener(this);
        shanghaiButton.setOnClickListener(this);
        guangzhouButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.bj_button:
                intent=new Intent(WelcomeActivity.this,MapActivity.class);
                intent.putExtra(MapActivity.CITI_KEY,MapActivity.BEIJING);
                startActivity(intent);
                break;
            case  R.id.gz_button:
                intent=new Intent(WelcomeActivity.this,MapActivity.class);
                intent.putExtra(MapActivity.CITI_KEY,MapActivity.GUANGZHOU);
                startActivity(intent);
                break;
            case R.id.sh_button:
                intent=new Intent(WelcomeActivity.this,MapActivity.class);
                intent.putExtra(MapActivity.CITI_KEY,MapActivity.SHANGHAI);
                startActivity(intent);
                break;

        }
    }
}