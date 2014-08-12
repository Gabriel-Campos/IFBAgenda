package br.com.ifbasamaro.cadastro;

import java.io.Serializable;
import br.com.ifbasamaro.cadastro.dao.AlunoDAO;
import br.com.ifbasamaro.cadastro.modelo.Aluno;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Formulario extends Activity{

	private FormularioHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		 Intent intent = getIntent();
		 Aluno alunoParaSerAlterado  = (Aluno) intent.getSerializableExtra("alunoSelecionado");
		
		 
		helper = new FormularioHelper(this);
		
		Button botao = (Button) findViewById(R.id.botao);
				
		if(alunoParaSerAlterado != null) {
			botao.setText("Alterar");
			helper.colocaAlunoNoFormulario(alunoParaSerAlterado);
		}
		
		
		botao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Aluno aluno = helper.pegaAlunoDoFormulario();
		
				AlunoDAO dao = new AlunoDAO(Formulario.this);
				dao.salva(aluno);
				dao.close();
				
				finish();
			}
		});
		

		
	}
	
}
