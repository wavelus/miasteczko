package com.wavelus.miasteczko.models

class MyTable {
    companion object {
        fun getTableId(id: Int):String{
            return when(id){
                0 -> "flanki"
                1 -> "koszary"
                2 -> "piwna_siedziba"
                3 -> "gitary"
                else -> {
                    "miasteczko"
                }
            }
        }
        fun getTableName(name: String):String{
            return when(name){
                "flanki" -> "Flankowy Zaułek"
                "koszary" -> "Sportowe Kosz'ary"
                "piwna_siedziba" -> "Piwna Siedziba"
                "gitary" -> "Śpiewające Gitary"

                else -> {
                    "Miasteczko"
                }
            }
        }
    }

}