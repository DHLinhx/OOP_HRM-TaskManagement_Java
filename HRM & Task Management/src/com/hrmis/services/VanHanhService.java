package com.hrmis.services;

import com.hrmis.data.DatabaseMock;
import com.hrmis.models.*;
import com.hrmis.enums.*;

public class VanHanhService {

    // =========================================================================
    // CÂU 1: Bản in hợp đồng chi tiết
    // =========================================================================
    public void inHopDongChiTiet(int maHopDong) {
        // 1. Duyệt trực tiếp qua từng HopDong (không cần ép kiểu)
        for (HopDong hd : DatabaseMock.dsHopDong) {
            if (hd.getMaHopDong() == maHopDong) {
                System.out.println("--- THÔNG TIN HỢP ĐỒNG ---");
                System.out.println("Số hợp đồng: " + hd.getSoHopDong());
                System.out.println("Mã nhân viên: " + hd.getMaNhanVien());
                System.out.println("Loại hợp đồng: " + hd.getLoaiHopDong());
                System.out.printf("Lương thỏa thuận: %,.0f VNĐ\n", hd.getLuongThoaThuan());
                System.out.println("Trạng thái: " + hd.getTrangThai());

                // 2. Duyệt trực tiếp qua từng DieuKhoanHopDong
                System.out.println("--- CÁC ĐIỀU KHOẢN KÈM THEO ---");
                for (DieuKhoanHopDong dk : DatabaseMock.dsDieuKhoanHopDong) {
                    if (dk.getMaHopDong() == maHopDong) {
                        System.out.println("Điều " + dk.getSoThuTuDieu() + ": " + dk.getTieuDeDieuKhoan() + " ("
                                + dk.getLoaiDieuKhoan() + ")");
                        System.out.println("  -> " + dk.getNoiDungDieuKhoan());
                    }
                }
                return;
            }
        }

        System.out.println("Không tìm thấy hợp đồng có mã: " + maHopDong);
    }
}
