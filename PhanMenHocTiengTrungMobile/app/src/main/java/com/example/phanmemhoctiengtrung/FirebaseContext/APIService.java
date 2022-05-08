package com.example.phanmemhoctiengtrung.FirebaseContext;

import com.example.phanmemhoctiengtrung.Model.BaiHoc;
import com.example.phanmemhoctiengtrung.Model.ChiTietBaiHoc;
import com.example.phanmemhoctiengtrung.Model.TaiKhoan;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface APIService {
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    APIService api = new Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(APIService.class);
    @GET("api/BaiHocs")
    Call<List<BaiHoc>> getBaiHocs();
    @GET("api/BaiHocs/BaiDaHoc")
    Call<List<BaiHoc>> getBaiDaHocs(@Query("idTaiKhoan") int idTaiKhoan);
    @GET("api/BaiHocs/BaiKiemTra")
    Call<List<BaiHoc>> getBaiKiemTras(@Query("idTaiKhoan") int idTaiKhoan);
    @POST("api/BaiHocs/PostBaiDaHoc")
    Call<Void> addBaiDaHoc(@Query("idBaiHoc") int idBaiHoc,@Query("idTaiKhoan") int idTaiKhoan,@Query("diem") int diem);

    @POST("api/BaiHocs/PostBaiKiemTra")
    Call<Void> addBaiKiemTra(@Query("idBaiHoc") int idBaiHoc,@Query("idTaiKhoan") int idTaiKhoan,@Query("diem") int diem);
    @GET("api/BaiHocs/GetChiTietBaiHoc")
    Call<List<ChiTietBaiHoc>> getChiTietBaiHocs(@Query("idBaiHoc") int idBaiHoc);
    @POST("api/TaiKhoans")
    Call<Void> addTaiKhoan(@Body TaiKhoan taiKhoan);
    @PUT("api/TaiKhoans")
    Call<Void> updateTaiKhoan(@Query("id") int idTaiKhoan,@Body TaiKhoan taiKhoan);


}
