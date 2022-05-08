package com.example.phanmemhoctiengtrung.Actitivy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.phanmemhoctiengtrung.Model.BaiHoc;
import com.example.phanmemhoctiengtrung.Model.ChiTietBaiHoc;
import com.example.phanmemhoctiengtrung.Model.TaiKhoan;

import java.util.List;

public class Common {
    public static TaiKhoan taiKhoan;
    public static BaiHoc baiHoc;
    public static List<ChiTietBaiHoc>chiTietBaiHocList;
    public static ChiTietBaiHoc chiTietBaiHoc;
    public static void dialog(Context context, String noidung) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(noidung);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}
