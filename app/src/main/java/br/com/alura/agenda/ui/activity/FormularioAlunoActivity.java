package br.com.alura.agenda.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String NOVO_ALUNO = "Novo aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private Button botaoSalvar;
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        setTitle(NOVO_ALUNO);

        inicializaCampos();

        configuraBotaoSalvar();
    }

    private void configuraBotaoSalvar() {
        botaoSalvar.setOnClickListener((view)->{
            Aluno alunoCriado = criaAluno();
            mostraMensagem();
            salvaAluno(alunoCriado);
        });
    }

    private void inicializaCampos() {
        campoNome = findViewById(R.id.activity_formulario_alunos_nome);
        campoTelefone = findViewById(R.id.activity_formulario_alunos_telefone);
        campoEmail = findViewById(R.id.activity_formulario_alunos_email);
        botaoSalvar = findViewById(R.id.activity_formulario_alunos_botao_salvar);
    }

    private void mostraMensagem() {
        Toast.makeText(FormularioAlunoActivity.this,
                "Aluno criado com sucesso!",
                Toast.LENGTH_SHORT).show();
    }

    private void salvaAluno(Aluno aluno) {
        dao.salva(aluno);
        finish();
    }

    private Aluno criaAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();
        Aluno alunoCriado = new Aluno(nome, telefone, email);
        return alunoCriado;
    }
}