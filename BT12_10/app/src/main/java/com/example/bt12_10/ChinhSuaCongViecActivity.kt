package com.example.bt12_10

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ChinhSuaCongViecActivity : AppCompatActivity() {

    private lateinit var edtSuaTenCV: EditText
    private lateinit var edtSuaMoTaCV: EditText
    private lateinit var btnLuuCV: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chinh_sua_cong_viec)

        anhXaView()
        nhanDuLieuCu()
        xuLySuKien()
    }

    private fun anhXaView() {
        edtSuaTenCV = findViewById(R.id.edtSuaTenCV)
        edtSuaMoTaCV = findViewById(R.id.edtSuaMoTaCV)
        btnLuuCV = findViewById(R.id.btnLuuCV)
    }

    private fun nhanDuLieuCu() {
        val tenCu = intent.getStringExtra("TEN_CV_CU")
        val moTaCu = intent.getStringExtra("MO_TA_CV_CU")

        edtSuaTenCV.setText(tenCu)
        edtSuaMoTaCV.setText(moTaCu)
    }

    private fun xuLySuKien() {
        btnLuuCV.setOnClickListener {
            val tenMoi = edtSuaTenCV.text.toString().trim()
            val moTaMoi = edtSuaMoTaCV.text.toString().trim()

            if (tenMoi.isEmpty()) {
                edtSuaTenCV.error = "Tên không được để trống!"
                edtSuaTenCV.requestFocus()
                return@setOnClickListener
            }

            // Đóng gói dữ liệu trả về cho màn hình gọi
            val intentKetQua = Intent().apply {
                putExtra("TEN_CV_MOI", tenMoi)
                putExtra("MO_TA_CV_MOI", moTaMoi)
            }
            setResult(Activity.RESULT_OK, intentKetQua)
            finish() // Đóng Activity
        }
    }
}