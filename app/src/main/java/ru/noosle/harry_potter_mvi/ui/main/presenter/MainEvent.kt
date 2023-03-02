package ru.noosle.harry_potter_mvi.ui.main.presenter

interface MainEvent
    class AllPersonsEvent: MainEvent
    class AllStudentsEvent: MainEvent
    class AllStaffEvent: MainEvent
    class AllSpellsEvent: MainEvent
    class StudentsByFacultyEvent(val faculty: String): MainEvent
