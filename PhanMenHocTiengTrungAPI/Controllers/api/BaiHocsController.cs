using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using PhanMenHocTiengTrungAPI.Models;

namespace PhanMenHocTiengTrungAPI.Controllers.api
{
    public class BaiHocsController : ApiController
    {
        private PhanMemHocTiengTrungEntities db = new PhanMemHocTiengTrungEntities();

        // GET: api/BaiHocs
        [Route("api/BaiHocs")]
        public IEnumerable<BaiHoc> GetBaiHocs()
        {
            return db.BaiHocs.ToList();
        }
        [Route("api/BaiHocs/BaiDaHoc")]
        public IEnumerable<Pro_BaiDaHoc_Result> GetBaiDaHocs(int? idTaiKhoan)
        {
            return db.Pro_BaiDaHoc(idTaiKhoan).ToList();
        }
        [Route("api/BaiHocs/BaiKiemTra")]
        public IEnumerable<Pro_BaiKiemTra_Result> GetBaiKiemTra(int? idTaiKhoan)
        {
            return db.Pro_BaiKiemTra(idTaiKhoan).ToList();
        }
        [Route("api/BaiHocs/PostBaiDaHoc")]
        public IHttpActionResult PostBaiHoc(int idBaiHoc, int idTaiKhoan, int diem)
        {
            BaiDaHoc baiDaHoc = new BaiDaHoc()
            {
                idBaiHoc = idBaiHoc,
                idTaiKhoan = idTaiKhoan,
                diem = diem
            };
            db.BaiDaHocs.Add(baiDaHoc);
            return Ok(db.SaveChanges());
        }
        [Route("api/BaiHocs/PostBaiKiemTra")]
        public IHttpActionResult PostBaiKiemTra(int idBaiHoc, int idTaiKhoan, int diem)
        {
            BaiKiemTra baiKiemTra = new BaiKiemTra()
            {
                idBaiHoc = idBaiHoc,
                idTaiKhoan = idTaiKhoan,
                diem = diem
            };
            db.BaiKiemTras.Add(baiKiemTra);
            return Ok(db.SaveChanges());
        }
        [Route("api/BaiHocs/GetChiTietBaiHoc")]
        public IEnumerable<Pro_ChiTietBaiHoc_Result> GetChiTietBaiHocs(int? idBaiHoc)
        {
            return db.Pro_ChiTietBaiHoc(idBaiHoc);
        }

    }
}