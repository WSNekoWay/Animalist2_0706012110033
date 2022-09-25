package Model

import android.os.Parcel
import android.os.Parcelable

class Kambing ( nama:String,
                jenis:String,
                usia:String,
                imageUri: String,
                id: Int):Animal(nama,jenis,usia,imageUri,id) {



    override fun suara(): String {
        return "Mbeh Mbeh Mbeh"

    }
                }