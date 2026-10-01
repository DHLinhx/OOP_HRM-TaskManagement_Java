package com.hrmis;

import java.time.LocalDate;

import com.hrmis.services.VanHanhService;

public class Main {
    public static void main(String[] args) {
        // DUONG
        // 1. Khởi tạo Service Vận hành
        VanHanhService vanHanhService = new VanHanhService();

        // 2. Chạy thử Câu 1 với mã hợp đồng số 3 (hoặc số 1, 2)
        System.out.println(">>> ĐANG CHẠY THỬ CÂU 1:");
        vanHanhService.inHopDongChiTiet(3);
        System.out.println("\n>>> ĐANG CHẠY THỬ CÂU 2:");
        vanHanhService.canhBaoHopDongSapHetHan(LocalDate.now());

        // LINH

        // HAO
    }

}
