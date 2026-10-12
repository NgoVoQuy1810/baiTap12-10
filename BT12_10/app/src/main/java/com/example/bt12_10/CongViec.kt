package com.example.bt12_10

import java.io.Serializable

// Class CongViec implements Serializable để có thể gửi qua Intent
data class CongViec(
    var tenCongViec: String,
    var moTa: String,
    var trangThai: String = "Chưa hoàn thành"
) : Serializable {

    // Hàm trả về chuỗi thông tin công việc
    fun layThongTinCongViec(): String {
        return "Tên CV: $tenCongViec\nMô tả: $moTa\nTrạng thái: $trangThai"
    }
}