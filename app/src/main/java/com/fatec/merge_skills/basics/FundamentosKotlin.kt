package com.fatec.merge_skills.basics

/**
 * ==========================================================
 * AULA 01 - FUNDAMENTOS DE KOTLIN (NIVELAMENTO PARA INICIANTES)
 * ==========================================================
 * 
 * Antes de mergulharmos no Jetpack Compose e na confecção de Telas, 
 * precisamos entender a linguagem nativa oficial do Android: O Kotlin.
 * 
 * Este arquivo não é executado diretamente no aplicativo, mas serve como 
 * material didático de consulta para você entender as regras do jogo.
 */


// ---------------------------------------------------------
// 1. VARIÁVEIS, MUTABILIDADE E TIPAGEM
// ---------------------------------------------------------
fun entendendoVariaveis() {
    // 'val' (Value): É uma variável IMUTÁVEL. Seu valor não pode ser alterado depois de assinalado.
    // É recomendável usar 'val' na maior parte do tempo por segurança.
    val nomeDoCurso = "Merge Skills: Android Nativo"
    // nomeDoCurso = "Outro Curso" // ERRO DE COMPILAÇÃO!

    // 'var' (Variable): É uma variável MUTÁVEL. O seu valor PODE mudar livremente (desde que seja do mesmo tipo).
    var versaoDoApp = 1
    versaoDoApp = 2 // OK!

    // Tipagem Forte e Inferência
    // O Kotlin adivinha o tipo sozinho, mas você pode declará-lo explicitamente se quiser:
    val cargaHoraria: Int = 40
    val valorDaMensalidade: Double = 99.90
    val possuiCertificado: Boolean = true
    val inicialDoNome: Char = 'M'
}

// ---------------------------------------------------------
// 2. NULABILIDADE (NULL SAFETY)
// ---------------------------------------------------------
// No Kotlin, quem avisa é amigo. Variáveis normais NÃO PODEM receber "null".
fun lidandoComNulos() {
    // Isso gera erro na hora (diferente do Java onde dá Crash no cliente = NullPointerException):
    // var nomeProfessor: String = null 

    // Se uma variável *puder* ser nula (Ex: usuário não preencheu o campo),
    // usamos o sinal de interrogação `?` após o Tipo:
    var githubDoProfessor: String? = null
    
    // Agora para usar, o Kotlin te OBRIGA a tratar:
    // Opção A: Safe Call `?.` (Só executa se não for nulo)
    val letras = githubDoProfessor?.length 

    // Opção B: Operador Elvis `?:` (Se for nulo, use um valor padrão)
    val linkSeguro = githubDoProfessor ?: "Não Cadastrado"
}

// ---------------------------------------------------------
// 3. CONTROLE DE FLUXO E MÉTODOS NATIVOS (IF e WHEN)
// ---------------------------------------------------------
fun checarAprovacao(notaMedia: Double) {
    // O Bom e velho IF existe aqui, mas a grande sacada é que IFs
    // no Kotlin retornam valor (podem agir como variáveis)!
    val status = if (notaMedia >= 7.0) {
        "Aprovado"
    } else {
        "Reprovado"
    }

    // "When" é o Switch Case "bombado" do Kotlin
    when (notaMedia) {
        10.0 -> println("Aluno Perfeito!")
        in 7.0..9.9 -> println("Aprovado com folga")
        else -> println("Precisamos estudar mais")
    }
}

// ---------------------------------------------------------
// 4. ESTRUTURAS DE REPETIÇÃO (LOOPS e FOREACH)
// ---------------------------------------------------------
fun testandoLoops() {
    val tecnologias = listOf("Kotlin", "Jetpack Compose", "Coroutines", "Koin")

    // Loop "For" Tradicional
    for (tech in tecnologias) {
        println("Aprenderemos: $tech")
    }

    // Uma forma mais limpa usando o forEach (Programação Funcional)
    tecnologias.forEach { tech ->
        println("Listando: $tech")
    }

    // Loop While comum
    var i = 0
    while(i < 3) {
        println("Contando $i")
        i++
    }
}

// ---------------------------------------------------------
// 5. FUNÇÕES E SINTAXE ENXUTA
// ---------------------------------------------------------
// Funcão tradicional
fun somarIdades(idadeA: Int, idadeB: Int): Int {
    return idadeA + idadeB
}

// No Kotlin, podemos reduzir funções de "uma linha" removendo chaves:
fun somarModerno(a: Int, b: Int) = a + b

// ---------------------------------------------------------
// 6. INTRODUÇÃO À ORIENTAÇÃO A OBJETOS
// ---------------------------------------------------------

// Uma Classe básica agrupando dados.
// Repare que o construtor primário (variáveis nela) já é declarado diretamente nos parênteses!
class AlunoNormal(val nome: String, val idade: Int) {
    fun apresentar() {
        println("Olá, me chamo $nome e tenho $idade anos.")
    }
}

// ---------------------------------------------------------
// 7. HERANÇA, CONSTRUTORES E POLIMORFISMO
// ---------------------------------------------------------
// No Kotlin, TUDO é fechado por padrão (não pode ser herdado).
// Para permitir Herança, a classe PAI precisa receber o prefixo "open".
open class UsuarioPlataforma(val id: Int, val nickname: String) {
    
    // Funções também precisam ser "open" se quiserem ser sobrescritas no FILHO
    open fun autenticar() {
        println("Autenticando via email e senha padrão...")
    }
}

// O Filho (AlunoPremium) herda do Pai (UsuarioPlataforma). 
// Passamos (id, nickname) pro construtor do Pai imediatamente.
class AlunoPremium(
    id: Int, 
    nickname: String, 
    val beneficiosVip: Boolean
) : UsuarioPlataforma(id, nickname) {

    // Sobrescrevendo a ação padrão do Pai
    override fun autenticar() {
        super.autenticar() // Chama a lógica antiga primeiro
        println("Acesso VIP Liberado instantaneamente!")
    }
}

// ---------------------------------------------------------
// 8. INTERFACES (Contratos de Ações)
// ---------------------------------------------------------
// Interfaces definem REGRA! Uma interface força quem herda ela 
// a implementar os métodos descritos obrigatoriamente.
interface NavegadorDeCursos {
    fun abrirAula(id: Int)
    fun finalizarCurso()
}

// Uma classe implementando (herdando) a Interface
class AppCliente : NavegadorDeCursos {
    override fun abrirAula(id: Int) {
        println("Buscando vídeo da aula $id no Render/Backend...")
    }

    override fun finalizarCurso() {
        println("Parabéns, emitindo certificado em PDF!")
    }
}
