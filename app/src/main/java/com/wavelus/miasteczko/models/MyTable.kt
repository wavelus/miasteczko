package com.wavelus.miasteczko.models

/**
 * Klasa przechowująca informacje na temat miejsc w których mogą odbywać się wydarzenia wraz z ich identyfikatorami*/
class MyTable {
    companion object {
        /**Zwraca id stołu*/
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
        /**Zwraca nazwę stołu*/
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
        /**Zwraca id miasta*/
        fun getTownId(name:String): String{
            return when(name) {
                "Miasteczko AGH" -> "town_agh"
                "Politechnika" -> "politechnika"
                else -> {
                    "town_agh"
                }
            }
        }
    }

}