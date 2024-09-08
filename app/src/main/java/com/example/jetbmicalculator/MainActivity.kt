package com.example.jetbmicalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetbmicalculator.ui.theme.JetBMICalculatorTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        setContent {
            JetBMICalculatorTheme {
                Scaffold() { padding ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)

                    ) {
                        Text(text = "BMI計算アプリ",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.padding(top = 70.dp))
                        Spacer(modifier = Modifier.height(30.dp))

                        // 身長
                        PinkLabeledTextField(
                            value = viewModel.height,
                            onValueChange = { viewModel.height = it }, // itはユーザが打ち込んだ値
                            label = "身長(cm)",
                            placeholder = "170",
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        // 体重
                        PinkLabeledTextField(
                            // valueの値を監視
                            value = viewModel.weight,
                            // valueの値が変わると、再描画
                            onValueChange = { viewModel.weight = it },
                            label = "体重(kg)",
                            placeholder = "65",
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        // 計算するボタン
                        Button(
                            onClick = { viewModel.calculateBMI() },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color(0xFFF85F6A))
                        ) {
                            Text(
                                text = "計算する",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                                )
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        // 結果表示テキスト
                        Text(
                            text = "あなたのBMIは ${viewModel.bmi}です",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = Color.Gray,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PinkLabeledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
) {
    Column {
        Text(text = label,
            color = Color(0xFFF85F6A),
            fontWeight = FontWeight.Bold
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange, // ユーザがテキストに打ち込んだ時の処理
            placeholder = { Text(text = placeholder) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // 数字のみ入力
            singleLine = true, // 1行のみ入力
        )
    }
}