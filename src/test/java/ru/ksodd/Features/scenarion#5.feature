@scenario#5

Feature: scenarion#5

  Scenario: Авторизация
    When вводим логин "mnn"
    And  вводим пароль "1"


  Scenario: Седьмой

    When перенос тесового документа из этапа согласования в этап утверждённые
#    When заполнение всех чекбоксов этапа утверждённых
    When входит в чек-бокс 'Начало реализации'



    When logout

  Scenario: ▲▲▲▲▲▲▲ Последний шаг ▲▲▲▲▲▲▲
    When выход из системы
