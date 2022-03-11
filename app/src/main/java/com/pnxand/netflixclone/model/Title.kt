package com.pnxand.netflixclone.model

import java.io.Serializable

class RcvTitle: Serializable {
    var page: Int = 0
    var results: ArrayList<Title> = arrayListOf()
}
class Title: Serializable {
    var id: Int = 0
    var media_type: String = ""
    var original_name: String = ""
    var original_title: String = ""
    var poster_path: String = ""
    var vote_count: Int = 0
    var vote_average: Double = 0.0
    var release_date: String = ""
    var overview: String = ""
}