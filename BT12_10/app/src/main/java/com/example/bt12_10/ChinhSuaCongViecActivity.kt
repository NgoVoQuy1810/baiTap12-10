package com.example.bt12_10

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChinhSuaCongViecActivity : AppCompatActivity() {

    private lateinit var edtSuaTenCV: EditText
    private lateinit var edtSuaMoTaCV: EditText
    private lateinit var rgMucDoUuTien: RadioGroup
    private lateinit var rbThap: RadioButton
    private lateinit var rbTrungBinh: RadioButton
    private lateinit var rbCao: RadioButton
    private lateinit var btnLuuCV: Button
    private var congViecCanSua: CongViec? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acticity_chinh_sua_cong_viec)

        anhXaView()
        nhanDuLieuCu()
        xuLySuKien()
    }

    private fun anhXaView() {
        edtSuaTenCV = findViewById(R.id.edtSuaTenCV)
        edtSuaMoTaCV = findViewById(R.id.edtSuaMoTaCV)
        rgMucDoUuTien = findViewById(R.id.rgMucDoUuTien)
        rbThap = findViewById(R.id.rbThap)
        rbTrungBinh = findViewById(R.id.rbTrungBinh)
        rbCao = findViewById(R.id.rbCao)
        btnLuuCV = findViewById(R.id.btnLuuCV)
    }

    private fun nhanDuLieuCu() {
        congViecCanSua = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("DOI_TUONG_CAN_SUA", CongViec::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("DOI_TUONG_CAN_SUA") as? CongViec
        }

        congViecCanSua?.let { cv ->
            edtSuaTenCV.setText(cv.tenCongViec)
            edtSuaMoTaCV.setText(cv.moTa)

            // Hiển thị mức độ ưu tiên hiện tại
            when (cv.mucDoUuTien) {
                "Thấp" -> rbThap.isChecked = true
                "Trung bình" -> rbTrungBinh.isChecked = true
                "Cao" -> rbCao.isChecked = true
                else -> rbTrungBinh.isChecked = true
            }
        }
    }

    private fun xuLySuKien() {
        btnLuuCV.setOnClickListener {
            val tenMoi = edtSuaTenCV.text.toString()
            val moTaMoi = edtSuaMoTaCV.text.toString().trim()

            if (tenMoi.isBlank()) {
                edtSuaTenCV.error = "Tên công việc không hợp lệ!"
                edtSuaTenCV.requestFocus()
                Toast.makeText(this, "Tên không được để trống hoặc chỉ chứa khoảng trắng", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val idDuocChon = rgMucDoUuTien.checkedRadioButtonId
            val mucDoUuTienMoi = findViewById<RadioButton>(idDuocChon)?.text.toString()

            val congViecCapNhat = CongViec(
                tenCongViec = tenMoi.trim(),
                moTa = moTaMoi,
                trangThai = congViecCanSua?.trangThai ?: "Đã chỉnh sửa",
                mucDoUuTien = mucDoUuTienMoi
            )

            val intentKetQua = Intent().apply {
                putExtra("DOI_TUONG_CONG_VIEC_MOI", congViecCapNhat)
            }
            setResult(Activity.RESULT_OK, intentKetQua)
            finish()
        }
    }
}