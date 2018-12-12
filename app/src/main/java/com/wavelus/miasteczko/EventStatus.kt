package com.wavelus.miasteczko

/**
 * Możlwie stany wydarzenia
 */
enum class EventStatus{
    /** Wydarzenie jeszcze się nie zaczęło.*/
    SOON,
    /** Wydarzenie w trakcie.*/
    DURING,
    /** Wydarzenie zostało zakończone.*/
    AFTER
}