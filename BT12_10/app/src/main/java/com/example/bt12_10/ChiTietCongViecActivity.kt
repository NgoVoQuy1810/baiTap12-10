package com.example.bt12_10

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ChiTietCongViecActivity : AppCompatActivity() {

    private lateinit var tvHienThiTen: TextView
    private lateinit var tvHienThiMoTa: TextView
    private lateinit var btnQuayLai: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_cong_viec)

        tvHienThiTen = findViewById(R.id.tvHienThiTen)
        tvHienThiMoTa = findViewById(R.id.tvHienThiMoTa)
        btnQuayLai = findViewById(R.id.btnQuayLai)

        // Nhận dữ liệu truyền từ MainActivity
        val ten = intent.getStringExtra("TEN_CV")
        val moTa = intent.getStringExtra("MO_TA_CV")

        tvHienThiTen.text = ten
        tvHienThiMoTa.text = if (moTa.isNullOrEmpty()) "Không có mô tả" else moTa

        btnQuayLai.setOnClickListener {
            finish()
        }
    }
}