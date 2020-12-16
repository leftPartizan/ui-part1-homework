package com.example.ui_part_1_homework.utill

/*
 * класс служит для предоставления поведения ординатора, которому мы даём деньги.
 * в зависимости от колличества полученных денег и немного рандома задаётся прирост уважания
 * ординатора к вам.
 * числовые значения капитала и репутации хранятся во вьюшках и от туда извлекаются служебным методом
 *
 */
class OrdinatorBehavior(){

    private val startCapital = (0..500).random()

    private val fellingState = (0..40).random()

    private fun getFelling(number: Int) : String {
        return when (number) {
            in 1..20 -> "враждебное"
            in 20..40 -> "пренебрежение"
            in 40..60 -> "сочувственное"
            in 60..80 -> "спокойное"
            in 80..90 -> "доброжилательное"
            in 90..100 -> "он тебя знает"
            else -> "сам не знает какое"
        }
    }

    private fun takeMoneyGetReputation(money: Int) : Int{
        return when (money) {
            0 -> 0
            in 1..10 -> (0..45).random() - 30
            in 10..25 -> (0..55).random() - 30
            in 25..50 -> (10..65).random() - 20
            in 50..100 -> (30..70).random() - 40
            in 100..1000 -> (0..50).random() - 10
            else -> 0
        }
    }

    private fun getNewState(money: Int, state: Int): Int {
        var newstate = state + takeMoneyGetReputation(money)
        if (newstate>100) newstate = 100
        if (newstate<0) newstate = 0
        return newstate
    }

    fun getCapitalText(number: Int?) : String {
        if (number == null) return getCapitalTextStart()
        return "сбережения ординатора: $number септимов"
    }

    fun getCapitalTextStart() = getCapitalText(startCapital)

    fun getFellingTextStart() = "отношение к вам " + getFelling(fellingState) + " ($fellingState)"

    fun getFellingText(money: Int, state: Int) : String{
        val newState = getNewState(money,state)
        return "отношение к вам " + getFelling(newState) + " ($newState)"
    }

    // служит для извлечения числового значения из строк состояния ординатора
    fun getStateIntFromView(text : String): Int {
        return text.filter { it.isDigit() }.toInt()
    }



}