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

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)

        tvThongTinSV=findViewById(R.id.tvThongTinSV)
        edtTenCV=findViewById(R.id.edtTenCV)
        edtMoTaCV = findViewById(R.id.edtMoTaCV)
        btnTaoCV = findViewById(R.id.btnTaoCV)
        btnXemDS = findViewById(R.id.btnXemDS)

        btnTaoCV.setOnClickListener {
            val title = edtTenCV.text.toString().trim()
            val desc = edtMoTaCV.text.toString().trim()
            if (title.isEmpty()) {
                edtTenCV.error = "Vui lòng nhập tên công việc!"
            } else {
                Toast.makeText(this, "Đã thêm công việc: $title", Toast.LENGTH_SHORT).show()
                // Xóa trắng ô nhập sau khi tạo
                edtTenCV.text.clear()
                edtMoTaCV.text.clear()
            }
        }
        btnXemDS.setOnClickListener {
            Toast.makeText(this, "Chuyển sang màn hình danh sách", Toast.LENGTH_SHORT).show()
        }
    }
}