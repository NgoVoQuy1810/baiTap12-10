package com.example.bt12_10

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ChinhSuaCongViecActivity : AppCompatActivity() {

    private lateinit var edtSuaTenCV: EditText
    private lateinit var edtSuaMoTaCV: EditText
    private lateinit var btnLuuCV: Button
    private var congViecCanSua: CongViec? = null

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
        congViecCanSua = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("DOI_TUONG_CAN_SUA", CongViec::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("DOI_TUONG_CAN_SUA") as? CongViec
        }

        congViecCanSua?.let {
            edtSuaTenCV.setText(it.tenCongViec)
            edtSuaMoTaCV.setText(it.moTa)
        }
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

            // Cập nhật lại thuộc tính của đối tượng CongViec
            val congViecCapNhat = CongViec(
                tenCongViec = tenMoi,
                moTa = moTaMoi,
                trangThai = "Đã chỉnh sửa"
            )

            val intentKetQua = Intent().apply {
                putExtra("DOI_TUONG_CONG_VIEC_MOI", congViecCapNhat)
            }
            setResult(Activity.RESULT_OK, intentKetQua)
            finish()
        }
    }
}