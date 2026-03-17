package com.fatec.merge_skills.ui.screens.aula02

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.parcelize.Parcelize

// Exemplo da Aula 02 - Data Classes, Lambdas, SafeCalls e Componentes visuais
@Parcelize
data class UserProfile(
    val name: String,
    val role: String? // Nullable para demonstrar SafeCall ?.
) : Parcelable

@Composable
fun Aula02Screen(modifier: Modifier = Modifier) {
    // 1. ESTADO INTERNO (Stateful Composable)
    // Usamos 'rememberSaveable' em vez de 'remember' para que o estado
    // sobreviva a mudanças de configuração (como rotacionar a tela).
    var activeProfile by rememberSaveable {
        mutableStateOf(UserProfile("Aluno MergeSkills", "Developer"))
    }


    // Chamada do conteúdo passando o estado e o evento de mudança (State Hoisting)
    UserProfileContent(
        profile = activeProfile,
        onProfileChange = { newProfile -> activeProfile = newProfile },
        modifier = modifier
    )
}

@Composable
fun UserProfileContent(
    profile: UserProfile,
    onProfileChange: (UserProfile) -> Unit,
    modifier: Modifier = Modifier
) {
    // 2. STATELESS COMPOSABLE
    // Este componente não "lembra" de nada, apenas reage aos parâmetros.
    // Isso facilita testes e reuso em diferentes contextos.

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Prática Aula 02",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Demonstração do Row e Box
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Box circular para um "Avatar" improvisado
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = profile.name.take(1).uppercase(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = profile.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                // Usando SafeCall e Elvis para renderizar o subtítulo
                Text(
                    text = profile.role?.uppercase() ?: "SEM CARGO",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botões que disparam eventos para o componente "Pai" (Stateful)
        Button(
            onClick = {
                onProfileChange(UserProfile("Visitante", null))
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            Text("Simular Nullable (SafeCall)")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onProfileChange(UserProfile("Aluno Vip", "Senior Android Dev"))
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Restaurar Dev")
        }
    }
}

