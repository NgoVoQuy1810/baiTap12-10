package com.example.bt12_10

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ChiTietCongViecActivity : AppCompatActivity() {

    private lateinit var tvHienThiTen: TextView
    private lateinit var tvHienThiMoTa: TextView
    private lateinit var btnChinhSuaCV: Button
    private lateinit var btnQuayLai: Button

    // Đăng ký Activity Result API để nhận dữ liệu trả về từ màn hình sửa
    private val trinhNhanKetQua = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { ketQua ->
        if (ketQua.resultCode == Activity.RESULT_OK) {
            val duLieuTraVe = ketQua.data
            val tenMoi = duLieuTraVe?.getStringExtra("TEN_CV_MOI")
            val moTaMoi = duLieuTraVe?.getStringExtra("MO_TA_CV_MOI")

            // Cập nhật lại giao diện
            tvHienThiTen.text = tenMoi
            tvHienThiMoTa.text = if (moTaMoi.isNullOrEmpty()) "Không có mô tả" else moTaMoi
            Toast.makeText(this, "Đã cập nhật công việc!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chi_tiet_cong_viec)

        anhXaView()
        nhanDuLieuBanDau()
        xuLySuKien()
    }

    private fun anhXaView() {
        tvHienThiTen = findViewById(R.id.tvHienThiTen)
        tvHienThiMoTa = findViewById(R.id.tvHienThiMoTa)
        btnChinhSuaCV = findViewById(R.id.btnChinhSuaCV)
        btnQuayLai = findViewById(R.id.btnQuayLai)
    }

    private fun nhanDuLieuBanDau() {
        val ten = intent.getStringExtra("TEN_CV")
        val moTa = intent.getStringExtra("MO_TA_CV")

        tvHienThiTen.text = ten
        tvHienThiMoTa.text = if (moTa.isNullOrEmpty()) "Không có mô tả" else moTa
    }

    private fun xuLySuKien() {
        // Nút Chỉnh sửa: mở màn hình chỉnh sửa qua trinhNhanKetQua
        btnChinhSuaCV.setOnClickListener {
            val yDinh = Intent(this, ChinhSuaCongViecActivity::class.java).apply {
                putExtra("TEN_CV_CU", tvHienThiTen.text.toString())
                putExtra("MO_TA_CV_CU", tvHienThiMoTa.text.toString())
            }
            trinhNhanKetQua.launch(yDinh)
        }

        // Nút Quay lại
        btnQuayLai.setOnClickListener {
            finish()
        }
    }
}