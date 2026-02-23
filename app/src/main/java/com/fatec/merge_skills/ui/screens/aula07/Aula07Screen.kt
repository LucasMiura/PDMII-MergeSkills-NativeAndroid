package com.fatec.merge_skills.ui.screens.aula07

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Aula 07 - Consumindo Rede e Controlando Estados de Processamento Assíncrono
 *
 * Esta tela assina (collectAsState) a StateFlow que está emitindo Loading/Sucesso/Erro 
 * a partir das Coroutines disparadas no nosso Ktor Client.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Aula07Screen(
    viewModel: Aula07ViewModel // Idealmente injetado via Koin posteriormente
) {
    // 1. Ouvindo as emissões de estado da Coroutine
    val state by viewModel.uiState.collectAsState()

    // 2. Disparando o Fetch de dados (Network) apenas na 1ª renderização
    LaunchedEffect(Unit) {
        viewModel.fetchPosts()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Aula 07 - Coroutines e Ktor") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                // Estado: CARREGANDO (Network Call in-flight)
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                
                // Estado: ERRO
                state.errorMessage != null -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center).padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Aviso!", 
                            color = MaterialTheme.colorScheme.error,
                            fontWeight = FontWeight.Bold 
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Verifique se o seu AndroidManifest possui permissão de Internet.\n\nDetalhe Técnico: ${state.errorMessage}")
                    }
                }
                
                // Estado: SUCESSO com Dados Vázios
                state.posts.isEmpty() -> {
                    Text(
                        text = "A API retornou uma lista vazia.",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                // Estado: SUCESSO (Renderização de Lista Infinita)
                else -> {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(state.posts) { post ->
                            Card(
                                elevation = CardDefaults.cardElevation(4.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        text = "${post.id} - ${post.title.uppercase()}", 
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = post.body.replace("\n", " "),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
