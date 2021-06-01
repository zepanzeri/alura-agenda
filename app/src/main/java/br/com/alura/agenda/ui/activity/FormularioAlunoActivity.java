package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private Button botaoSalvar;
    private final AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);
        inicializaCampos();
        configuraBotaoSalvar();
        Intent dados = getIntent();

        if (dados.hasExtra("aluno")) {
            aluno = (Aluno) dados.getSerializableExtra("aluno");
            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
        } else {
            aluno = new Aluno();
        }

    }

    private void configuraBotaoSalvar() {
        botaoSalvar.setOnClickListener((view) -> {
//            Aluno alunoCriado = preencheAluno();
//            salvaAluno(alunoCriado);
            preencheAluno();
            if (aluno.temIdValid()) {
                dao.edita(aluno);
                mostraMensagem("Aluno editado com sucesso");
            } else {
                dao.salva(aluno);
                mostraMensagem("Aluno cadastrado com sucesso");
            }
            finish();
        });
    }

    private void inicializaCampos() {
        campoNome = findViewById(R.id.activity_formulario_alunos_nome);
        campoTelefone = findViewById(R.id.activity_formulario_alunos_telefone);
        campoEmail = findViewById(R.id.activity_formulario_alunos_email);
        botaoSalvar = findViewById(R.id.activity_formulario_alunos_botao_salvar);
    }

    private void mostraMensagem(String mensagem) {
        Toast.makeText(FormularioAlunoActivity.this,
                mensagem,
                Toast.LENGTH_LONG).show();
    }


    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();
        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }
}