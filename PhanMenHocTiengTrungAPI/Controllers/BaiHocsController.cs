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
    public class BaiHocsController : Controller
    {
        private PhanMemHocTiengTrungEntities db = new PhanMemHocTiengTrungEntities();

        // GET: BaiHocs
        public ActionResult Index()
        {
            return View(db.BaiHocs.ToList());
        }

        // GET: BaiHocs/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            BaiHoc baiHoc = db.BaiHocs.Find(id);
            if (baiHoc == null)
            {
                return HttpNotFound();
            }
            return View(baiHoc);
        }

        // GET: BaiHocs/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: BaiHocs/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "id,chude,idYoutube,diem")] BaiHoc baiHoc)
        {
            if (ModelState.IsValid)
            {
                db.BaiHocs.Add(baiHoc);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(baiHoc);
        }

        // GET: BaiHocs/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            BaiHoc baiHoc = db.BaiHocs.Find(id);
            if (baiHoc == null)
            {
                return HttpNotFound();
            }
            return View(baiHoc);
        }

        // POST: BaiHocs/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "id,chude,idYoutube,diem")] BaiHoc baiHoc)
        {
            if (ModelState.IsValid)
            {
                db.Entry(baiHoc).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(baiHoc);
        }

        // GET: BaiHocs/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            BaiHoc baiHoc = db.BaiHocs.Find(id);
            if (baiHoc == null)
            {
                return HttpNotFound();
            }
            return View(baiHoc);
        }

        // POST: BaiHocs/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            BaiHoc baiHoc = db.BaiHocs.Find(id);
            db.BaiHocs.Remove(baiHoc);
            db.SaveChanges();
            return RedirectToAction("Index");
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
