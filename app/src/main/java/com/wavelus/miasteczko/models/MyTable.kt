package com.wavelus.miasteczko.models

class MyTable {
    companion object {
        fun getTableId(id: Int):String{
            return when(id){
                0 -> "flanki"
                1 -> "piwna_siedziba"
                else -> {
                    "miasteczko"
                }
            }
        }
        fun getTableName(name: String):String{
            return when(name){
                "flanki" -> "Flankowy Zaułek"
                "piwna_siedziba" -> "Piwna Siedziba"
                else -> {
                    "Miasteczko"
                }
            }
        }
    }

}