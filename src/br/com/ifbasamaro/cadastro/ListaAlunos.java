 package br.com.ifbasamaro.cadastro;

import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.ifbasamaro.cadastro.dao.AlunoDAO;
import br.com.ifbasamaro.cadastro.modelo.Aluno;

public class ListaAlunos extends Activity {

	private ListView lista;
	private Aluno aluno;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagemalunos);
		
		lista = (ListView) findViewById(R.id.lista);
		
		registerForContextMenu(lista);
		
		lista.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao,
					long id) {
			

				Aluno alunoClicado = (Aluno) adapter.getItemAtPosition(posicao);
				
				Intent irParaFormulario = new Intent(ListaAlunos.this, Formulario.class);
				irParaFormulario.putExtra("alunoSelecionado", alunoClicado);
				
				
				startActivity(irParaFormulario);
			}
		});
		lista.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				
				aluno = (Aluno) adapter.getItemAtPosition(posicao);
				
				return false;
			}
		}); 
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		menu.add("Ligar");
		menu.add("Enviar SMS");
		MenuItem deletar = menu.add("Deletar");
		deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlunoDAO dao = new AlunoDAO(ListaAlunos.this);
				
				dao.deletar(aluno);
				dao.close();
				
				carregaLista();
					
				return false;
			}
		});
		
		menu.add("Ver no mapa");
		menu.add("Enviar e-mail");
		
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		
		carregaLista();
	}

	private void carregaLista() {
		AlunoDAO dao = new AlunoDAO(this);
		List<Aluno> alunos = dao.getLista();
		dao.close();
		
		int layout = android.R.layout.simple_list_item_1;
		
		ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, layout, alunos);
		
		
		lista.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.lista_alunos, menu);
		
		return super.onCreateOptionsMenu(menu);
		
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemClicado = item.getItemId();
		
		switch (itemClicado) {
		case R.id.novo:
			Intent irParaFormulario = new Intent(this, Formulario.class);
			
			startActivity(irParaFormulario);
			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
