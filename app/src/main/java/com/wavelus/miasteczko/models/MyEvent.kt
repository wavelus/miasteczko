package com.wavelus.miasteczko.models

/**
 * Sposób przechowywania danych o wydarzeniu.
 */
class MyEvent() {
    var event_id: String? = null
    /** Nazwa wydarzenia*/
    var event_name: String? = null
    /** Identyfikator właściciela wydarzenia*/
    var event_owner_id: String? = null
    /** Identyfikator miejsca wydarzenia*/
    var event_place_id: String? = null
    /** Data/czas rozpoczęcia wydarzenia*/
    var event_date_start: String? = null
    /** Planowa data/czas zakończenia wydarzenia*/
    var event_date_end: String? = null
    /** Status wydarzenia*/
    var event_status: String? = null

    /** Konstruktor obiektu*/
    constructor(event_id:String, event_name:String,event_owner_id: String,event_place_id: String,event_date_start: String,
                event_date_end: String,event_status: String):this(){
        this.event_id = event_id
        this.event_name = event_name
        this.event_owner_id = event_owner_id
        this.event_place_id = event_place_id
        this.event_date_start = event_date_start
        this.event_date_end = event_date_end
        this.event_status = event_status
    }
}