package com.hrmis;

import com.hrmis.services.VanHanhService;

public class Main {
    public static void main(String[] args) {
        // 1. Khởi tạo Service Vận hành
        VanHanhService vanHanhService = new VanHanhService();

        // 2. Chạy thử Câu 1 với mã hợp đồng số 3 (hoặc số 1, 2)
        System.out.println(">>> ĐANG CHẠY THỬ CÂU 1:");
        vanHanhService.inHopDongChiTiet(3);
    }
}
