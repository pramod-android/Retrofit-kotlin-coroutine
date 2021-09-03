package com.patil.retrofit.coroutines.data.model

class Acromine : ArrayList<Acromine.AcromineItem>(){
    data class AcromineItem(
        val sf: String,
        val lfs: List<Lf>
    ) {
        data class Lf(
            val lf: String,
            val freq: Int,
            val since: Int,
            val vars: List<Var>
        ) {
            data class Var(
                val lf: String,
                val freq: Int,
                val since: Int
            )
        }
    }
}