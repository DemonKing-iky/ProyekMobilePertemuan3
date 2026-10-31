package com.example.layoutmaterialform.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.layoutmaterialform.ui.theme.LayoutMaterialFormTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen() {
    // STATE: Untuk menampung input pengguna
    var nama by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var noTelp by remember { mutableStateOf("") } // Penambahan untuk Latihan Praktikum 1
    var isTA by remember { mutableStateOf(true) }
    var setuju by remember { mutableStateOf(false) }

    // STATE: Untuk validasi email
    val isEmailValid = email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isErrorEmail = email.isNotEmpty() && !isEmailValid

    // STATE: Untuk Dropdown Menu
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("D3 Manajemen Informatika", "S1 Sistem Informasi")
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Form Pendaftaran") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Aksi simpan */ }) {
                Icon(Icons.Default.Add, contentDescription = "Simpan")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Data Mahasiswa",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    // INPUT 1: Nama Lengkap
                    OutlinedTextField(
                        value = nama,
                        onValueChange = { nama = it },
                        label = { Text("Nama Lengkap") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // INPUT 2: Alamat Email
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Alamat Email") },
                        isError = isErrorEmail,
                        supportingText = {
                            if (isErrorEmail) {
                                Text("Format email tidak valid!", color = MaterialTheme.colorScheme.error)
                            }
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // INPUT TAMBAHAN (Latihan Praktikum 1): Nomor Telepon (Keyboard Angka)
                    OutlinedTextField(
                        value = noTelp,
                        onValueChange = { noTelp = it },
                        label = { Text("Nomor Telepon") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // INPUT 3: Dropdown Menu (Program Studi)
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = selectedOptionText,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Program Studi") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            modifier = Modifier
                                .menuAnchor(type = ExposedDropdownMenuAnchorType.PrimaryNotEditable, enabled = true)
                                .fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            options.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(selectionOption) },
                                    onClick = {
                                        selectedOptionText = selectionOption
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    // INPUT 4: Switch Tugas Akhir
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Tugas Akhir", modifier = Modifier.weight(1f))
                        Switch(
                            checked = isTA,
                            onCheckedChange = { isTA = it }
                        )
                    }

                    // INPUT 5: Checkbox Persetujuan
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = setuju,
                            onCheckedChange = { setuju = it }
                        )
                        Text(
                            text = "Saya menyetujui syarat & ketentuan",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    // TOMBOL SUBMIT
                    Button(
                        onClick = { /* Aksi submit */ },
                        enabled = setuju && nama.isNotBlank() && isEmailValid,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Kirim Pendaftaran")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Form Screen Light Mode")
@Composable
fun FormScreenPreview() {
    LayoutMaterialFormTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            FormScreen()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Form Screen Dark Mode")
@Composable
fun FormScreenDarkPreview() {
    LayoutMaterialFormTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            FormScreen()
        }
    }
}