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
    using System.ComponentModel;

    public partial class ChiTietBaiHoc
    {
        [DisplayName("ID")]
        public int id { get; set; }
        [DisplayName("Bài học")]
        public int idBaiHoc { get; set; }
        [DisplayName("Tượng hình")]
        public string tuonghinh { get; set; }
        [DisplayName("Nghĩa tiếng việt")]
        public string nghiatiengviet { get; set; }
        [DisplayName("Âm thanh")]
        public string amthanh { get; set; }
        [DisplayName("Video")]
        public string video { get; set; }
        [DisplayName("Đáp án A")]
        public string a { get; set; }
        [DisplayName("Đáp án B")]
        public string b { get; set; }
        [DisplayName("Đáp án C")]
        public string c { get; set; }
        [DisplayName("Đáp án D")]
        public string d { get; set; }
        [DisplayName("Đáp án đúng")]
        public string dapdan { get; set; }
    }
}
