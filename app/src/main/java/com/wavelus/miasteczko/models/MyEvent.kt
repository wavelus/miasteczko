package com.wavelus.miasteczko.models

class MyEvent() {
    var event_name: String? = null
    var event_owner_id: String? = null
    var event_place_id: String? = null
    var event_date_start: String? = null
    var event_date_end: String? = null
    var event_status: String? = null

    constructor(event_name:String,event_owner_id: String,event_place_id: String,event_date_start: String,
                event_date_end: String,event_status: String):this(){
        this.event_name = event_name
        this.event_owner_id = event_owner_id
        this.event_place_id = event_place_id
        this.event_date_start = event_date_start
        this.event_date_end = event_date_end
        this.event_status = event_status
    }
}