
package contagem.truco.jhonatan.davi.app.contagemapp.contagem.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import contagem.truco.jhonatan.davi.app.contagemapp.R;
import contagem.truco.jhonatan.davi.app.contagemapp.contagem.contagem.app.view.ContagemActivity;
import contagem.truco.jhonatan.davi.app.contagemapp.contagem.login.presenter.LoginPresenter;
import contagem.truco.jhonatan.davi.app.contagemapp.contagem.login.presenter.LoginPresenterImpl;
import contagem.truco.jhonatan.davi.app.contagemapp.contagem.view.InitMVP;

public class LoginActivity extends AppCompatActivity implements InitMVP, View.OnClickListener {

    private LoginPresenter presenter;

    private CardView _start_game;
    private AppCompatEditText _nome_time_one;
    private AppCompatEditText _nome_time_two;

    private AppCompatTextView _text_logo;
    private AppCompatTextView _text_start_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        load();
    }

    @Override
    public void onResume() {
        super.onResume();

        _nome_time_one.setText("");
        _nome_time_two.setText("");
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void init() {

        _start_game = findViewById(R.id.item_view_login_button_comecar);
        _start_game.setOnClickListener(this);

        _text_start_game = findViewById(R.id.item_view_card_view_button_comecar);

        _nome_time_one = findViewById(R.id.item_layout_login_nome_time_one);
        _nome_time_two = findViewById(R.id.item_layout_login_nome_time_two);

        _text_logo = findViewById(R.id.item_text_logo);
            
        Typeface typefaceTestOne = Typeface.createFromAsset(getAssets(), "fonts/njnaruto.ttf");
        _text_start_game.setTypeface(typefaceTestOne);
        _nome_time_one.setTypeface(typefaceTestOne);
        _nome_time_two.setTypeface(typefaceTestOne);
        _text_logo.setTypeface(typefaceTestOne);

    }

    @Override
    public void load() {

        presenter = new LoginPresenterImpl(getContext(), getActivity());
    }

    @Override
    public void task() {

    }

    public Context getContext() {
        return this;
    }


    public Activity getActivity() {
        return this;
    }

    @SuppressWarnings({"unchecked", "all"})
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.item_view_login_button_comecar:

                String one = String.valueOf(_nome_time_one.getText().toString());
                String two = String.valueOf(_nome_time_two.getText().toString());

                if (!one.isEmpty() && !two.isEmpty()) {

                    Intent intent = new Intent(getContext(), ContagemActivity.class);
                    intent.putExtra("one", one);
                    intent.putExtra("two", two);
                    ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getContext(), R.anim.esquerda, R.anim.fade_out);
                    ActivityCompat.startActivity(this, intent, activityOptionsCompat.toBundle());

                } else {
                    presenter.toast("Ops! preencha os nomes de cada time!", Toast.LENGTH_LONG);

                }

                break;
        }

    }

}
