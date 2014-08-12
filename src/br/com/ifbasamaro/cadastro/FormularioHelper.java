package br.com.ifbasamaro.cadastro;

import br.com.ifbasamaro.cadastro.modelo.Aluno;
import android.widget.EditText;

public class FormularioHelper {
	
	
		private EditText editNome;
		private EditText editTelefone;
		private EditText editEndereco;
		
		
	public FormularioHelper(Formulario formulario) {

		
		editNome = (EditText) formulario.findViewById(R.id.nome);
		editTelefone = (EditText) formulario.findViewById(R.id.telefone);
		editEndereco = (EditText) formulario.findViewById(R.id.endereco);
	}


	public Aluno pegaAlunoDoFormulario() {
		Aluno aluno = new Aluno();
		
		aluno.setNome(editNome.getText().toString());
		aluno.setTelefone(editTelefone.getText().toString());
		aluno.setEndereco(editEndereco.getText().toString());
		
		return aluno;
	}


	public void colocaAlunoNoFormulario(Aluno alunoParaSerAlterado) {
		editNome.setText(alunoParaSerAlterado.getNome());
		
	}

}
