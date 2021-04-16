package com.example.talktome.ui.authorized.survey.data

interface UISurveyData {

    fun userGenderData(): List<Any>

    fun getGenderSurveyData(): List<Any>

    fun getServiceSurveyData(): List<Any>
}

class UISurveyDataDelegate() : UISurveyData {

    override fun userGenderData() = mutableListOf<Any>().apply {
        add("Ваш пол?")

        add(
            arrayListOf(
                UIGender(1, "Женщина"),
                UIGender(2, "Мужчина")
            )

        )
    }

    override fun getGenderSurveyData() = mutableListOf<Any>().apply {
        add("Ваш пол?")

        add(
            arrayListOf(
                UIGender(1, "Женщина"),
                UIGender(2, "Мужчина")
            )

        )

        add("С кем вам было бы комфортнее работать?")

        add(
            arrayListOf(
                UIGender(1, "Женщина"),
                UIGender(2, "Мужчина"),
                UIGender(3, "Все равно")
            )

        )
    }

    override fun getServiceSurveyData() = mutableListOf<Any>().apply {
        add("Какая у вас тревожность?")

        add(
            arrayListOf(
                UIServiceType(1, "Cтресс", false),
                UIServiceType(2, "Апатия", false),
                UIServiceType(3, "Отсутствие мотивации", false),
                UIServiceType(4, "Панические атаки", false),
                UIServiceType(5, "Нарушение сна", false),
                UIServiceType(6, "Перепады настроение", false),
                UIServiceType(7, "Неадекватная самооценка", false),
                UIServiceType(8, "Депрессия", false),
                UIServiceType(9, "Хроническая усталость", false),
                UIServiceType(10, "Проблемы с концентрацией", false),
                UIServiceType(11, "Чувство тревоги", false),
                UIServiceType(12, "Неконтролируемые эмоции", false)
            )
        )
    }

}