package com.example.bt12_10

import java.io.Serializable

data class CongViec(
    var tenCongViec: String,
    var moTa: String,
    var trangThai: String = "Chưa hoàn thành",
    var mucDoUuTien: String = "Trung bình"
) : Serializable {

    fun layThongTinCongViec(): String {
        return "Tên CV: $tenCongViec \nMô tả: $moTa \nMức độ ưu tiên: $mucDoUuTien \nTrạng thái: $trangThai"
    }
}