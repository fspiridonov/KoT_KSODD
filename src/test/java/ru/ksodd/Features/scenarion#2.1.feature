@scenario#2_1

Feature: scenarion#2_1

  Scenario: Authorization
    When autorization with login "mnn" and password "1"

  Scenario: второй
    When создание нового запроса с помощью клика на кнопку 'Новый запрос'
    Then проверка открытия выпадающего списка в поле 'Улица'
    When выбор улицы из выпадающего списка
    When выбор типа
    When заполняет поле 'Протяженность, м'
    When заполняет поле 'Содержание работ'
    When заполняет поле 'Причина' 'при создании запроса, с улицей на которую уже есть запросы'
    When выбор ответственного
    When кликаем на кнопку 'Создать'

    When logout

  Scenario: ▲▲▲▲▲▲▲ Последний шаг ▲▲▲▲▲▲▲
    When Stop test
