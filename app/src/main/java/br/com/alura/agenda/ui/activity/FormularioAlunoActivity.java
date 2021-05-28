package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        AlunoDAO dao = new AlunoDAO();

        final Button botaoSalvar = findViewById(R.id.activity_formulario_alunos_botao_salvar);
        final EditText campoNome = findViewById(R.id.activity_formulario_alunos_nome);
        final EditText campoTelefone = findViewById(R.id.activity_formulario_alunos_telefone);
        final EditText campoEmail = findViewById(R.id.activity_formulario_alunos_email);

        botaoSalvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String nome = campoNome.getText().toString();
                String telefone = campoTelefone.getText().toString();
                String email = campoEmail.getText().toString();

                Aluno alunoCriado = new Aluno(nome, telefone, email);
                dao.salva(alunoCriado);
                startActivity(new Intent(FormularioAlunoActivity.this,
                        ListaAlunosActivity.class));
                Toast.makeText(FormularioAlunoActivity.this,
                        "Aluno criado com sucesso!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}