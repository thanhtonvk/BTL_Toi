using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using PhanMenHocTiengTrungAPI.Models;

namespace PhanMenHocTiengTrungAPI.Controllers
{
    public class ChiTietBaiHocsController : Controller
    {
        private PhanMemHocTiengTrungEntities db = new PhanMemHocTiengTrungEntities();

        // GET: ChiTietBaiHocs
        public ActionResult Index(int? idBaiHoc)
        {
            return View(db.ChiTietBaiHocs.Where(x=>x.idBaiHoc == idBaiHoc).ToList());
        }

        // GET: ChiTietBaiHocs/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            ChiTietBaiHoc chiTietBaiHoc = db.ChiTietBaiHocs.Find(id);
            if (chiTietBaiHoc == null)
            {
                return HttpNotFound();
            }
            return View(chiTietBaiHoc);
        }

        // GET: ChiTietBaiHocs/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: ChiTietBaiHocs/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "id,idBaiHoc,tuonghinh,nghiatiengviet,amthanh,video,a,b,c,d,dapdan")] ChiTietBaiHoc chiTietBaiHoc)
        {
            if (ModelState.IsValid)
            {
                db.ChiTietBaiHocs.Add(chiTietBaiHoc);
                db.SaveChanges();
                return RedirectToAction("Index",new {idBaiHoc = chiTietBaiHoc.idBaiHoc});
            }

            return View(chiTietBaiHoc);
        }

        // GET: ChiTietBaiHocs/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            ChiTietBaiHoc chiTietBaiHoc = db.ChiTietBaiHocs.Find(id);
            if (chiTietBaiHoc == null)
            {
                return HttpNotFound();
            }
            return View(chiTietBaiHoc);
        }

        // POST: ChiTietBaiHocs/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "id,idBaiHoc,tuonghinh,nghiatiengviet,amthanh,video,a,b,c,d,dapdan")] ChiTietBaiHoc chiTietBaiHoc)
        {
            if (ModelState.IsValid)
            {
                db.Entry(chiTietBaiHoc).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index", new { idBaiHoc = chiTietBaiHoc.idBaiHoc });
            }
            return View(chiTietBaiHoc);
        }

        // GET: ChiTietBaiHocs/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            ChiTietBaiHoc chiTietBaiHoc = db.ChiTietBaiHocs.Find(id);
            if (chiTietBaiHoc == null)
            {
                return HttpNotFound();
            }
            return View(chiTietBaiHoc);
        }

        // POST: ChiTietBaiHocs/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            ChiTietBaiHoc chiTietBaiHoc = db.ChiTietBaiHocs.Find(id);
            db.ChiTietBaiHocs.Remove(chiTietBaiHoc);
            db.SaveChanges();
            return RedirectToAction("Index", new { idBaiHoc = chiTietBaiHoc.idBaiHoc });
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
