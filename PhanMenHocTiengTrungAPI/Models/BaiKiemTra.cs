//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace PhanMenHocTiengTrungAPI.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class BaiKiemTra
    {
        public int id { get; set; }
        public int idBaiHoc { get; set; }
        public int idTaiKhoan { get; set; }
        public Nullable<int> diem { get; set; }
    }
}
