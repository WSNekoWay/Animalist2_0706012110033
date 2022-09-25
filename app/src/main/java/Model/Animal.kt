package Model

open class Animal(
    nama:String,
    jenis:String,
    usia:String,
    imageUri: String,
    id: Int
) {
    var nama: String = nama
    var jenis: String = jenis
    var usia: String = usia
    var imageUri: String = imageUri
    var id: Int = id

    open fun suara():String {
        return "Aku Hampa Mas"
    }
    fun makan(s : Biji): String{
        return "Nom Nom Nom Kamu memberi makan biji-bijian"
    }
    fun <Int> makan (i: Rumput): String{
        return "Nom Nom Nom Kamu memberi makan rerumputan"
    }

}