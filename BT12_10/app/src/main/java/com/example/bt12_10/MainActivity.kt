package com.example.bt12_10

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
        btnTaoCV.setOnClickListener {
            val ten = edtTenCV.text.toString().trim()
            val moTa = edtMoTaCV.text.toString().trim()

            if (ten.isEmpty()) {
                edtTenCV.error = "Vui lòng nhập tên công việc!"
                edtTenCV.requestFocus()
            } else {
                // Tạo đối tượng CongViec từ class
                val congViecMoi = CongViec(
                    tenCongViec = ten,
                    moTa = if (moTa.isEmpty()) "Không có mô tả" else moTa,
                    trangThai = "Mới tạo"
                )

                // Truyền toàn bộ object CongViec qua Intent
                val yDinh = Intent(this, ChiTietCongViecActivity::class.java).apply {
                    putExtra("DOI_TUONG_CONG_VIEC", congViecMoi)
                }
                startActivity(yDinh)
            }
        }

        btnXemDS.setOnClickListener {
            Toast.makeText(this, "Xem danh sách công việc", Toast.LENGTH_SHORT).show()
        }
    }
}