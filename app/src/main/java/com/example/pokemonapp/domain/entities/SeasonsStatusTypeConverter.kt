package com.example.pokemonapp.domain.entities

import androidx.room.TypeConverter

//class SeasonsStatusTypeConverter {
//
//    @TypeConverter
//    fun fromTypeToInt(value: SeasonStatusType) =
//
//
//}


//@TypeConverter
//fun fromTypeToString(value: List<ReasonType>): List<String> = value.map {
//    when (it) {
//        ReasonType.REFUSAL_DELIVERY -> ReasonType.REFUSAL_DELIVERY.name
//        ReasonType.REFUSAL_PLANNED_PICKUP -> ReasonType.REFUSAL_PLANNED_PICKUP.name
//        ReasonType.REFUSAL_VISIT -> ReasonType.REFUSAL_VISIT.name
//    }
//}
//
//@TypeConverter
//fun fromStringToType(value: List<String>): List<ReasonType> =
//    value.map {
//        when (it) {
//            ReasonType.REFUSAL_VISIT.name -> ReasonType.REFUSAL_VISIT
//            ReasonType.REFUSAL_DELIVERY.name -> ReasonType.REFUSAL_DELIVERY
//            ReasonType.REFUSAL_PLANNED_PICKUP.name -> ReasonType.REFUSAL_PLANNED_PICKUP
//            else -> throw InvalidReasonTypeException()
//        }
//    }