@scenario#Request_type_new_project

  Feature: scenarion#Request_type_new_project
    Scenario: Authorization
      When autorization with login "test2" and password "123456"

    Scenario: Новое проектирование
      When перенос инициативы в запрос
      Then проверка открытия выпадающего списка в поле 'Улица'
      When выбор улицы из выпадающего списка
      When выбор типа 'Новое проектирование'
      When заполняет поле 'Протяженность, м'
      When заполняет поле 'Содержание работ'
      When заполняет поле 'Причина' 'при создании запроса, с улицей на которую уже есть запросы'
      When клик на кнопку 'Создать'


    Scenario: Выход из системы
      When logout