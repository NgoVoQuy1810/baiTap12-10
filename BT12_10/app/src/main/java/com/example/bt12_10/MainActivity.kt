package com.example.bt12_10

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvThongTinSV: TextView
    private lateinit var edtTenCV: EditText
    private lateinit var edtMoTaCV: EditText
    private lateinit var btnTaoCV: Button
    private lateinit var btnXemDS: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        anhXaView()
        xuLySuKien()
    }

    private fun anhXaView() {
        tvThongTinSV = findViewById(R.id.tvThongTinSV)
        edtTenCV = findViewById(R.id.edtTenCV)
        edtMoTaCV = findViewById(R.id.edtMoTaCV)
        btnTaoCV = findViewById(R.id.btnTaoCV)
        btnXemDS = findViewById(R.id.btnXemDS)
    }

    private fun xuLySuKien() {
        // Nhấn nút Tạo CV -> Chuyển sang ChiTietCongViecActivity và gửi dữ liệu qua Intent
        btnTaoCV.setOnClickListener {
            val ten = edtTenCV.text.toString().trim()
            val moTa = edtMoTaCV.text.toString().trim()

            if (ten.isEmpty()) {
                edtTenCV.error = "Vui lòng nhập tên công việc!"
                edtTenCV.requestFocus()
            } else {
                val yDinh = Intent(this, ChiTietCongViecActivity::class.java).apply {
                    putExtra("TEN_CV", ten)
                    putExtra("MO_TA_CV", moTa)
                }
                startActivity(yDinh)
            }
        }

        // Nút Xem danh sách
        btnXemDS.setOnClickListener {
            Toast.makeText(this, "Xem danh sách công việc", Toast.LENGTH_SHORT).show()
        }
    }
}