# Project Scope and Requirements
**Interface Requirements**
-Find show title
-Find show types (Theatre, Musical, Opera, Concert)
-Show description
-Show times and dates
-Show duration
-Price of tickets for a performance
-Availability of tickets for a performance
-If show contains live music, the performer or group should be prominently displayed
-SQL Injection protection (Number selection, Search for title uses prepared statements and *rollsback* (Investigate if this rolls back the database or the query) after search)

**Show Information**
-Shows can be staged multiple times on the same day (matinee and evening)
-Shows have a given type
-Theatre shows, musicals, operas can have other languages
-Concerts always include live music
-If live music is included the performer or group should be shown

**Theatre Information**
-200 seats (120 Stalls 80 circle) - Build from database
-All seats are the same price - Build from database
-Prices of tickets vary by show - Build from database
-Concessions are 25% discount - Build from database

**User Information**
-Must provide a name
-Must provide an address
-Must provide credit card number
-Above only for order, browsing is unrestricted