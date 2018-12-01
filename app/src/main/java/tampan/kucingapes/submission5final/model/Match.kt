/*
 * Match.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 1:59 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.model

data class Match(var idEvent: String,
                 var dateEvent: String,
                 var strTime: String,
                 var strEvent: String,
                 var strHomeTeam: String,
                 var strAwayTeam: String,
                 var intHomeScore: String,
                 var intAwayScore: String,
                 var idHomeTeam: String,
                 var idAwayTeam: String,
                 var strHomeGoalDetails: String,
                 var strAwayGoalDetails: String,
                 var intHomeShots: String,
                 var intAwayShots: String,
                 var strHomeYellowCards: String,
                 var strAwayYellowCards: String,
                 var strHomeRedCards: String,
                 var strAwayRedCards: String,
                 var strHomeLineupGoalkeeper: String,
                 var strAwayLineupGoalkeeper: String,
                 var strHomeLineupDefense: String,
                 var strAwayLineupDefense: String,
                 var strHomeLineupMidfield: String,
                 var strAwayLineupMidfield: String,
                 var strHomeLineupForward: String,
                 var strAwayLineupForward: String,
                 var strHomeLineupSubstitutes: String,
                 var strAwayLineupSubstitutes: String)