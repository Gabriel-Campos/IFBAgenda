package br.com.ifbasamaro.cadastro.dao;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.ifbasamaro.cadastro.modelo.Aluno;

public class AlunoDAO extends SQLiteOpenHelper {

	private static final String DATABASE = "CadastroIF";
	private static final int VERSAO = 2;

	public AlunoDAO(Context context) {
		super(context, DATABASE, null, VERSAO);
	}

	public void salva(Aluno aluno) {
		ContentValues values = new ContentValues();
		
		values.put("nome", aluno.getNome());
		values.put("endereco", aluno.getEndereco());
		values.put("telefone", aluno.getTelefone());

		
		getWritableDatabase().insert("Alunos", null, values);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + "Alunos" + " ");
		sb.append("(id INTEGER PRIMARY KEY, ");
		sb.append(" nome TEXT UNIQUE NOT NULL, ");
		sb.append(" telefone TEXT, ");
		sb.append(" endereco TEXT, ");
		sb.append(" foto TEXT);");
		db.execSQL(sb.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String ddl = "DROP TABLE IF EXISTS Alunos";
		db.execSQL(ddl);
		
		this.onCreate(db);
		
	}

	public List<Aluno> getLista() {
		String[] colunas = {"id", "nome", "telefone", "endereco", "foto"};
		
		Cursor cursor = getWritableDatabase().query("Alunos", colunas, null, null,null ,null, null);
		
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		
		while (cursor.moveToNext() ){
			
			
			Aluno aluno = new Aluno();
		
			aluno.setId(cursor.getLong(0));
			aluno.setNome(cursor.getString(1));
			aluno.setTelefone(cursor.getString(2));
			aluno.setEndereco(cursor.getString(3));
			aluno.setFoto(cursor.getString(4));
			
			alunos.add(aluno);
		}
				
		return alunos;
	}

	public void deletar(Aluno aluno) {
		String[] args = {aluno.getId().toString()}; 
		getWritableDatabase().delete("Alunos", "id=?", args);
			
	}


	
	

}
