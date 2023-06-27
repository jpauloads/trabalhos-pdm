package com.example.gerenciaunidades

class Curso (val codigo: Int, val nome: String, val nroAlunos: Int?, val notaMec: Float) {

    override fun toString(): String {
        return "\nCurso: "+
                "\nCodigo = $codigo" +
                "\nNome = $nome" +
                "\nNumero de alunos = $nroAlunos" +
                "\nNota no Mec = $notaMec"
    }
}