package com.example.bt12_10

import android.app.Activity
import android.content.Intent
import android.os.Build
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
    private lateinit var btnHoanThanh: Button // Bổ sung nút

    private var congViecHienTai: CongViec? = null

    private val trinhNhanKetQua = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { ketQua ->
        if (ketQua.resultCode == Activity.RESULT_OK) {
            val duLieuTraVe = ketQua.data
            val congViecDaSua = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                duLieuTraVe?.getSerializableExtra("DOI_TUONG_CONG_VIEC_MOI", CongViec::class.java)
            } else {
                @Suppress("DEPRECATION")
                duLieuTraVe?.getSerializableExtra("DOI_TUONG_CONG_VIEC_MOI") as? CongViec
            }

            congViecDaSua?.let {
                congViecHienTai = it
                capNhatGiaoDien()
                Toast.makeText(this, "Đã cập nhật thông tin!", Toast.LENGTH_SHORT).show()
            }
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
        btnHoanThanh = findViewById(R.id.btnHoanThanh)
    }

    private fun nhanDuLieuBanDau() {
        congViecHienTai = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("DOI_TUONG_CONG_VIEC", CongViec::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("DOI_TUONG_CONG_VIEC") as? CongViec
        }
        capNhatGiaoDien()
    }

    private fun capNhatGiaoDien() {
        congViecHienTai?.let { cv ->
            tvHienThiTen.text = cv.tenCongViec
            // Dùng hàm layThongTinCongViec() từ Câu 4 để hiển thị
            tvHienThiMoTa.text = cv.layThongTinCongViec()

            // Cập nhật trạng thái hiển thị của nút Hoàn thành
            if (cv.trangThai == "Đã hoàn thành") {
                btnHoanThanh.isEnabled = false
                btnHoanThanh.text = "Công việc đã hoàn thành"
                btnChinhSuaCV.isEnabled = false // Không cho sửa nếu đã xong
            } else {
                btnHoanThanh.isEnabled = true
                btnHoanThanh.text = "Đánh dấu hoàn thành"
                btnChinhSuaCV.isEnabled = true
            }
        }
    }

    private fun xuLySuKien() {
        btnChinhSuaCV.setOnClickListener {
            congViecHienTai?.let { cv ->
                val yDinh = Intent(this, ChinhSuaCongViecActivity::class.java).apply {
                    putExtra("DOI_TUONG_CAN_SUA", cv)
                }
                trinhNhanKetQua.launch(yDinh)
            }
        }

        // Xử lý khi bấm đánh dấu hoàn thành
        btnHoanThanh.setOnClickListener {
            congViecHienTai?.trangThai = "Đã hoàn thành"
            capNhatGiaoDien()
            Toast.makeText(this, "Đã đánh dấu hoàn thành!", Toast.LENGTH_SHORT).show()
        }

        btnQuayLai.setOnClickListener {
            finish()
        }
    }
}