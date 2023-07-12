package com.example.demosimplexml

import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import java.io.InputStream

fun parser(inputStream: InputStream): List<HocSinh> {
    val xmlParser = Xml.newPullParser()
    xmlParser.setInput(inputStream, "UTF-8")
    xmlParser.nextTag()
    return readDanhSach(xmlParser)
}

fun readDanhSach(xmlParser: XmlPullParser): List<HocSinh>{
    val listHocSinhs = ArrayList<HocSinh>()

    xmlParser.require(XmlPullParser.START_TAG, null, "danhsach")
    while (xmlParser.next() != XmlPullParser.END_TAG){
        if (xmlParser.eventType != XmlPullParser.START_TAG) {
            continue
        }
        val name: String = xmlParser.name
        if (name.equals("hocsinh")) {
            listHocSinhs.add(readHocSinh(xmlParser))
        } else {
            skip(xmlParser)
        }
    }

    return listHocSinhs
}

fun readHocSinh(xmlParser: XmlPullParser): HocSinh{
    var ten = ""
    var tuoi = 0
    var img = ""

    xmlParser.require(XmlPullParser.START_TAG, null, "hocsinh")
    while (xmlParser.next() != XmlPullParser.END_TAG){
        if(xmlParser.eventType != XmlPullParser.START_TAG){
            continue
        }
        val name = xmlParser.name
        if(name.equals("ten")){
            ten = readTen(xmlParser)
        } else if(name.equals("tuoi")){
            tuoi = readTuoi(xmlParser)
        } else if(name.equals("img")){
            img = readImg(xmlParser)
        } else {
            skip(xmlParser)
        }
    }

    val hocSinh: HocSinh = HocSinh(ten, tuoi, img)
    return hocSinh
}

fun readTen(xmlParser: XmlPullParser): String{
    xmlParser.require(XmlPullParser.START_TAG, null, "ten")
    val ten = readText(xmlParser)
    xmlParser.require(XmlPullParser.END_TAG, null, "ten")
    return ten
}

fun readTuoi(xmlParser: XmlPullParser): Int{
    xmlParser.require(XmlPullParser.START_TAG, null, "tuoi")
    val tuoi = readText(xmlParser).toInt()
    xmlParser.require(XmlPullParser.END_TAG, null, "tuoi")
    return tuoi
}

fun readText(xmlParser: XmlPullParser): String{
    var text = ""
    if(xmlParser.next() == XmlPullParser.TEXT){
        text = xmlParser.text
        xmlParser.nextTag()
    }
    return text
}

fun readImg(xmlParser: XmlPullParser): String{
    xmlParser.require(XmlPullParser.START_TAG, null, "img")
    val img = xmlParser.getAttributeValue(null, "src")
    xmlParser.nextTag()
    xmlParser.require(XmlPullParser.END_TAG, null, "img")
    return img
}

fun skip(xmlParser: XmlPullParser) {
    if(xmlParser.eventType != XmlPullParser.START_TAG){
        throw IllegalArgumentException("Must be skip with start tag")
    }
    var depth = 1
    while (depth != 0){
        when (xmlParser.next()) {
            XmlPullParser.END_TAG -> depth--
            XmlPullParser.START_TAG -> depth++
        }
    }
}