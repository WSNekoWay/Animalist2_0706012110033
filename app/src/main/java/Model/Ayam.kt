package Model

import android.os.Parcelable
class Ayam( nama:String,
            jenis:String,
            usia:String,
            imageUri: String,
            id: Int):Animal(nama,jenis,usia,imageUri,id) {



                override fun suara(): String {
                    return "Bock Bock Bock"}
            }