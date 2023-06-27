package com.example.gerenciaunidades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import java.lang.Float

class TelaPrincipal : AppCompatActivity() {
    private lateinit var it_codigo : EditText
    private lateinit var it_nome : EditText
    private lateinit var it_nroAlunos : EditText
    private lateinit var it_notaMec : EditText
    private lateinit var lv_cursos : ListView
    private lateinit var bt_inserir : Button
    private lateinit var bt_atualizar : Button
    private lateinit var bt_deletar : Button
    private lateinit var bt_mostrar : Button
    private lateinit var listaCursos: ArrayList<Curso>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_principal)

        it_codigo = findViewById(R.id.it_codigo)
        it_nome = findViewById(R.id.it_nome)
        it_notaMec = findViewById(R.id.it_nota_mec)
        it_nroAlunos = findViewById(R.id.it_nro_alunos)
        bt_inserir = findViewById(R.id.bt_inserir)
        bt_atualizar = findViewById(R.id.bt_atualizar)
        bt_deletar = findViewById(R.id.bt_remover)
        bt_mostrar = findViewById(R.id.bt_mostrar)
        lv_cursos = findViewById(R.id.lv_cursos)


        listaCursos = ArrayList()

        bt_inserir.setOnClickListener {
            val linhaTabela = Curso(
                Integer.parseInt(it_codigo.text.toString()),
                it_nome.text.toString(),
                Integer.parseInt(it_nroAlunos.text.toString()),
                Float.parseFloat(it_notaMec.text.toString())
            )
            listaCursos.add(linhaTabela)
            limparTela()
        }

        bt_mostrar.setOnClickListener {
            var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaCursos)
            lv_cursos.adapter = adapter
        }

        bt_deletar.setOnClickListener {
            val index : Int
            index = filtraIndice(listaCursos, Integer.parseInt(it_codigo.text.toString()), ::filtro)
            listaCursos.removeAt(index)
            limparTela()
        }

        bt_atualizar.setOnClickListener {
            val cursoUpdate = Curso(
                Integer.parseInt(it_codigo.text.toString()),
                it_nome.text.toString(),
                Integer.parseInt(it_nroAlunos.text.toString()),
                Float.parseFloat(it_notaMec.text.toString())
            )
            val index = filtraIndice(listaCursos, Integer.parseInt(it_codigo.text.toString()), ::filtro)
            listaCursos.set(index, cursoUpdate)
            limparTela()
        }
    }
    private fun filtro(curso: Curso, codigo: Int): Boolean {
        if(curso.codigo == codigo){
            return true
        }
        return false
    }

    private fun filtraIndice (lista : ArrayList<Curso>, codigo : Int, filtro: (Curso, Int) -> Boolean): Int {
        var indice: Int = 0
        for(elemento in lista)
        {
            if(filtro(elemento, codigo))
            {
                indice = lista.indexOf(elemento)
            }
        }
        return indice
    }



    private fun limparTela()
    {
        it_codigo.text.clear()
        it_nome.text.clear()
        it_nroAlunos.text.clear()
        it_notaMec.text.clear()
    }
}